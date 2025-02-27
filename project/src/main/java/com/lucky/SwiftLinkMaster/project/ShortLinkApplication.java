package com.lucky.SwiftLinkMaster.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/25 22:56
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.project
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@MapperScan("com.lucky.SwiftLinkMaster.project.dao.mapper")
@SpringBootApplication
public class ShortLinkApplication {
    public static void main(String[] args){
        SpringApplication.run(ShortLinkApplication.class,args);
    }
}
