package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.service.impl.ClientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Time : 2021/8/23 18:50
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
public abstract class AbsAsyncAsrEngineHandlerService {

    @Autowired
    protected ClientManagement clientManagement;

    public abstract void createTask(String sid) throws IOException, InterruptedException;

}
