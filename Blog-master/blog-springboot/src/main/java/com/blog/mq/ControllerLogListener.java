package com.blog.mq;


import com.blog.config.RabbitMqConfig;
import com.blog.dao.mongo.ControllerLogDAO;
import com.blog.model.entity.ControllerLog;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mqpearh
 */
@Component
@RabbitListener(queues = RabbitMqConfig.CONTROLLER_LOG_QUEUE)
public class ControllerLogListener {

    @Autowired
    ControllerLogDAO controllerLogDAO;

    @RabbitHandler
    public void save(ControllerLog log) {
        controllerLogDAO.saveControllerLog(log);
    }
}
