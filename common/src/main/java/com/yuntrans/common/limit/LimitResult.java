/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 18:04
 */
package com.yuntrans.common.limit;


public class LimitResult {
    private int used;

    private int idle;

    public LimitResult(int used, int idle) {
        this.used = used;
        this.idle = idle;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public void setIdle(int idle) {
        this.idle = idle;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LimitResult))
            return false;
        LimitResult other = (LimitResult)o;
        return other.canEqual(this) && (getUsed() == other.getUsed() && (getIdle() == other.getIdle()));
    }

    protected boolean canEqual(Object other) {
        return other instanceof LimitResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + getUsed();
        return result * 59 + getIdle();
    }

    public String toString() {
        return "LimitResult(used=" + getUsed() + ", idle=" + getIdle() + ")";
    }

    public LimitResult() {}

    public int getUsed() {
        return this.used;
    }

    public int getIdle() {
        return this.idle;
    }
}
