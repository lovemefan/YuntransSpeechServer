# WebsocketServer 服务
>用于与客户端建立websocket连接。将语音数据推送到消息队列当中的同时，接受语音识别返回的结果并推送给客户端

## 状态码
| 状态码 | 状态 | 描述               |
| ------ | ------------------ | ---- |
| 401     | UNAUTHORIZED | 未授权,  appKey 异常 |
| 406    | NOT_ACCEPTABLE | 参数错误，未被接受 |
| 10000  |SUCCESS| 连接成功           |
| 10001  |FAILED| 连接失败           |
| 10002  |UNAUTHORIZED| 未授权，非法签名 |
| 10003  | FORBIDDEN |禁止访问           |
|10004|TIMEOUT|连接超时|
| 10005  | ILLEGAL_CONNECTION |非法请求           |
| 10006 | FORMAT_ERROR |格式错误，必须为json格式 |