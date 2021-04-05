package top.parak.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@Order(value = 2)
@Component
public class SecondStartRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SecondStartRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Start args [{}]", args);
    }
}
