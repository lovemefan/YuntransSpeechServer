/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:07
 */
package com.yuntrans.gateway.core.context;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.task.YuntransTaskContext;
import com.yuntrans.common.utils.Endpoint;

import java.util.Map;
import java.util.Objects;

public class GatewayTaskContext {
    private ConnectionContext connectionContext;

    private YuntransTaskContext yuntransTaskContext;

    private ObjectNode clientContext;

    private String frontendApp;

    private Map<String, String> groups;

    private Map<String, Endpoint> routedEndpoints;

    private Map<String, Endpoint> balancedEndpoints;

    private Map<String, ObjectNode> tags;

    public GatewayTaskContext(ConnectionContext connectionContext, YuntransTaskContext yuntransTaskContext, ObjectNode clientContext, String frontendApp, Map<String, String> groups, Map<String, Endpoint> routedEndpoints, Map<String, Endpoint> balancedEndpoints, Map<String, ObjectNode> tags) {
        this.connectionContext = connectionContext;
        this.yuntransTaskContext = yuntransTaskContext;
        this.clientContext = clientContext;
        this.frontendApp = frontendApp;
        this.groups = groups;
        this.routedEndpoints = routedEndpoints;
        this.balancedEndpoints = balancedEndpoints;
        this.tags = tags;
    }

    public static GatewayTaskContextBuilder builder() {
        return new GatewayTaskContextBuilder();
    }

    public static class GatewayTaskContextBuilder {
        private ConnectionContext connectionContext;

        private YuntransTaskContext yuntransTaskContext;

        private ObjectNode clientContext;

        private String frontendApp;

        private Map<String, String> groups;

        private Map<String, Endpoint> routedEndpoints;

        private Map<String, Endpoint> balancedEndpoints;

        private Map<String, ObjectNode> tags;

        public GatewayTaskContextBuilder connectionContext(ConnectionContext connectionContext) {
            this.connectionContext = connectionContext;
            return this;
        }

        public GatewayTaskContextBuilder yuntransTaskContext(YuntransTaskContext yuntransTaskContext) {
            this.yuntransTaskContext = yuntransTaskContext;
            return this;
        }

        public GatewayTaskContextBuilder clientContext(ObjectNode clientContext) {
            this.clientContext = clientContext;
            return this;
        }

        public GatewayTaskContextBuilder frontendApp(String frontendApp) {
            this.frontendApp = frontendApp;
            return this;
        }

        public GatewayTaskContextBuilder groups(Map<String, String> groups) {
            this.groups = groups;
            return this;
        }

        public GatewayTaskContextBuilder routedEndpoints(Map<String, Endpoint> routedEndpoints) {
            this.routedEndpoints = routedEndpoints;
            return this;
        }

        public GatewayTaskContextBuilder balancedEndpoints(Map<String, Endpoint> balancedEndpoints) {
            this.balancedEndpoints = balancedEndpoints;
            return this;
        }

        public GatewayTaskContextBuilder tags(Map<String, ObjectNode> tags) {
            this.tags = tags;
            return this;
        }

        public GatewayTaskContext build() {
            return new GatewayTaskContext(this.connectionContext, this.yuntransTaskContext, this.clientContext, this.frontendApp, this.groups, this.routedEndpoints, this.balancedEndpoints, this.tags);
        }

        public String toString() {
            return "GatewayTaskContext.GatewayTaskContextBuilder(connectionContext=" + this.connectionContext + ", yuntransTaskContext=" + this.yuntransTaskContext + ", clientContext=" + this.clientContext + ", frontendApp=" + this.frontendApp + ", groups=" + this.groups + ", routedEndpoints=" + this.routedEndpoints + ", balancedEndpoints=" + this.balancedEndpoints + ", tags=" + this.tags + ")";
        }

        public GatewayTaskContextBuilder gatewayTaskContext(YuntransTaskContext yuntransTaskContext) {
            this.yuntransTaskContext = yuntransTaskContext;
            return this;
        }
    }

    public void setConnectionContext(ConnectionContext connectionContext) {
        this.connectionContext = connectionContext;
    }

    public void setYuntransTaskContext(YuntransTaskContext yuntransTaskContext) {
        this.yuntransTaskContext = yuntransTaskContext;
    }

    public void setClientContext(ObjectNode clientContext) {
        this.clientContext = clientContext;
    }

    public void setFrontendApp(String frontendApp) {
        this.frontendApp = frontendApp;
    }

    public void setGroups(Map<String, String> groups) {
        this.groups = groups;
    }

    public void setRoutedEndpoints(Map<String, Endpoint> routedEndpoints) {
        this.routedEndpoints = routedEndpoints;
    }

