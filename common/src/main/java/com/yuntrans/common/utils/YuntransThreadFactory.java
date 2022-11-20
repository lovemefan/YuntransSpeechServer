package com.yuntrans.common.utils;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class YuntransThreadFactory implements ThreadFactory {
        private final String prefix;

        private final AtomicInteger number = new AtomicInteger(0);

        public YuntransThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        public Thread newThread(Runnable r) {
            int n = this.number.incrementAndGet();
            String threadName = this.prefix + n;
            Thread thread = new Thread(r);
            thread.setName(threadName);
            return thread;
        }

}
