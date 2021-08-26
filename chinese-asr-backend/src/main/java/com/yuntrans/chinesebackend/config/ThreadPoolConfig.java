package com.yuntrans.chinesebackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Time : 2021/8/25 15:59
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Value("${asrbackend.asrHandler.asrcorePoolSize:5}")
    private Integer asrHandlerCorePoolSize;

    @Value("${asrbackend.asrHandler.maxPoolSize:10}")
    private Integer asrHandlerMaxPoolSize;

    @Value("${asrbackend.asrSender.asrcorePoolSize:5}")
    private Integer asrSenderCorePoolSize;

    @Value("${asrbackend.asrSender.maxPoolSize:10}")
    private Integer asrSenderMaxPoolSize;



    //用来监听队列的线程池
    @Bean(name = "asrHandlerThreadPool")
    public ThreadPoolTaskExecutor asrHandlerThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,它是可以同时被执行的线程数量
        executor.setCorePoolSize(asrHandlerCorePoolSize);
        // 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
        executor.setMaxPoolSize(asrHandlerMaxPoolSize);
        // 设置缓冲队列容量,在执行任务之前用于保存任务
        executor.setQueueCapacity(50);
        // 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("asr-handler-Pool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //初始化
        executor.initialize();
        return executor;
    }

    //用来监听队列的线程池
    @Bean(name = "asrSenderThreadPool")
    public ThreadPoolTaskExecutor asrSenderThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数,它是可以同时被执行的线程数量
        executor.setCorePoolSize(asrSenderCorePoolSize);
        // 设置最大线程数,缓冲队列满了之后会申请超过核心线程数的线程
        executor.setMaxPoolSize(asrSenderMaxPoolSize);
        // 设置缓冲队列容量,在执行任务之前用于保存任务
        executor.setQueueCapacity(50);
        // 设置线程生存时间（秒）,当超过了核心线程出之外的线程在生存时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 设置线程名称前缀
        executor.setThreadNamePrefix("asr-Sender-Pool-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //初始化
        executor.initialize();
        return executor;
    }
}
