package com.jinshun.contact.controller.common;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.utils.DateUtils;
import com.jinshun.contact.utils.FileUtils;
import com.jinshun.contact.utils.enums.DateStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadSupport extends ControllerSupport {

	@RequestMapping(value = "/uploadTmp")
	public @ResponseBody Map<String, String> uploadTmp(@RequestParam(value = "file", required = false) MultipartFile mfile) throws IOException {
		String now = DateUtils.getDate(new Date(), DateStyle.YYYY_MM_DD);
		File dir = new File(Environment.UPLOAD_PATH + now);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(dir, UUID.randomUUID().toString() + "." + FileUtils.getFileSuffixName(mfile.getOriginalFilename()));
		mfile.transferTo(file);

		Map<String, String> map = new HashMap<String, String>();
		map.put("originalFilename", mfile.getOriginalFilename());
		map.put("tempFile", now + "/" + file.getName());
		return map;
	}
}
