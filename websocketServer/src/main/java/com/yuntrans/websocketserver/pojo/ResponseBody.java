/**
 * @Time : 2021/8/12 16:02
 * @Author : lovemefan
 * @Email : lovemefan@outlook.com
 */
package com.yuntrans.websocketserver.pojo;

import com.yuntrans.websocketserver.wsEnum.WsStatus;
import lombok.Data;

@Data
public class ResponseBody<T> {

    //status状态值：代表本次请求response的状态结果。
    private String status;
    private Integer statusCode;
    //response描述：对本次状态码的描述。
    private String message;
    //data数据：本次返回的数据。
    private T data;

}
