/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:27
 */
package com.yuntrans.gateway.porcelain;

import com.yuntrans.common.task.YuntransService;
import com.yuntrans.gateway.core.context.GatewayTaskContext;

public abstract class PorcelainServiceBase implements YuntransService<GatewayTaskContext, Void, PorcelainTaskListener, PorcelainTask> {
    public abstract PorcelainTask newTask(GatewayTaskContext paramGatewayTaskContext, PorcelainTaskListener paramPorcelainTaskListener);

    public PorcelainTask newTask(GatewayTaskContext gatewayTaskContext, Void aVoid, PorcelainTaskListener porcelainTaskListener) {
        throw new Error("Method not implemented!");
    }
}
