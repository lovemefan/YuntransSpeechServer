/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:36
 */
package com.yuntrans.common.balance;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.common.task.YuntransTaskContext;
import com.yuntrans.common.utils.EndpointType;

public interface BalanceService extends YuntransService<YuntransTaskContext, BalanceParams, BalanceListener, BalanceTask> {
    void registerBalancer(EndpointType paramEndpointType, Balancer paramBalancer) throws Exception;

    void deregisterBalancer(EndpointType paramEndpointType);
}
