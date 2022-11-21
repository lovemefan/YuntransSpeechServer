/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 15:57
 */
package com.yuntrans.gateway.core.config;

import com.yuntrans.common.exception.YuntransException;
import com.yuntrans.common.status.Status;

public enum GatewayStatus implements Status {
     NAMESPACE_NOT_FOUND(40010001, "NAMESPACE_NOT_FOUND", "Namespace not found!"),

     DIRECTIVE_NOT_SUPPORTED (40010002, "DIRECTIVE_NOT_SUPPORTED", "Directive is not supported!"),

     DIRECTIVE_INVALID (40010003, "DIRECTIVE_INVALID", "Directive is invalid!"),

     CLIENT_DISCONNECT (40010004, "CLIENT_DISCONNECT", "Client disconnected before task finished!"),

     TASK_STATE_ERROR (40010005, "TASK_STATE_ERROR", "The directive is not accepted in current state!"),

     MODEL_ACCESS_DENIED (40010006, "MODEL_ACCESS_DENIED", "User is not allowed to access this model!"),

     DIALOG_SILENT_SPEECH (40010007, "DIALOG_SILENT_SPEECH", "Silent speech!"),

     SHORT_URL_NOT_EXIST (40010008, "SHORT_URL_NOT_EXIST", "SHORT_URL_NOT_EXIST"),

     SHORT_URL_EXPIRED (40010009, "SHORT_URL_EXPIRED", "SHORT_URL_EXPIRED"),

     TASK_TIMEOUT (50010010, "TASK_TIMEOUT", "TASK_TIMEOUT"),

     REQUEST_NLP_FAILED (50220999, "REQUEST_NLP_FAILED", "REQUEST_NLP_FAILED"),

     REQUEST_ATTR_FAILED (50230999, "REQUEST_ATTR_FAILED", "REQUEST_ATTR_FAILED"),

     REQUEST_TRANS_UNFIYPOST_FAILED (50240999, "REQUEST_TRANS_UNFIYPOST_FAILED", "REQUEST_TRANS_UNFIYPOST_FAILED"),

     REQUEST_DEVICE_MANAGER_FAILED (50250999, "REQUEST_DEVICE_MANAGER_FAILED", "REQUEST_DEVICE_MANAGER_FAILED"),

     PARSE_JSON_FORMAT_ERROR (50260999, "PARSE_JSON_FORMAT_ERROR", "PARSE_JSON_FORMAT_ERROR"),

     REQUEST_REALTIME_FAILED (51040999, "REQUEST_REALTIME_FAILED", "REQUEST_REALTIME_FAILED");

     private final int code;
     private final String name;
     private String text;
     private Throwable cause;

     GatewayStatus(int code, String name, String text) {
          this.code = code;
          this.name = name;
          this.text = text;
     }

     GatewayStatus(int code, String name, String text, Throwable cause) {
          this.code = code;
          this.name = name;
          this.text = text;
          this.cause = cause;
     }

     public GatewayStatus modify(String text) {
          this.text=text;
          return this;
     }

     public GatewayStatus modify(String text, Throwable cause) {
          this.text = text;
          this.cause = cause;
          return this;
     }
     public YuntransException toException() {
          return new YuntransException(this);
     }

     @Override
     public int getCode() {
          return this.code;
     }

     @Override
     public String getName() {
          return this.name;
     }

     @Override
     public String getText() {
          return this.text;
     }

     @Override
     public Throwable getCause() {
          return this.cause;
     }
     @Override
     public String getStatusText(String serviceName) {
          return serviceName + ":" + this.name + ":" + this.text;
     }
}
