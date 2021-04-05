package top.parak.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Configuration;
import top.parak.start.FirstStartRunner;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@Configuration
public class ParaKExitCodeGenerator implements ExitCodeGenerator {
    private Logger logger = LoggerFactory.getLogger(FirstStartRunner.class);

    @Override
    public int getExitCode() {
        logger.info("Server exit");
        return -1;
    }
}
