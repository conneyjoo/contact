package com.jinshun.contact.service;

import com.jinshun.contact.constant.Environment;
import com.jinshun.contact.dao.FileRepository;
import com.jinshun.contact.entity.File;
import com.jinshun.contact.service.common.CommonService;
import com.jinshun.contact.util.SQLString;
import com.jinshun.contact.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class FileService extends CommonService {

    @Autowired
    private FileRepository fileRepository;

    public List<?> getFiles(File file) {
        SQLString sql = new SQLString("select f.* from t_file f where 1 = 1");
        sql.addCondition("and f.type = ?", file.getType());

        if (file.getType().equals(File.FILE_TYPE_BID)) {
            sql.addCondition("and f.bid_id = ?", file.getBidId());
        } else {
            sql.addCondition("and f.bid_success_id = ?", file.getBidSuccessId());
        }

        sql.append("order by id desc");

        return find(sql);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public void remove(File file) throws IOException {
        fileRepository.delete(file.getId());

        if (!StringUtils.isEmpty(file.getPath())) {
            FileUtils.forceDelete(new java.io.File(Environment.UPLOAD_PATH + file.getPath()));
        }
    }
}
