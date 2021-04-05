package top.parak.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@Order(value = 1)
@Component
public class FirstStartRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(FirstStartRunner.class);

    @Override
    public void run(String... args) throws Exception {
        InetAddress host = InetAddress.getLocalHost();
        logger.info("Server start at {}", host.getHostName());
    }
}
