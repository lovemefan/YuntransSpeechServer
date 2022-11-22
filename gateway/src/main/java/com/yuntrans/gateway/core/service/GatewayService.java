/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/22 23:32
 */
package com.yuntrans.gateway.core.service;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.gateway.core.context.GatewayTaskContext;

public interface GatewayService<TParams, TListener, TTask> extends YuntransService<GatewayTaskContext, TParams, TListener, TTask> {}

