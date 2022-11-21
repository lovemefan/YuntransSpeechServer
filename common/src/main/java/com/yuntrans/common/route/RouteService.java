/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 18:58
 */
package com.yuntrans.common.route;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.common.task.YuntransTaskContext;

public interface RouteService extends YuntransService<YuntransTaskContext, RouteParams, RouteListener, RouteTask> {}
