package com.efounder.JEnterprise.zhdg.util;

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

    // 定义线程池
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(100,
            200,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

}
