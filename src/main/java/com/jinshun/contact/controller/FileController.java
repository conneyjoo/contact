package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.File;
import com.jinshun.contact.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController extends ControllerSupport {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Access()
    @RequestMapping("getFiles")
    public @ResponseBody List<?> getFiles(File file) {
        if (!StringUtils.isEmpty(file.getType()) && (!StringUtils.isEmpty(file.getBidId()) || !StringUtils.isEmpty(file.getBidSuccessId()))) {
            return fileService.getFiles(file);
        }

        return Collections.emptyList();
    }

    @Access()
    @RequestMapping("save")
    public @ResponseBody Message save(File file) {
        Message message = new Message();

        try {
            file.setCompanyId(getCurrentCompany().getId());
            message.setData(fileService.save(file));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
            if (e instanceof DataIntegrityViolationException) {
                message.setStatus(-1);
            }
        }

        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody Message remove(File file) {
        Message message = new Message();

        try {
            fileService.remove(file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message.setSuccess(false);
        }

        return message;
    }
}
