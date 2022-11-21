/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:39
 */
package com.yuntrans.common.utils;


public class NameValuePair<T> {
    private String name;

    private T value;

    public NameValuePair(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NameValuePair))
            return false;
        NameValuePair<?> other = (NameValuePair)o;
        if (!other.canEqual(this))
            return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
            return false;
        Object this$value = getValue(), other$value = other.getValue();
        return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value));
    }

    protected boolean canEqual(Object other) {
        return other instanceof NameValuePair;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $value = getValue();
        return result * 59 + (($value == null) ? 43 : $value.hashCode());
    }

    public String toString() {
        return "NameValuePair(name=" + getName() + ", value=" + getValue() + ")";
    }

    public NameValuePair() {}

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }
}
