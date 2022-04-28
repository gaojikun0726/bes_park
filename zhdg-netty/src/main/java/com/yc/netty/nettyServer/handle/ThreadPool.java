package com.yc.netty.nettyServer.handle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @CkassName: ThreadPool
 * @Author: YangChao
 * @Date: 2020/5/8 12:18
 * @Descruotuib:
 * @Version: 1.0
 **/
public class ThreadPool {

    /**
     * corePoolSize:定义线程池---核心线程会一直存活，即使没有任务需要执行
     * maxPoolSize:最大线程数
     * keepAliveTime:线程空闲时间---当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
      */
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(100,
            200,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

}
