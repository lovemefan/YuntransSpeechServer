# YuntransSpeechServer

![jdk](https://img.shields.io/badge/JDK-11-blue)
![spring boot](https://img.shields.io/badge/spring%20boot-V2.3.2-blue)
![spring cloud alibaba](https://img.shields.io/badge/spring%20cloud%20alibaba-2.2.6.RELEASE-blue)
[![license](https://img.shields.io/github/license/samuelcolvin/arq.svg)](https://github.com/samuelcolvin/arq/blob/master/LICENSE)

## 项目介绍(开发中)
> 基于spring cloud alibaba 框架的语音识别（websocket）和语音合成微服务后端

使用的中间件
* redis (缓存和数据库)
* nacos (注册中心和配置中心)
* rocketmq (异步解耦，流量削峰)
* mysql (数据持久化)

### 系统架构
![系统架构](./docs/yuntrans-speech.png)
### 项目服务组件清单
|  应用名 | 描述  | 是否可选  |
| ------------ | ------------ | ------------ |
| yuntrans-cloud-common  |  通用模块，提供一些基础类  |  必选 |
| yuntrans-cloud-gateway  |语音网关服务，实现负载均衡  |  必选 |
| yuntrans-cloud-sentinel  | 流量控制、熔断降级、系统负载保护  | 必选  |
| yuntrans-clond-nacos|服务注册中心与配置中心|必选|
| yuntrans-clond-dubbo|高性能RPC框架，用于提供grpc接口和使用语音grpc调用|可选，其他服务可依赖|
| yuntrans-clond-portal|认证与授权服务|必选|
| yuntrans-cloud-prometheus|服务监控服务|必选|
| yuntrans-cloud-cadvisor|容器监控服务|可选|
| yuntrans-cloud-elasticsearch|搜索与数据分析引擎|可选|
| yuntrans-cloud-mysql|关系数据库|必选|
| yuntrans-cloud-redis|缓存服务|必选|
| yuntrans-cloud-fluentd|日志收集、查询、可视化服务|必选|
| yuntrans-cloud-mongodb|非关系数据库|可选（待定）|
| yuntrans-cloud-asr  | 语音识别引擎  | 必选  |
| yuntrans-cloud-tts  | 语音合成引擎  | 必选  |
| yuntrans-cloud-realtime-asr  | 实时语音转写服务  | 必选  |
| yuntrans-cloud-file-asr  | 文件语音转写服务  | 必选  |
| yuntrans-cloud-file-tts  | 文件语音合成服务  | 必选  |
| yuntrans-cloud-realtime-tts  | 实时语音合成服务  | 必选  |
| yuntrans-clond-asr-pre-vad|语音端点检测服务，实时语音转写、文件转写依赖此服务|必选|
| yuntrans-clond-asr-pre-se|语音识别前处理，语音增强|必选|
| yuntrans-clond-asr-post-punc|语音识别后处理，标点恢复服务|必选|
| yuntrans-clond-asr-post-correct|语音识别后处理，文本纠错服务|必选|
