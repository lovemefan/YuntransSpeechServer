/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:00
 */
package com.yuntrans.common.route;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuntrans.common.utils.Endpoint;

import java.util.Map;
import java.util.Objects;

public class RouteResult {
    private Map<String, String> groups;

    private Map<String, Endpoint> endpoints;

    private Map<String, ObjectNode> tags;

    private ObjectNode config;

    public RouteResult(Map<String, String> groups, Map<String, Endpoint> endpoints, Map<String, ObjectNode> tags, ObjectNode config) {
        this.groups = groups;
        this.endpoints = endpoints;
        this.tags = tags;
        this.config = config;
    }

    public void setGroups(Map<String, String> groups) {
        this.groups = groups;
    }

    public void setEndpoints(Map<String, Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public void setTags(Map<String, ObjectNode> tags) {
        this.tags = tags;
    }

    public void setConfig(ObjectNode config) {
        this.config = config;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RouteResult))
            return false;
        RouteResult other = (RouteResult)o;
        if (!other.canEqual(this))
            return false;
        Object this$groups = (Map<String, String>)getGroups(), other$groups = (Map<String, String>)other.getGroups();
        if (!Objects.equals(this$groups, other$groups))
            return false;
        Object this$endpoints = (Map<String, Endpoint>)getEndpoints(), other$endpoints = (Map<String, Endpoint>)other.getEndpoints();
        if (!Objects.equals(this$endpoints, other$endpoints))
            return false;
        Object this$tags = (Map<String, ObjectNode>)getTags(), other$tags = (Map<String, ObjectNode>)other.getTags();
        if ((this$tags == null) ? (other$tags != null) : !this$tags.equals(other$tags))
            return false;
        Object this$config = getConfig(), other$config = other.getConfig();
        return Objects.equals(this$config, other$config);
    }

    protected boolean canEqual(Object other) {
        return other instanceof RouteResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $groups = (Map<String, String>)getGroups();
        result = result * 59 + (($groups == null) ? 43 : $groups.hashCode());
        Object $endpoints = (Map<String, Endpoint>)getEndpoints();
        result = result * 59 + (($endpoints == null) ? 43 : $endpoints.hashCode());
        Object $tags = (Map<String, ObjectNode>)getTags();
        result = result * 59 + (($tags == null) ? 43 : $tags.hashCode());
        Object $config = getConfig();
        return result * 59 + (($config == null) ? 43 : $config.hashCode());
    }

    public String toString() {
        return "RouteResult(groups=" + getGroups() + ", endpoints=" + getEndpoints() + ", tags=" + getTags() + ", config=" + getConfig() + ")";
    }

    public RouteResult() {}

    public Map<String, String> getGroups() {
        return this.groups;
    }

    public Map<String, Endpoint> getEndpoints() {
        return this.endpoints;
    }

    public Map<String, ObjectNode> getTags() {
        return this.tags;
    }

    public ObjectNode getConfig() {
        return this.config;
    }
}
