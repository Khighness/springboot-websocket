package top.parak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author KHighness
 * @since 2021-04-05
 */

@EnableAsync
@SpringBootApplication
public class KHighnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(KHighnessApplication.class, args);
    }

}