    public void setBalancedEndpoints(Map<String, Endpoint> balancedEndpoints) {
        this.balancedEndpoints = balancedEndpoints;
    }

    public void setTags(Map<String, ObjectNode> tags) {
        this.tags = tags;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GatewayTaskContext))
            return false;
        GatewayTaskContext other = (GatewayTaskContext)o;
        if (!other.canEqual(this))
            return false;
        Object this$connectionContext = getConnectionContext(), other$connectionContext = other.getConnectionContext();
        if (!Objects.equals(this$connectionContext, other$connectionContext))
            return false;
        Object this$yuntransTaskContext = getYuntransTaskContext(), other$yuntransTaskContext = other.getYuntransTaskContext();
        if (!Objects.equals(this$yuntransTaskContext, other$yuntransTaskContext))
            return false;
        Object this$clientContext = getClientContext(), other$clientContext = other.getClientContext();
        if (!Objects.equals(this$clientContext, other$clientContext))
            return false;
        Object this$frontendApp = getFrontendApp(), other$frontendApp = other.getFrontendApp();
        if (!Objects.equals(this$frontendApp, other$frontendApp))
            return false;
        Object this$groups = (Map<String, String>)getGroups(), other$groups = (Map<String, String>)other.getGroups();
        if (!Objects.equals(this$groups, other$groups))
            return false;
        Object this$routedEndpoints = (Map<String, Endpoint>)getRoutedEndpoints(), other$routedEndpoints = (Map<String, Endpoint>)other.getRoutedEndpoints();
        if (!Objects.equals(this$routedEndpoints, other$routedEndpoints))
            return false;
        Object this$balancedEndpoints = (Map<String, Endpoint>)getBalancedEndpoints(), other$balancedEndpoints = (Map<String, Endpoint>)other.getBalancedEndpoints();
        if (!Objects.equals(this$balancedEndpoints, other$balancedEndpoints))
            return false;
        Object this$tags = (Map<String, ObjectNode>)getTags(), other$tags = (Map<String, ObjectNode>)other.getTags();
        return Objects.equals(this$tags, other$tags);
    }

    protected boolean canEqual(Object other) {
        return other instanceof GatewayTaskContext;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $connectionContext = getConnectionContext();
        result = result * 59 + (($connectionContext == null) ? 43 : $connectionContext.hashCode());
        Object $yuntransTaskContext = getYuntransTaskContext();
        result = result * 59 + (($yuntransTaskContext == null) ? 43 : $yuntransTaskContext.hashCode());
        Object $clientContext = getClientContext();
        result = result * 59 + (($clientContext == null) ? 43 : $clientContext.hashCode());
        Object $frontendApp = getFrontendApp();
        result = result * 59 + (($frontendApp == null) ? 43 : $frontendApp.hashCode());
        Object $groups = (Map<String, String>)getGroups();
        result = result * 59 + (($groups == null) ? 43 : $groups.hashCode());
        Object $routedEndpoints = (Map<String, Endpoint>)getRoutedEndpoints();
        result = result * 59 + (($routedEndpoints == null) ? 43 : $routedEndpoints.hashCode());
        Object $balancedEndpoints = (Map<String, Endpoint>)getBalancedEndpoints();
        result = result * 59 + (($balancedEndpoints == null) ? 43 : $balancedEndpoints.hashCode());
        Object $tags = (Map<String, ObjectNode>)getTags();
        return result * 59 + (($tags == null) ? 43 : $tags.hashCode());
    }

    public String toString() {
        return "GatewayTaskContext(connectionContext=" + getConnectionContext() + ", yuntransTaskContext=" + getYuntransTaskContext() + ", clientContext=" + getClientContext() + ", frontendApp=" + getFrontendApp() + ", groups=" + getGroups() + ", routedEndpoints=" + getRoutedEndpoints() + ", balancedEndpoints=" + getBalancedEndpoints() + ", tags=" + getTags() + ")";
    }

    public GatewayTaskContext() {}

    public ConnectionContext getConnectionContext() {
        return this.connectionContext;
    }

    public YuntransTaskContext getYuntransTaskContext() {
        return this.yuntransTaskContext;
    }

    public ObjectNode getClientContext() {
        return this.clientContext;
    }

    public String getFrontendApp() {
        return this.frontendApp;
    }

    public Map<String, String> getGroups() {
        return this.groups;
    }

    public Map<String, Endpoint> getRoutedEndpoints() {
        return this.routedEndpoints;
    }

    public Map<String, Endpoint> getBalancedEndpoints() {
        return this.balancedEndpoints;
    }

    public Map<String, ObjectNode> getTags() {
        return this.tags;
    }
}
