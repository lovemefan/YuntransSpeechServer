/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:42
 */
package com.yuntrans.gateway.core.service;

import com.yuntrans.common.task.YuntransTask;

public interface GatewayTask<TResult> extends YuntransTask<TResult> {
    String[] getPosteriorApps();

    String getFrontendApp();
}
