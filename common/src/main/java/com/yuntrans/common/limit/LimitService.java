/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:28
 */
package com.yuntrans.common.limit;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.common.task.YuntransTaskContext;

public interface LimitService extends YuntransService<YuntransTaskContext, LimitParams, LimitListener, LimitTask> {
    public static final long OUTDATE_THRESHOLD = 300000L;
}
