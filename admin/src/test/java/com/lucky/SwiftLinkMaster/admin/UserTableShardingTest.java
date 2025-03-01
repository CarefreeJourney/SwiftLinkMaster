package com.lucky.SwiftLinkMaster.admin;

/**
 * @Author: lcl
 * @CreateTime: 2025/2/21 19:26
 * @Description: TODO
 * @Position: com.lucky.SwiftLinkMaster.admin
 * @CurrentVersion: 1.0
 * @VersionHistory: 1.0
 */
public class UserTableShardingTest {
//    public static final String SQL = "CREATE TABLE `t_user_%d` (\n" +
//            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'SaaS系统一般需要分库分表，就要用雪花算法，因此要用bigint(20)存id',\n" +
//            "  `username` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
//            "  `password` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
//            "  `real_name` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
//            "  `phone` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,\n" +
//            "  `mail` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱可以用户自定义尽量设置长一点',\n" +
//            "  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',\n" +
//            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间，不需要创建者和更新者这两个字段，因为是 SaaS 系统',\n" +
//            "  `update_time` datetime DEFAULT NULL,\n" +
//            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT '软删除，1删，0未删',\n" +
//            "  PRIMARY KEY (`id`),\n" +
//            "  UNIQUE KEY `idx_unique_username` (`username`) USING BTREE\n" +
//            ") ENGINE=InnoDB AUTO_INCREMENT=1892897279523520514 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;";
    public static final String SQL = "CREATE TABLE `t_group_%d` (\n" +
        "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
        "  `gid` varchar(32) DEFAULT NULL COMMENT '分组标识，可以单独重复，但不能和 username 作为组合重复了',\n" +
        "  `name` varchar(64) DEFAULT NULL COMMENT '分组名称',\n" +
        "  `username` varchar(256) DEFAULT NULL COMMENT '创建分组用户名',\n" +
        "  `sort_order` int DEFAULT NULL COMMENT '分组排序',\n" +
        "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
        "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
        "  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',\n" +
        "  PRIMARY KEY (`id`),\n" +
        "  UNIQUE KEY `idx_unique_username_gid` (`gid`,`username`) USING BTREE\n" +
        ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL)+"%n",i);// %n：这个格式化符号会根据运行程序的操作系统自动插入相应的新行字符。例如，在Windows系统上它会被替换为\r\n，而在Unix/Linux/Mac系统上则被替换为\n。
            // 使用%n而不是直接使用\n的好处是确保了代码的跨平台兼容性。
//            System.out.printf("drop table t_user%d;%n",i);
        }
    }
}
