package org.shoper.soper.rpc.conf;


import org.shoper.soper.rpc.thrift.conf.ThriftServerConfig;
import org.shoper.soper.rpc.thrift.server.ThriftServer;

/**
 * Created by ShawnShoper on 16/6/19.
 */
public class ThriftServerBuilder {
    //hide construct
    private ThriftServerConfig tsc;

    public static ThriftServerBuilder newInstance(ThriftServerConfig tsc) {
        ThriftServerBuilder tsb = new ThriftServerBuilder();
        tsb.tsc = tsc;
        return tsb;
    }


    public ThriftServer build(String rpc) {
        if ("thrift".equals(rpc)) {
            return new ThriftServer(tsc);
        }
        return null;
    }
}
