package org.shoper.soper.config;

/**
 * Created by ShawnShoper on 16/5/5.
 */
public class InterfaceConfig extends AbstractConfig {
    // 服务接口的本地实现类名
    protected String local;

    // 服务接口的本地实现类名
    protected Class<?> stub;
    //接口实现 processor.这里之所以使用Object 实例,是为了以后好与其他容器集成
    protected Object processor;
    // 服务监控
    protected MonitorConfig monitor;
    // 代理类型
    protected String proxy;
    // 集群方式
    protected String cluster;
    // 过滤器
    protected String filter;
    // 监听器
    protected String listener;

    // 负责人
    protected String owner;

    public Class<?> getStub() {
        return stub;
    }

    public void setStub(Class<?> stub) {
        this.stub = stub;
    }

    public Object getProcessor() {
        return processor;
    }

    public void setProcessor(Object processor) {
        this.processor = processor;
    }
}
