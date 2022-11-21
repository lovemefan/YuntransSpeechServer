/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:43
 */
package com.yuntrans.gateway.core.service;


import com.yuntrans.gateway.core.config.GatewayConfig;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayPrometheusService {
    private static final Logger log = LoggerFactory.getLogger(GatewayPrometheusService.class);

    private final GatewayConfig config;

    private final MeterRegistry registry;

    private final ConcurrentHashMap<String, Counter> counters = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, DistributionSummary> distributions = new ConcurrentHashMap<>();

    @Autowired
    public GatewayPrometheusService(GatewayConfig gatewayConfig, MeterRegistry meterRegistry) {
        this.config = gatewayConfig;
        this.registry = meterRegistry;
    }

    @PostConstruct
    private void init() {
        log.info("Initialized bean '{}'", getClass().getSimpleName());
    }

    public void incTaskCounter(String namespace, String appkey, String userId, int status) {
        String counterKey = "nls_gateway_tasks_" + ensure(namespace) + "_" + ensure(appkey) + "_" + ensure(userId) + "_" + status;
        ((Counter)this.counters.computeIfAbsent(counterKey, key -> {
            List<Tag> tags = new ArrayList<>();
            tags.add(Tag.of("namespace", ensure(namespace)));
            tags.add(Tag.of("appkey", ensure(appkey)));
            tags.add(Tag.of("user_id", ensure(userId)));
            tags.add(Tag.of("status", String.valueOf(status)));
            return this.registry.counter("nls_gateway_tasks", tags);
        })).increment();
    }

    public void recordLatency(String namespace, String appkey, String userId, long latency) {
        if (this.config.getMeter().getLatency().isTagsEnabled()) {
            String name = "nls_gateway_latency_" + ensure(namespace) + "_" + ensure(appkey) + "_" + ensure(userId);
            ((DistributionSummary)this.distributions.computeIfAbsent(name, key -> {
                List<Tag> tags = new ArrayList<>();
                tags.add(Tag.of("namespace", ensure(namespace)));
                tags.add(Tag.of("appkey", ensure(appkey)));
                tags.add(Tag.of("user_id", ensure(userId)));
                DistributionSummary.Builder builder = DistributionSummary.builder("nls_gateway_latency");
                if (this.config.getMeter().getLatency().isHistogramEnabled())
                    builder.serviceLevelObjectives(100.0D, 200.0D, 300.0D, 400.0D, 500.0D, 1000.0D);
                return builder.tags(tags).register(this.registry);
            })).record(latency);
        } else {
            String name = "nls_gateway_latency";
            ((DistributionSummary)this.distributions.computeIfAbsent(name, key -> {
                DistributionSummary.Builder builder = DistributionSummary.builder("nls_gateway_latency");
                if (this.config.getMeter().getLatency().isHistogramEnabled())
                    builder.serviceLevelObjectives(100.0D, 200.0D, 300.0D, 400.0D, 500.0D, 1000.0D);
                return builder.register(this.registry);
            })).record(latency);
        }
    }

    private String ensure(String str) {
        return (str == null) ? "" : str;
    }
}
