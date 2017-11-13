package com.jinshun.contact.controller.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

public class ControllerSupport {

	public static final Message SUCCESS = new Message();

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public HttpServletResponse getResponse() {
		return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public void setCurrentUser(User user) {
		getSession().setAttribute(Environment.LOGIN_USER_KEY, user);
	}

	public User getCurrentUser() {
		Object obj = getSession().getAttribute(Environment.LOGIN_USER_KEY);
		return obj != null ? (User) obj : null;
	}

	public String getServerRootUrl() {
		HttpServletRequest request = getRequest();
		String rootName = request.getRequestURI().substring(1);
		StringBuilder url = new StringBuilder("http://");
		if (request.getProtocol().toLowerCase().indexOf("https") != -1) {
			url = new StringBuilder("https://");
		}
		url.append(request.getServerName()).append(":").append(request.getServerPort());
		url.append("/").append(rootName.substring(0, rootName.indexOf("/")));
		return url.toString();
	}

	public String getClientIp() {
		HttpServletRequest request = getRequest();

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public boolean isInner(String ip) {
		String reg = "(10|172|192)\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-5]{0,2}|[3-9][0-9]{0,1})";
		Pattern p = Pattern.compile(reg);
		Matcher matcher = p.matcher(ip);
		return matcher.find();
	}
	
	public ResponseEntity<byte[]> getStreamRepsonse(byte[] bytes, String attachment) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	   	headers.setContentDispositionFormData("attachment", attachment);
	   	return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

	public static class Message {

		private Boolean success = true;

		private Integer status = 0;

		private Object data;

		public Boolean getSuccess() {
			return success;
		}

		public void setSuccess(Boolean success) {
			this.success = success;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
	}
}
