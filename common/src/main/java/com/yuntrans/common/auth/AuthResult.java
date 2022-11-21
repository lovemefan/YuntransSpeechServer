/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/20 21:27
 */
package com.yuntrans.common.auth;


public class AuthResult {
    private String userId;

    private long expireTime;

    public AuthResult(String userId, long expireTime) {
        this.userId = userId;
        this.expireTime = expireTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthResult))
            return false;
        AuthResult other = (AuthResult)o;
        if (!other.canEqual(this))
            return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        return ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) ? false : (!(getExpireTime() != other.getExpireTime()));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuthResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        long expireTime = getExpireTime();
        return result * 59 + (int)(expireTime >>> 32L ^ expireTime);
    }

    public String toString() {
        return "AuthResult(userId=" + getUserId() + ", expireTime=" + getExpireTime() + ")";
    }

    public AuthResult() {}

    public String getUserId() {
        return this.userId;
    }

    public long getExpireTime() {
        return this.expireTime;
    }
}
