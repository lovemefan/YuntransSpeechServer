/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 19:44
 */
package com.yuntrans.common.measure;

import java.util.List;

public class MeasureParams {
    private final List<MeasureRecord> records;

    public MeasureParams(List<MeasureRecord> records) {
        this.records = records;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeasureParams))
            return false;
        MeasureParams other = (MeasureParams)o;
        if (!other.canEqual(this))
            return false;
        Object this$records = (List<MeasureRecord>)getRecords(), other$records = (List<MeasureRecord>)other.getRecords();
        return !((this$records == null) ? (other$records != null) : !this$records.equals(other$records));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MeasureParams;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $records = (List<MeasureRecord>)getRecords();
        return result * 59 + (($records == null) ? 43 : $records.hashCode());
    }

    public String toString() {
        return "MeasureParams(records=" + getRecords() + ")";
    }

    public List<MeasureRecord> getRecords() {
        return this.records;
    }
}
