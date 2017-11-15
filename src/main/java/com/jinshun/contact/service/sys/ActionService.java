package com.jinshun.contact.service.sys;

import com.jinshun.contact.dao.sys.ActionRepository;
import com.jinshun.contact.dao.sys.MenuRepository;
import com.jinshun.contact.entity.Action;
import com.jinshun.contact.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public List<Action> getActions(Long roleId) {
        return actionRepository.getActions(roleId);
    }

    public Set<String> getActionSet(Long roleId) {
        return actionRepository.getActionSet(roleId);
    }
}
