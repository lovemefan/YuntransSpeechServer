# YuntransSpeechServer

![jdk](https://img.shields.io/badge/JDK-11-blue)
![spring boot](https://img.shields.io/badge/spring%20boot-V2.3.2-blue)
![spring cloud alibaba](https://img.shields.io/badge/spring%20cloud%20alibaba-2.2.6.RELEASE-blue)
[![license](https://img.shields.io/github/license/samuelcolvin/arq.svg)](https://github.com/samuelcolvin/arq/blob/master/LICENSE)

## 项目介绍
> 基于spring cloud alibaba 框架的语音识别（websocket）和语音合成微服务后端

使用的中间件
* redis (缓存和数据库)
* nacos (注册中心和配置中心)
* rocketmq (异步解耦，流量削峰)
* mysql (数据持久化)

### 系统架构
![系统架构](https://github.com/lovemefan/YuntransSpeechServer/raw/master/resources/Yuntrans_Architecture.png)