package org.shoper.soper.config;

import org.shoper.soper.register.conf.RegistryConfig;

import java.util.List;

/**
 * Created by ShawnShoper on 16/5/4.
 */
public class ApplicationConfig extends AbstractConfig {

    // 应用名称
    private String name;
    // 模块版本
    private String version;
    // 应用负责人
    private String owner;
    // 组织名(BU或部门)
    private String organization;
    // 分层
    private String architecture;
    // 环境，如：dev/test/run
    private String environment;
    // Java代码编译器
    private String compiler;
    // 日志输出方式
    private String logger;
    // 注册中心
    private List<RegistryConfig> registries;
    // 服务监控
    private MonitorConfig monitor;

    // 是否为缺省
    private Boolean isDefault;
}
