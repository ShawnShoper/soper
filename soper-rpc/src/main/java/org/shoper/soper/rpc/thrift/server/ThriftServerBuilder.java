package org.shoper.soper.rpc.thrift.server;


import org.shoper.soper.rpc.thrift.conf.ThriftServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShawnShoper on 16/6/19.
 */
public class ThriftServerBuilder {
    //hide construct
    private ThriftServerBuilder() {

    }

    private ThriftServerConfig tsc;

    public static ThriftServerBuilder newInstance(String host, int port, int minThreads, int maxThreads, int timeout, TimeUnit timeUnit) {
        ThriftServerConfig tsc = new ThriftServerConfig();
        tsc.setHost(host);
        tsc.setPort(port);
        tsc.setMinThreads(minThreads);
        tsc.setMaxThreads(maxThreads);
        tsc.setTimeout(timeout);
        tsc.setTimeUnit(timeUnit);
        return newInstance(tsc);
    }

    public static ThriftServerBuilder newInstance(ThriftServerConfig tsc) {
        ThriftServerBuilder tsb = new ThriftServerBuilder();
        tsb.tsc = tsc;
        return tsb;
    }

    public ThriftServer build() {
        return new ThriftServer(tsc);
    }
}
