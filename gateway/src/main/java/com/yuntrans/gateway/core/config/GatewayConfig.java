/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:45
 */
package com.yuntrans.gateway.core.config;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yuntrans.cloud.gateway.common")
public class GatewayConfig {
    public void setEnv(String env) {
        this.env = env;
    }

    public void setGeneralAlgoSvcMapping(Map<String, String> generalAlgoSvcMapping) {
        this.generalAlgoSvcMapping = generalAlgoSvcMapping;
    }

    public void setJupiterServiceMapping(Map<String, String> jupiterServiceMapping) {
        this.jupiterServiceMapping = jupiterServiceMapping;
    }

    public void setTtsRouteConfig(Map<String, String> ttsRouteConfig) {
        this.ttsRouteConfig = ttsRouteConfig;
    }

    public void setTtsRoutes(Map<String, RouteConfig> ttsRoutes) {
        this.ttsRoutes = ttsRoutes;
    }

    public void setTtsPrefixRouteConfig(Map<String, String> ttsPrefixRouteConfig) {
        this.ttsPrefixRouteConfig = ttsPrefixRouteConfig;
    }

    public void setRestSvcMapping(Map<String, RestServiceConfig> restSvcMapping) {
        this.restSvcMapping = restSvcMapping;
    }

    public void setCrosDomain(String crosDomain) {
        this.crosDomain = crosDomain;
    }

    public void setWmsPubAppkey(String wmsPubAppkey) {
        this.wmsPubAppkey = wmsPubAppkey;
    }

    public void setWmsPubToken(String wmsPubToken) {
        this.wmsPubToken = wmsPubToken;
    }

