/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:32
 */
package com.yuntrans.gateway.core.service;


import com.yuntrans.common.task.YuntransTaskBase;
import com.yuntrans.common.task.YuntransTaskListener;
import com.yuntrans.gateway.core.context.GatewayTaskContext;

public abstract class GatewayTaskBase<TResult> extends YuntransTaskBase<TResult> implements GatewayTask<TResult> {
    private static final String[] EMPTY_ARRAY = new String[0];

    protected final GatewayTaskContext gatewayContext;

    protected GatewayTaskBase(GatewayTaskContext gatewayTaskContext, YuntransTaskListener<TResult> listener) {
        super(gatewayTaskContext.getYuntransTaskContext(), listener);
        this.gatewayContext = gatewayTaskContext;
    }

    public String[] getPosteriorApps() {
        return EMPTY_ARRAY;
    }

    public String getFrontendApp() {
        return "";
    }
}
