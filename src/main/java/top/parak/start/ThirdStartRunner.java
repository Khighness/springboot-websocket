package top.parak.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@Order(value = 3)
@Component
public class ThirdStartRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(ThirdStartRunner.class);

    @Value("${server.port}")
    public String serverPort;

    @Override
    public void run(String... args) throws Exception {
        InetAddress host = InetAddress.getLocalHost();
        StringBuilder uri = new StringBuilder().append("http://")
                .append(host.getHostAddress()).append(':')
                .append(serverPort).append("/websocket/login");
        logger.info("Chat to {} with </username>", uri.toString());
    }

}
