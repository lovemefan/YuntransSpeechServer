/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 20:28
 */
package com.yuntrans.gateway.porcelain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.annotation.Resource;

import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.task.YuntransTaskContext;
import com.yuntrans.gateway.core.config.GatewayConfig;
import com.yuntrans.gateway.core.config.GatewayStatus;
import com.yuntrans.gateway.core.context.GatewayTaskContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PorcelainServiceManager {


    public static final String GENERAL_ALGO_NAME = "general_algo";

    public static final String JUPITER_SERVICE_NAME = "jupiter";

    @Resource
    GatewayConfig gatewayConfig;

    private final HashMap<String, PorcelainServiceBase> services = new LinkedHashMap<>();

    public void registerService(String namespace, PorcelainServiceBase service) throws Exception {
        log.info("Registering porcelain service: namespace={}, serviceType={}", namespace, service
                .getClass().getSimpleName());
        PorcelainServiceBase svc = this.services.getOrDefault(namespace, null);
        if (svc != null) {
            String msg = String.format("Porcelain service already registered: namespace=%s, existing=%s, provided=%s", namespace, svc

                    .getClass().getSimpleName(), service.getClass().getSimpleName());
            throw new Exception(msg);
        }
        this.services.put(namespace, service);
        log.info("Registered porcelain service");
    }

    public void deregisterService(String namespace, PorcelainServiceBase service) {
        log.info("Deregistering porcelain service: namespace={}", namespace);
        PorcelainServiceBase svc = this.services.getOrDefault(namespace, null);
        if (svc == null) {
            log.warn("Failed to deregister porcelain service, service not found: namespace={}", namespace);
            return;
        }
        if (svc != service) {
            log.warn("Failed to deregister porcelain service, service not match: namespace={}, expected={}, actual={}", namespace, svc
                    .getClass().getSimpleName(), service.getClass().getSimpleName());
            return;
        }
        this.services.remove(namespace);
        log.info("Deregistered porcelain service: serviceType={}", service.getClass().getSimpleName());
    }

    public PorcelainTask newTask(GatewayTaskContext context, PorcelainTaskListener listener) throws YuntransException {
        YuntransTaskContext ctx = context.getYuntransTaskContext();
        log.debug("Creating porcelain task for namespace '{}' ...", ctx.getNamespace());
        PorcelainServiceBase service = this.services.get(ctx.getNamespace());
        if (service == null &&
                this.gatewayConfig.getGeneralAlgoSvcMapping().containsKey(ctx.getNamespace()))
            service = this.services.get("general_algo");
        if (service == null &&
                this.gatewayConfig.getJupiterServiceMapping().containsKey(ctx.getNamespace()))
            service = this.services.get("jupiter");
        if (service == null) {
            String msg = "Porcelain service not found for namespace '" + ctx.getNamespace() + "'";
            throw GatewayStatus.NAMESPACE_NOT_FOUND.modify(msg).toException();
        }
        return service.newTask(context, listener);
    }
}
