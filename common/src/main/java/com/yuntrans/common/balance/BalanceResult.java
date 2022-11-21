/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:34
 */
package com.yuntrans.common.balance;

import com.yuntrans.common.utils.Endpoint;

import java.util.Map;
import java.util.Objects;

public class BalanceResult {
    private Map<String, Endpoint> endpoints;

    public BalanceResult(Map<String, Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public void setEndpoints(Map<String, Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BalanceResult))
            return false;
        BalanceResult other = (BalanceResult)o;
        if (!other.canEqual(this))
            return false;
        Object this$endpoints = (Map<String, Endpoint>)getEndpoints(), other$endpoints = (Map<String, Endpoint>)other.getEndpoints();
        return Objects.equals(this$endpoints, other$endpoints);
    }

    protected boolean canEqual(Object other) {
        return other instanceof BalanceResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $endpoints = (Map<String, Endpoint>)getEndpoints();
        return result * 59 + (($endpoints == null) ? 43 : $endpoints.hashCode());
    }

    public String toString() {
        return "BalanceResult(endpoints=" + getEndpoints() + ")";
    }

    public BalanceResult() {}

    public Map<String, Endpoint> getEndpoints() {
        return this.endpoints;
    }
}
