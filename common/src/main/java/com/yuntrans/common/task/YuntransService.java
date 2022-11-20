/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:58
 */
package com.yuntrans.common.task;

import com.yuntrans.common.exception.YuntransException;

public interface YuntransService<TContext, TParams, TListener, TTask> {
    TTask newTask(TContext paramTContext, TParams paramTParams, TListener paramTListener) throws YuntransException;
}