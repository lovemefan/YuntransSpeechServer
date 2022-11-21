/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:35
 */
package com.yuntrans.common.balance;

import com.yuntrans.common.utils.Endpoint;

import java.util.Map;

public class BalanceParams {
    private Map<String, Endpoint> endpoints;

    public BalanceParams(Map<String, Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public void setEndpoints(Map<String, Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BalanceParams))
            return false;
        BalanceParams other = (BalanceParams)o;
        if (!other.canEqual(this))
            return false;
        Object this$endpoints = (Map<String, Endpoint>)getEndpoints(), other$endpoints = (Map<String, Endpoint>)other.getEndpoints();
        return !((this$endpoints == null) ? (other$endpoints != null) : !this$endpoints.equals(other$endpoints));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BalanceParams;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $endpoints = (Map<String, Endpoint>)getEndpoints();
        return result * 59 + (($endpoints == null) ? 43 : $endpoints.hashCode());
    }

    public String toString() {
        return "BalanceParams(endpoints=" + getEndpoints() + ")";
    }

    public BalanceParams() {}

    public Map<String, Endpoint> getEndpoints() {
        return this.endpoints;
    }
}
