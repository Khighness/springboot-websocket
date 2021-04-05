# springboot-websocket

![Java](https://shields.io/badge/java-8-orange?logo=java&style=for-the-badge) ![Elastic Search](https://shields.io/badge/springboot%20websocket-2.2.2.RELEASE-blue?logo=Socket.io&style=for-the-badge) ![Spring Boot](https://shields.io/badge/springboot%20thymeleaf-2.2.2.RELEASE-lightgreen?logo=Thymeleaf&style=for-the-badge)



## 项目结构

```
src
  ├─main
  │  ├─java
  │  │  └─top
  │  │      └─parak
  │  │          ├─config      配置层
  │  │          ├─controller  控制层 
  │  │          ├─start       启动层
  │  │          └─websocket   WS服务器
  │  └─resources
  │      ├─static
  │      │  ├─css  CSS文件
  │      │  └─js   JavaScript文件
  │      └─view    HTML文件
  └─test
      └─java       测试
```



## 运行结果

运行项目，打开 http://192.168.117.133:3333/websocket/login/<username>

私聊：

![privateChat](https://cdn.nlark.com/yuque/0/2021/png/493248/1617610296430-912fe9b3-8415-401d-b193-26ee1f9f3469.png)

群聊：

![groupChat](https://cdn.nlark.com/yuque/0/2021/png/493248/1617610344944-0645a772-e3f3-4b2e-879f-a28d22427505.png)