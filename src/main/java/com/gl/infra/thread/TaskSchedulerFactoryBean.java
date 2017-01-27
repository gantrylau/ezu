package com.gl.infra.thread;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.scheduling.TaskScheduler;

/**
 * @author gantrylau
 * @since 2016年04月02日
 */
public class TaskSchedulerFactoryBean implements FactoryBean<TaskScheduler> {

    @Override
    public TaskScheduler getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
