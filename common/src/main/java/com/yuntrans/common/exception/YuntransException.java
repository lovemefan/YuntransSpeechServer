package com.yuntrans.common.exception;
import java.util.Optional;

import com.yuntrans.common.status.Status;
import com.yuntrans.common.status.YuntransStatus;
import org.slf4j.Logger;

public class YuntransException extends Exception {
    private static final String UNKNOWN = "UNKNOWN";

    private final int code;

    private final String name;

    private final String text;

    private final String serviceName;

    public YuntransException() {
        super("UNKNOWN");
        this.code = 0;
        this.name = "UNKNOWN";
        this.text = "UNKNOWN";
        this.serviceName = null;
    }

    public YuntransException(Status status) {
        super(status.getText(), status.getCause());
        this.code = status.getCode();
        this.name = status.getName();
        this.text = status.getText();
        this.serviceName = null;
    }

    public YuntransException(int code, String text) {
        super((text == null) ? UNKNOWN : text);
        this.code = code;
        if (text == null) {
            this.name = "UNKNOWN";
            this.serviceName = "UNKNOWN";
            this.text = text;
        } else {
            int index1 = text.indexOf(":");
            if (index1 < 0) {
                this.name = "UNKNOWN";
                this.serviceName = "UNKNOWN";
                this.text = text;
            } else {
                int index2 = text.indexOf(":", index1 + 1);
                if (index2 < 0) {
                    this.name = "UNKNOWN";
                    this.serviceName = "UNKNOWN";
                    this.text = text;
                } else {
                    this.name = text.substring(index1 + 1, index2);
                    this.serviceName = text.substring(0, index1);
                    this.text = text.substring(index2 + 1);
                }
            }
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getName(String serviceName) {
        if (this.serviceName == null)
            return serviceName + ":" + this.name;
        return this.serviceName + ":" + this.name;
    }

    public String getText(String serviceName) {
        String displayName = Optional.<String>ofNullable(this.serviceName).orElse(serviceName);
        if ("UNKNOWN".equals(displayName) || "UNKNOWN".equals(this.name))
            return this.text;
        return displayName + ":" + this.name + ":" + this.text;
    }

    public void log(Logger logger) {
        if (this.serviceName == null) {
            if (this.code >= YuntransStatus.SERVER_ERROR.getCode()) {
                logger.error("Got nls server error: code={}, name={}:", this.code, this.name, this);
            } else if (logger.isDebugEnabled()) {
                logger.debug("Got nls client error: code={}, name={}:", this.code, this.name, this);
            } else {
                logger.warn("Got nls client error: code={}, name={}, text={}", this.code, this.name, this.text);
            }
        } else if (this.code < YuntransStatus.SERVER_ERROR.getCode()) {
            logger.warn("Got backend error: code={}, text={}", this.code, getMessage());
        } else {
            logger.error("Got backend error: code={}, text={}", this.code, getMessage());
        }
    }

    public boolean isClientErr() {
        return (this.code < YuntransStatus.SERVER_ERROR.getCode() && this.code >= YuntransStatus.CLIENT_ERROR.getCode());
    }

    public boolean isServerErr() {
        return (this.code >= YuntransStatus.SERVER_ERROR.getCode());
    }
}
