package org.shoper.soper.rpc;

import org.shoper.soper.config.InterfaceConfig;
import org.shoper.soper.exception.RPCException;
import org.shoper.soper.register.conf.RegistryConfig;
import org.shoper.soper.rpc.thrift.conf.ProtocolFactory;
import org.shoper.soper.rpc.thrift.conf.ThriftServerConfig;
import org.shoper.soper.rpc.thrift.server.ThriftServer;
import org.shoper.soper.rpc.thrift.server.ThriftServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShawnShoper on 16/6/19.
 */
@Component
public class RPC {
    @Autowired
    ReportServerImpl reportServer;
    public void start()throws InterruptedException {
        //设置注册中心
        RegistryConfig registryConfig = new RegistryConfig();

        //设置服务器配置...
        ThriftServerConfig tsc = new ThriftServerConfig();
        tsc.setHost("127.0.0.1");
        tsc.setPort(8099);
        tsc.setTimeUnit(TimeUnit.SECONDS);
        tsc.setTimeout(20);
        tsc.setMinThreads(3);
        tsc.setProtocol(ProtocolFactory.COMPACT);
        tsc.setMaxThreads(100);

        ThriftServer thriftServer = ThriftServerBuilder.newInstance(tsc).build();
        InterfaceConfig interfaceConfig = new InterfaceConfig();
        interfaceConfig.setStub(ReportServer.class);
        interfaceConfig.setProcessor(reportServer);
        try {
            thriftServer.registy(interfaceConfig);
            if (thriftServer.server()) {
                System.out.println("发布成功");
            } else {
                thriftServer.stop();
                System.out.println("发布失败");
            }
        } catch (RPCException e) {
            thriftServer.stop();
            e.printStackTrace();
        }
    }
}
