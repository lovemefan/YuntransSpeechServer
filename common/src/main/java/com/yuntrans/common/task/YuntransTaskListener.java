/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 21:08
 */
package com.yuntrans.common.task;

public interface YuntransTaskListener<TResult> {
    void onCompleted(TResult paramTResult);

    void onError(Throwable paramThrowable);
}
