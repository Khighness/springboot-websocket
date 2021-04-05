package top.parak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@Configuration
public class WebSocketConfigure {

    /**
     * 扫描并注册所有携带@ServerEndpoint注解的实例
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public CustomEndpointConfigure customEndpointConfigure() {
        return new CustomEndpointConfigure();
    }
}
