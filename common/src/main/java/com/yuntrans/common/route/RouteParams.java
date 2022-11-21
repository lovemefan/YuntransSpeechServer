/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:04
 */
package com.yuntrans.common.route;


import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import java.util.Objects;

public class RouteParams {
    private Map<String, String> groups;

    private ObjectNode clientContext;

    public RouteParams(Map<String, String> groups, ObjectNode clientContext) {
        this.groups = groups;
        this.clientContext = clientContext;
    }

    public void setGroups(Map<String, String> groups) {
        this.groups = groups;
    }

    public void setClientContext(ObjectNode clientContext) {
        this.clientContext = clientContext;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RouteParams))
            return false;
        RouteParams other = (RouteParams)o;
        if (!other.canEqual(this))
            return false;
        Object this$groups = (Map<String, String>)getGroups(), other$groups = (Map<String, String>)other.getGroups();
        if (!Objects.equals(this$groups, other$groups))
            return false;
        Object this$clientContext = getClientContext(), other$clientContext = other.getClientContext();
        return Objects.equals(this$clientContext, other$clientContext);
    }

    protected boolean canEqual(Object other) {
        return other instanceof RouteParams;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $groups = (Map<String, String>)getGroups();
        result = result * 59 + (($groups == null) ? 43 : $groups.hashCode());
        Object $clientContext = getClientContext();
        return result * 59 + (($clientContext == null) ? 43 : $clientContext.hashCode());
    }

    public String toString() {
        return "RouteParams(groups=" + getGroups() + ", clientContext=" + getClientContext() + ")";
    }

    public RouteParams() {}

    public Map<String, String> getGroups() {
        return this.groups;
    }

    public ObjectNode getClientContext() {
        return this.clientContext;
    }

    public RouteParams(Map<String, String> groups) {
        this.groups = groups;
    }
}
