package org.shoper.soper.rpc.thrift.conf;

import org.apache.zookeeper.server.ServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * Thrift server config
 * Created by ShawnShoper on 16/6/19.
 */
public class ThriftServerConfig extends ServerConfig {
    private String host;
    private int port;
    private int minThreads;
    private int maxThreads;
    private int timeout;
    private int deployTimeout;
    private TimeUnit timeUnit;
    private ProtocolFactory protocol;

    public ProtocolFactory getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolFactory protocol) {
        this.protocol = protocol;
    }

    public int getDeployTimeout() {
        return deployTimeout;
    }

    public void setDeployTimeout(int deployTimeout) {
        this.deployTimeout = deployTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
