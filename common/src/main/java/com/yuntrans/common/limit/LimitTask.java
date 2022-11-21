/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:28
 */
package com.yuntrans.common.limit;

import com.yuntrans.common.task.YuntransTask;

public interface LimitTask extends YuntransTask<LimitResult> {
    void release();
}
