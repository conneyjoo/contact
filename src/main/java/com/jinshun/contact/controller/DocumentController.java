package com.jinshun.contact.controller;

import com.jinshun.contact.auth.Access;
import com.jinshun.contact.controller.common.ControllerSupport;
import com.jinshun.contact.entity.Document;
import com.jinshun.contact.service.DocumentService;
import com.jinshun.contact.utils.model.DocumentQueryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController extends ControllerSupport {

    private Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @RequestMapping("saveOrUpdate")
    @Access()
    public @ResponseBody
    Message saveOrUpdateBid(Document document) {
        Message message = new Message();
        document.setCompany(getCurrentCompany());
        document.setUpdateTime(new Date());
        try {
            message.setData(documentService.saveOrUpdate(document));
            message.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @Access()
    @RequestMapping("remove")
    public @ResponseBody
    Message remove(Long id) {
        Message message = new Message();
        try {
            documentService.delete(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            message.setSuccess(false);
        }
        return message;
    }

    @RequestMapping("getById")
    @Access()
    public @ResponseBody
    Document getById(Long id) {
        if (id == null) {
            LOGGER.error("ID不能为空!!");
            return null;
        }
        return documentService.getById(id);
    }

    @Access()
    @RequestMapping("findDocuments")
    public @ResponseBody
    List<?> findDocuments(DocumentQueryModel model, Integer curPage, Integer pageSize, String sort, String direction) {
        model.setCompanyId(getCurrentCompany().getId());
        List<?> objs = documentService.queryDocument(model, curPage, pageSize, sort, direction);
        return objs;
    }


}
