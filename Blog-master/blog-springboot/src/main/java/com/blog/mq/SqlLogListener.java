package com.blog.mq;


import com.blog.config.RabbitMqConfig;
import com.blog.dao.mongo.SqlLogDAO;
import com.blog.model.entity.SqlLog;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mqpearh
 */
@Component
@RabbitListener(queues = RabbitMqConfig.SQL_LOG_QUEUE)
public class SqlLogListener {

    @Autowired
    SqlLogDAO sqlLogDAO;

    @RabbitHandler
    public void log(SqlLog log) {
        sqlLogDAO.saveSqlLog(log);
    }
}
