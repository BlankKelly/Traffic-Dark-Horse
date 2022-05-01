## traffic-dark-horse
### 介绍
互联网系统中一个系统可能接入了前台大大小小几十个甚至数百个前台场景，在日常的系统监控
或者大促活动中，这些场景是由一个后端集群提供服务的，某个前台场景有可能因为运营活动等出
现了突发的热点流量，这时候需要快速发现某个场景的流量激增，然后进行干预，进行限流降级等，
避免因为某个场景的突增流量对所有的场景产生影响。

该组件通过在本地内存实时计算分场景的QPS，支持秒级发现流量黑马，支持扩展点将流量统计数据存储到任意地方，
在大盘展示分场景（渠道）的流量TOP榜，是系统稳定性建设和大促备战的重要支撑工具

备注：本组件的目的是快速获取流量的趋势，定位突发激增流量的来源，是一个在本地计算的组件
，不存在分布式的上报和中心化的汇总计算等模块。

### 设计

### 使用
#### 示例代码
```java
import com.myhub.traffic.dark.horse.Request;

import javax.annotation.Resource;

@RestController
public class DemoController {
    @Resource
    private Request request;
    
    @RequestMapping("/hello")
    public String hello(Map<String, String> params) {
        String businessId = params.get("businessId");
        // 进行请求的上报或者通过aop方式统一上报
        request.newRequest(businessId);
        
        return "hello";
    }
}
```