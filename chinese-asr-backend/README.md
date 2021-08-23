# Asr backend
asr backend的任务为订阅某一主题（topic）的消息进行消费，并将消费结果结果推送至指定主题的消息队列。

其中，同一主题有不同的标签（tag），每一个tag对应一个独立的消费者，为了避免并发数量大时，创建线程数量过多，占用服务器资源，于是采用reactor线程模型。
## Reactor线程模型

Reacotr模型主要分为三个角色

* Reactor：把IO事件分配给对应的handler处理
* Acceptor：处理客户端连接事件 
* Handler：处理非阻塞的任务


根据Reactor的数量和处理资源的线程数量的不同，分为三类：

* 单Reactor单线程模型
* 单Reactor多线程模型
* 多Reactor多线程模型

## 单Reactor单线程模型
主要思想为reactor将不同的任务分给Acceptor处理连接使时间，
再由acceptor 将任务传递给handler使用线程池处理