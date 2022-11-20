package com.yuntrans.common.task;

import com.yuntrans.common.exception.YuntransException;

import java.util.concurrent.TimeUnit;

public interface YuntransTask<TResult> {
    TResult getResult() throws YuntransException;

    TResult getResult(long paramLong, TimeUnit paramTimeUnit) throws YuntransException;

    void cancel();
}
