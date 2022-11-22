/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/22 23:31
 */
package com.yuntrans.gateway.core.plumbing;

import com.yuntrans.gateway.core.service.GatewayService;

public interface PlumbingService<TParams, TListener, TTask> extends GatewayService<TParams, TListener, TTask> {
    String getPosteriorApp();
}
