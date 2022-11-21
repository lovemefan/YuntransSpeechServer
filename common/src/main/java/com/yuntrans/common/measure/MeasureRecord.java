/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:45
 */
package com.yuntrans.common.measure;

import java.util.Objects;

public class MeasureRecord {
    private String type;

    private long amount;

    public MeasureRecord(String type, long amount) {
        this.type = type;
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeasureRecord))
            return false;
        MeasureRecord other = (MeasureRecord)o;
        if (!other.canEqual(this))
            return false;
        Object this$type = getType(), other$type = other.getType();
        return (Objects.equals(this$type, other$type)) && (getAmount() == other.getAmount());
    }

    protected boolean canEqual(Object other) {
        return other instanceof MeasureRecord;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $type = getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        long $amount = getAmount();
        return result * 59 + (int)($amount >>> 32L ^ $amount);
    }

    public String toString() {
        return "MeasureRecord(type=" + getType() + ", amount=" + getAmount() + ")";
    }

    public MeasureRecord() {}

    public String getType() {
        return this.type;
    }

    public long getAmount() {
        return this.amount;
    }
}

