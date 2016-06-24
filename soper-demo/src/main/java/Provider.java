import org.shoper.soper.config.InterfaceConfig;
import org.shoper.soper.exception.RPCException;
import org.shoper.soper.register.conf.RegistryConfig;
import org.shoper.soper.rpc.conf.ThriftServerBuilder;
import org.shoper.soper.rpc.thrift.conf.ProtocolFactory;
import org.shoper.soper.rpc.thrift.conf.ThriftServerConfig;
import org.shoper.soper.rpc.thrift.server.ThriftServer;

import java.util.concurrent.TimeUnit;

/**
 * Created by ShawnShoper on 16/6/19.
 */
public class Provider {
    public static void main(String[] args)throws InterruptedException {
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

        ThriftServer thriftServer = ThriftServerBuilder.newInstance(tsc).build("thrift");
        InterfaceConfig interfaceConfig = new InterfaceConfig();
        interfaceConfig.setStub(ReportServer.class);
        interfaceConfig.setProcessor(new ReportServerImpl());
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
