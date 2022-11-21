/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 20:52
 */
package com.yuntrans.common.auth;


import java.util.Objects;

public class AuthParams {
    private String token;

    public AuthParams(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthParams))
            return false;
        AuthParams other = (AuthParams)o;
        if (!other.canEqual(this))
            return false;
        Object this$token = getToken(), other$token = other.getToken();
        return Objects.equals(this$token, other$token);
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuthParams;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String token = getToken();
        return result * 59 + ((token == null) ? 43 : token.hashCode());
    }

    public String toString() {
        return "AuthParams(token=" + getToken() + ")";
    }

    public AuthParams() {}

    public String getToken() {
        return this.token;
    }
}
