/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 18:13
 */
package com.yuntrans.gateway.websocket;


import com.yuntrans.common.auth.AuthService;
import com.yuntrans.common.balance.BalanceService;
import com.yuntrans.common.limit.LimitServiceFactory;
import com.yuntrans.common.measure.MeasureService;
import com.yuntrans.common.route.RouteService;
import com.yuntrans.common.utils.ParamsLogger;
import com.yuntrans.common.utils.ParamsLoggerFactory;
import com.yuntrans.common.utils.YuntransThreadFactory;
import com.yuntrans.gateway.core.LoggerFacade;
import com.yuntrans.gateway.core.config.GatewayConfig;
import com.yuntrans.gateway.core.service.GatewayMetrics;
import com.yuntrans.gateway.netty.YuntransServerBase;
import com.yuntrans.gateway.netty.YuntransServerOptions;
import com.yuntrans.gateway.porcelain.PorcelainServiceManager;
import io.netty.channel.ChannelInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

@Service
@Slf4j
class WsServer extends YuntransServerBase {

    private static final String SERVER_NAME = "ws";

    private static final String LOGGER_NAME_CONN_OPEN = "conn-open";

    private static final String LOGGER_NAME_TASK = "task";

    private static final String LOGGER_NAME_MESSAGE = "message";

    private static final String LOGGER_NAME_CONTEXT = "context";

    private static final String LOGGER_NAME_ROUTE = "route";

    private static final String LOGGER_NAME_ACCESS = "access";

    private static final String LOGGER_NAME_SLA = "sla";

    final WsServerConfig config;

    final AuthService authService;

    final RouteService routeService;

    final LimitServiceFactory limitServiceFactory;

    final BalanceService balanceService;

    final MeasureService measureService;

    final PorcelainServiceManager serviceManager;

    final GatewayMetrics metrics;

    final ParamsLogger connOpenLogger;

    final ParamsLogger taskLogger;

    final ParamsLogger messageLogger;

    final ParamsLogger contextLogger;

    final ParamsLogger routeLogger;

    final ParamsLogger accessLogger;

    final ParamsLogger slaLogger;

    final LoggerFacade loggerFacade;

    final GatewayConfig gatewayConfig;

    final ScheduledThreadPoolExecutor delayer = new ScheduledThreadPoolExecutor(1, (ThreadFactory)new YuntransThreadFactory("delayer-"));

    @Value("${yuntrans.cloud.region}")
    String region;

    @Autowired
    public WsServer(WsServerConfig wsServerConfig, AuthService authService, RouteService routeService, LimitServiceFactory limitServiceFactory, BalanceService balanceService, MeasureService measureService, PorcelainServiceManager serviceManager, GatewayMetrics gatewayMetrics, GatewayConfig gatewayConfig, ParamsLoggerFactory paramsLoggerFactory, LoggerFacade loggerFacade) {
        this.config = wsServerConfig;
        this.authService = authService;
        this.routeService = routeService;
        this.limitServiceFactory = limitServiceFactory;
        this.balanceService = balanceService;
        this.measureService = measureService;
        this.serviceManager = serviceManager;
        this.gatewayConfig = gatewayConfig;
        this.metrics = gatewayMetrics;
        this.connOpenLogger = paramsLoggerFactory.getJsonLogger("conn-open");
        this.taskLogger = paramsLoggerFactory.getJsonLogger("task");
        this.messageLogger = paramsLoggerFactory.getJsonLogger("message");
        this.contextLogger = paramsLoggerFactory.getJsonLogger("context");
        this.routeLogger = paramsLoggerFactory.getJsonLogger("route");
        this.accessLogger = paramsLoggerFactory.getJsonLogger("access");
        this.slaLogger = paramsLoggerFactory.getJsonLogger("sla");
        this.loggerFacade = loggerFacade;
    }

    @PostConstruct
    public void init() throws Exception {
        log.info("Initializing bean '{}'", getClass().getSimpleName());
        YuntransServerOptions options = YuntransServerOptions.builder().name("ws").address(this.config.getAddress()).port(this.config.getPort()).workerNum(this.config.getWorkerNum()).channelInitializer((ChannelInitializer)new WsChannelInitializer(this)).build();
        start(options);
        log.info("Initialized bean '{}'", getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying bean '{}'", getClass().getSimpleName());
        stop();
        log.info("Destroyed bean '{}'", getClass().getSimpleName());
    }
}
