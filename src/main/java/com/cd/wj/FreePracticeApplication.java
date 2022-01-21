package com.cd.wj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FreePracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreePracticeApplication.class, args);
        log.info("=========================FreePracticeApplication启动成功==========================");
    }

}
