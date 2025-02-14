package com.lucky.SwiftLinkMaster.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/11 13:09
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.admin
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
@SpringBootApplication
@MapperScan("com.lucky.SwiftLinkMaster.admin.dao.mapper")
public class SwiftLinkMasterAdminApplication {
    public static void main(String[] args){
        SpringApplication.run(SwiftLinkMasterAdminApplication.class,args);
    }
}
