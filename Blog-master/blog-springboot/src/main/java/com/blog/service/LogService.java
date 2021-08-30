package com.blog.service;

import com.blog.dao.mongo.ControllerLogDAO;
import com.blog.dao.mongo.SqlLogDAO;
import com.blog.model.entity.ControllerLog;
import com.blog.model.entity.SqlLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * LogService
 *
 * @author zzx
 * @date 2021.5.29 17:57
 */
@Service
public class LogService {

    @Autowired
    ControllerLogDAO controllerLogDAO;

    @Autowired
    SqlLogDAO sqlLogDAO;

    public List<ControllerLog> findControllerNewestLog(Date left, Date right, Integer size) {
        return controllerLogDAO.findNewestLog(left, right, size);
    }

    public List<SqlLog> findSqlNewestLog(Date left, Date right, Integer size) {
        return sqlLogDAO.findNewestLog(left, right, size);
    }
}