    public void setFlashRecognizerMaxBodySize(int flashRecognizerMaxBodySize) {
        this.flashRecognizerMaxBodySize = flashRecognizerMaxBodySize;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GatewayConfig))
            return false;
        GatewayConfig other = (GatewayConfig)o;
        if (!other.canEqual(this))
            return false;
        Object this$env = getEnv(), other$env = other.getEnv();
        if ((this$env == null) ? (other$env != null) : !this$env.equals(other$env))
            return false;
        Object this$generalAlgoSvcMapping = (Map<String, String>)getGeneralAlgoSvcMapping(), other$generalAlgoSvcMapping = (Map<String, String>)other.getGeneralAlgoSvcMapping();
        if (!Objects.equals(this$generalAlgoSvcMapping, other$generalAlgoSvcMapping))
            return false;
        Object this$jupiterServiceMapping = (Map<String, String>)getJupiterServiceMapping(), other$jupiterServiceMapping = (Map<String, String>)other.getJupiterServiceMapping();
        if ((this$jupiterServiceMapping == null) ? (other$jupiterServiceMapping != null) : !this$jupiterServiceMapping.equals(other$jupiterServiceMapping))
            return false;
        Object this$ttsRouteConfig = (Map<String, String>)getTtsRouteConfig(), other$ttsRouteConfig = (Map<String, String>)other.getTtsRouteConfig();
        if ((this$ttsRouteConfig == null) ? (other$ttsRouteConfig != null) : !this$ttsRouteConfig.equals(other$ttsRouteConfig))
            return false;
        Object this$ttsRoutes = (Map<String, RouteConfig>)getTtsRoutes(), other$ttsRoutes = (Map<String, RouteConfig>)other.getTtsRoutes();
        if ((this$ttsRoutes == null) ? (other$ttsRoutes != null) : !this$ttsRoutes.equals(other$ttsRoutes))
            return false;
        Object this$ttsPrefixRouteConfig = (Map<String, String>)getTtsPrefixRouteConfig(), other$ttsPrefixRouteConfig = (Map<String, String>)other.getTtsPrefixRouteConfig();
        if ((this$ttsPrefixRouteConfig == null) ? (other$ttsPrefixRouteConfig != null) : !this$ttsPrefixRouteConfig.equals(other$ttsPrefixRouteConfig))
            return false;
        Object this$restSvcMapping = (Map<String, RestServiceConfig>)getRestSvcMapping(), other$restSvcMapping = (Map<String, RestServiceConfig>)other.getRestSvcMapping();
        if ((this$restSvcMapping == null) ? (other$restSvcMapping != null) : !this$restSvcMapping.equals(other$restSvcMapping))
            return false;
        Object this$crosDomain = getCrosDomain(), other$crosDomain = other.getCrosDomain();
        if ((this$crosDomain == null) ? (other$crosDomain != null) : !this$crosDomain.equals(other$crosDomain))
            return false;
        Object this$wmsPubAppkey = getWmsPubAppkey(), other$wmsPubAppkey = other.getWmsPubAppkey();
        if ((this$wmsPubAppkey == null) ? (other$wmsPubAppkey != null) : !this$wmsPubAppkey.equals(other$wmsPubAppkey))
            return false;
        Object this$wmsPubToken = getWmsPubToken(), other$wmsPubToken = other.getWmsPubToken();
        if ((this$wmsPubToken == null) ? (other$wmsPubToken != null) : !this$wmsPubToken.equals(other$wmsPubToken))
            return false;
        if (getFlashRecognizerMaxBodySize() != other.getFlashRecognizerMaxBodySize())
            return false;
        Object this$meter = getMeter(), other$meter = other.getMeter();
        return !((this$meter == null) ? (other$meter != null) : !this$meter.equals(other$meter));
    }

    protected boolean canEqual(Object other) {
        return other instanceof GatewayConfig;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $env = getEnv();
        result = result * 59 + (($env == null) ? 43 : $env.hashCode());
        Object $generalAlgoSvcMapping = (Map<String, String>)getGeneralAlgoSvcMapping();
        result = result * 59 + (($generalAlgoSvcMapping == null) ? 43 : $generalAlgoSvcMapping.hashCode());
        Object $jupiterServiceMapping = (Map<String, String>)getJupiterServiceMapping();
        result = result * 59 + (($jupiterServiceMapping == null) ? 43 : $jupiterServiceMapping.hashCode());
        Object $ttsRouteConfig = (Map<String, String>)getTtsRouteConfig();
        result = result * 59 + (($ttsRouteConfig == null) ? 43 : $ttsRouteConfig.hashCode());
        Object $ttsRoutes = (Map<String, RouteConfig>)getTtsRoutes();
        result = result * 59 + (($ttsRoutes == null) ? 43 : $ttsRoutes.hashCode());
        Object $ttsPrefixRouteConfig = (Map<String, String>)getTtsPrefixRouteConfig();
        result = result * 59 + (($ttsPrefixRouteConfig == null) ? 43 : $ttsPrefixRouteConfig.hashCode());
        Object $restSvcMapping = (Map<String, RestServiceConfig>)getRestSvcMapping();
        result = result * 59 + (($restSvcMapping == null) ? 43 : $restSvcMapping.hashCode());
        Object $crosDomain = getCrosDomain();
        result = result * 59 + (($crosDomain == null) ? 43 : $crosDomain.hashCode());
        Object $wmsPubAppkey = getWmsPubAppkey();
        result = result * 59 + (($wmsPubAppkey == null) ? 43 : $wmsPubAppkey.hashCode());
        Object $wmsPubToken = getWmsPubToken();
        result = result * 59 + (($wmsPubToken == null) ? 43 : $wmsPubToken.hashCode());
        result = result * 59 + getFlashRecognizerMaxBodySize();
        Object $meter = getMeter();
        return result * 59 + (($meter == null) ? 43 : $meter.hashCode());
    }

    public String toString() {
        return "GatewayConfig(env=" + getEnv() + ", generalAlgoSvcMapping=" + getGeneralAlgoSvcMapping() + ", jupiterServiceMapping=" + getJupiterServiceMapping() + ", ttsRouteConfig=" + getTtsRouteConfig() + ", ttsRoutes=" + getTtsRoutes() + ", ttsPrefixRouteConfig=" + getTtsPrefixRouteConfig() + ", restSvcMapping=" + getRestSvcMapping() + ", crosDomain=" + getCrosDomain() + ", wmsPubAppkey=" + getWmsPubAppkey() + ", wmsPubToken=" + getWmsPubToken() + ", flashRecognizerMaxBodySize=" + getFlashRecognizerMaxBodySize() + ", meter=" + getMeter() + ")";
    }

    private static final Logger log = LoggerFactory.getLogger(GatewayConfig.class);

    private String env;

    public String getEnv() {
        return this.env;
    }

    private Map<String, String> generalAlgoSvcMapping = new LinkedHashMap<>();

    public Map<String, String> getGeneralAlgoSvcMapping() {
        return this.generalAlgoSvcMapping;
    }

    private Map<String, String> jupiterServiceMapping = new LinkedHashMap<>();

    public Map<String, String> getJupiterServiceMapping() {
        return this.jupiterServiceMapping;
    }

    private Map<String, String> ttsRouteConfig = new LinkedHashMap<>();

    public Map<String, String> getTtsRouteConfig() {
        return this.ttsRouteConfig;
    }

    private Map<String, RouteConfig> ttsRoutes = new LinkedHashMap<>();

    public Map<String, RouteConfig> getTtsRoutes() {
        return this.ttsRoutes;
    }

    private Map<String, String> ttsPrefixRouteConfig = new LinkedHashMap<>();

    private Map<String, RestServiceConfig> restSvcMapping;

    private String crosDomain;

    private String wmsPubAppkey;

    private String wmsPubToken;

    public Map<String, String> getTtsPrefixRouteConfig() {
        return this.ttsPrefixRouteConfig;
    }

    public Map<String, RestServiceConfig> getRestSvcMapping() {
        return this.restSvcMapping;
    }

    public String getCrosDomain() {
        return this.crosDomain;
    }

    public String getWmsPubAppkey() {
        return this.wmsPubAppkey;
    }

    public String getWmsPubToken() {
        return this.wmsPubToken;
    }

    private int flashRecognizerMaxBodySize = 100;

    public int getFlashRecognizerMaxBodySize() {
        return this.flashRecognizerMaxBodySize;
    }

    private Meter meter = new Meter();

    public Meter getMeter() {
        return this.meter;
    }

    public static class RouteConfig {
        public String toString() {
            return "GatewayConfig.RouteConfig(endpoints=" + getEndpoints() + ")";
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $endpoints = (Map<String, GatewayConfig.EndpointConfig>)getEndpoints();
            return result * 59 + (($endpoints == null) ? 43 : $endpoints.hashCode());
        }

        protected boolean canEqual(Object other) {
            return other instanceof RouteConfig;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof RouteConfig))
                return false;
            RouteConfig other = (RouteConfig)o;
            if (!other.canEqual(this))
                return false;
            Object this$endpoints = (Map<String, GatewayConfig.EndpointConfig>)getEndpoints(), other$endpoints = (Map<String, GatewayConfig.EndpointConfig>)other.getEndpoints();
            return !((this$endpoints == null) ? (other$endpoints != null) : !this$endpoints.equals(other$endpoints));
        }

        public void setEndpoints(Map<String, GatewayConfig.EndpointConfig> endpoints) {
            this.endpoints = endpoints;
        }

        private Map<String, GatewayConfig.EndpointConfig> endpoints = new LinkedHashMap<>();

        public Map<String, GatewayConfig.EndpointConfig> getEndpoints() {
            return this.endpoints;
        }
    }

    public static class EndpointConfig {
        private String endpoint;

        private double weight;

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof EndpointConfig))
                return false;
            EndpointConfig other = (EndpointConfig)o;
            if (!other.canEqual(this))
                return false;
            Object this$endpoint = getEndpoint(), other$endpoint = other.getEndpoint();
            return ((this$endpoint == null) ? (other$endpoint != null) : !this$endpoint.equals(other$endpoint)) ? false : (!(Double.compare(getWeight(), other.getWeight()) != 0));
        }

        protected boolean canEqual(Object other) {
            return other instanceof EndpointConfig;
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $endpoint = getEndpoint();
            result = result * 59 + (($endpoint == null) ? 43 : $endpoint.hashCode());
            long $weight = Double.doubleToLongBits(getWeight());
            return result * 59 + (int)($weight >>> 32L ^ $weight);
        }

        public String toString() {
            return "GatewayConfig.EndpointConfig(endpoint=" + getEndpoint() + ", weight=" + getWeight() + ")";
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public double getWeight() {
            return this.weight;
        }
    }

    public static class Meter {
        public String toString() {
            return "GatewayConfig.Meter(latency=" + getLatency() + ")";
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $latency = getLatency();
            return result * 59 + (($latency == null) ? 43 : $latency.hashCode());
        }

        protected boolean canEqual(Object other) {
            return other instanceof Meter;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Meter))
                return false;
            Meter other = (Meter)o;
            if (!other.canEqual(this))
                return false;
            Object this$latency = getLatency(), other$latency = other.getLatency();
            return !((this$latency == null) ? (other$latency != null) : !this$latency.equals(other$latency));
        }

        public void setLatency(Latency latency) {
            this.latency = latency;
        }

        private Latency latency = new Latency();

        public Latency getLatency() {
            return this.latency;
        }

        public static class Latency {
            public void setHistogramEnabled(boolean histogramEnabled) {
                this.histogramEnabled = histogramEnabled;
            }

            public void setTagsEnabled(boolean tagsEnabled) {
                this.tagsEnabled = tagsEnabled;
            }

            public boolean equals(Object o) {
                if (o == this)
                    return true;
                if (!(o instanceof Latency))
                    return false;
                Latency other = (Latency)o;
                return !other.canEqual(this) ? false : ((isHistogramEnabled() != other.isHistogramEnabled()) ? false : (!(isTagsEnabled() != other.isTagsEnabled())));
            }

            protected boolean canEqual(Object other) {
                return other instanceof Latency;
            }

            public int hashCode() {
                int PRIME = 59;
                int result = 1;
                result = result * 59 + (isHistogramEnabled() ? 79 : 97);
                return result * 59 + (isTagsEnabled() ? 79 : 97);
            }

            public String toString() {
                return "GatewayConfig.Meter.Latency(histogramEnabled=" + isHistogramEnabled() + ", tagsEnabled=" + isTagsEnabled() + ")";
            }

            private boolean histogramEnabled = false;

            public boolean isHistogramEnabled() {
                return this.histogramEnabled;
            }

            private boolean tagsEnabled = false;

            public boolean isTagsEnabled() {
                return this.tagsEnabled;
            }
        }
    }

    @PostConstruct
    public void init() {
        log.info("Loaded gateway config: {}", this);
        log.info("Initialized bean '{}'", getClass().getSimpleName());
    }
}
