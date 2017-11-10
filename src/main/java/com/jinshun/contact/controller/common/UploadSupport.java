package com.jinshun.contact.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.ReflectUtils;
import com.jinshun.contact.utils.enums.DateStyle;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class UploadSupport extends ControllerSupport {

	@RequestMapping(value = "/uploadTmp")
	public @ResponseBody Map<String, String> uploadTmp(@RequestParam(value = "file", required = false) MultipartFile mfile, HttpServletRequest request) throws IOException {
		CommonsMultipartFile cmfile = (CommonsMultipartFile) mfile;
		File srcFile = (File) ReflectUtils.forceGet(cmfile.getFileItem(), "tempFile");
		File destFile = new File(Environment.ROOT_PATH + Environment.UPLOAD_PATH + "tmp/" + DateUtils.getDate(new Date(), DateStyle.YYYY_MM_DD) + "/" + srcFile.getName());
		FileUtils.copyFile(srcFile, destFile);

		Map<String, String> map = new HashMap<String, String>();
		map.put("originalFilename", cmfile.getOriginalFilename());
		map.put("tempFile", destFile.getPath());
		return map;
	}

	public static class MultipartUpload implements Serializable {

		private static final long serialVersionUID = -5001501605731161592L;

		private String originalFilenames;
		private String tempFiles;

		private String originalFilenames1;
		private String tempFiles1;

		private String originalFilenames2;
		private String tempFiles2;

		private String originalFilenames3;
		private String tempFiles3;

		private String originalFilenames4;
		private String tempFiles4;

		private String originalFilenames5;
		private String tempFiles5;

		private String originalFilenames6;
		private String tempFiles6;

		private String originalFilenames7;
		private String tempFiles7;

		public String getOriginalFilenames() {
			return originalFilenames;
		}

		public void setOriginalFilenames(String originalFilenames) {
			this.originalFilenames = originalFilenames;
		}

		public String getTempFiles() {
			return tempFiles;
		}

		public void setTempFiles(String tempFiles) {
			this.tempFiles = tempFiles;
		}

		public String getOriginalFilenames1() {
			return originalFilenames1;
		}

		public void setOriginalFilenames1(String originalFilenames1) {
			this.originalFilenames1 = originalFilenames1;
		}

		public String getTempFiles1() {
			return tempFiles1;
		}

		public void setTempFiles1(String tempFiles1) {
			this.tempFiles1 = tempFiles1;
		}

		public String getOriginalFilenames2() {
			return originalFilenames2;
		}

		public void setOriginalFilenames2(String originalFilenames2) {
			this.originalFilenames2 = originalFilenames2;
		}

		public String getTempFiles2() {
			return tempFiles2;
		}

		public void setTempFiles2(String tempFiles2) {
			this.tempFiles2 = tempFiles2;
		}

		public String getOriginalFilenames3() {
			return originalFilenames3;
		}

		public void setOriginalFilenames3(String originalFilenames3) {
			this.originalFilenames3 = originalFilenames3;
		}

		public String getTempFiles3() {
			return tempFiles3;
		}

		public void setTempFiles3(String tempFiles3) {
			this.tempFiles3 = tempFiles3;
		}

		public String getOriginalFilenames4() {
			return originalFilenames4;
		}

		public void setOriginalFilenames4(String originalFilenames4) {
			this.originalFilenames4 = originalFilenames4;
		}

		public String getTempFiles4() {
			return tempFiles4;
		}

		public void setTempFiles4(String tempFiles4) {
			this.tempFiles4 = tempFiles4;
		}

		public String getOriginalFilenames5() {
			return originalFilenames5;
		}

		public void setOriginalFilenames5(String originalFilenames5) {
			this.originalFilenames5 = originalFilenames5;
		}

		public String getTempFiles5() {
			return tempFiles5;
		}

		public void setTempFiles5(String tempFiles5) {
			this.tempFiles5 = tempFiles5;
		}

		public String getOriginalFilenames6() {
			return originalFilenames6;
		}

		public void setOriginalFilenames6(String originalFilenames6) {
			this.originalFilenames6 = originalFilenames6;
		}

		public String getTempFiles6() {
			return tempFiles6;
		}

		public void setTempFiles6(String tempFiles6) {
			this.tempFiles6 = tempFiles6;
		}

		public String getOriginalFilenames7() {
			return originalFilenames7;
		}

		public void setOriginalFilenames7(String originalFilenames7) {
			this.originalFilenames7 = originalFilenames7;
		}

		public String getTempFiles7() {
			return tempFiles7;
		}

		public void setTempFiles7(String tempFiles7) {
			this.tempFiles7 = tempFiles7;
		}
	}
}
