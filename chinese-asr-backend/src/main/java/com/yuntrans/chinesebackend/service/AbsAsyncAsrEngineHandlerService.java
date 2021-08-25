package com.yuntrans.chinesebackend.service;

import com.yuntrans.chinesebackend.service.impl.ClientManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${asrbackend.num-thread-pool:5}")
    private static Integer numThreadPool;

    //开启异步处理线程池
    protected static final ExecutorService workers = Executors.newFixedThreadPool(numThreadPool);

    public abstract void createTask(String sid);

}
