package org.shoper.soper.rpc.thrift.server;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.shoper.soper.config.InterfaceConfig;
import org.shoper.soper.exception.ProcessorException;
import org.shoper.soper.exception.RPCDeployTimeoutExcepion;
import org.shoper.soper.exception.RPCException;
import org.shoper.soper.register.conf.RegistryConfig;
import org.shoper.soper.rpc.thrift.conf.ProtocolFactory;
import org.shoper.soper.rpc.thrift.conf.ThriftServerConfig;

import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

import static java.util.Objects.isNull;

/**
 * Created by ShawnShoper on 16/5/5.
 */
public class ThriftServer {
    private TServer server;
    private ThriftServerConfig tsc;
    private RegistryConfig registryConfig;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ThriftServer(ThriftServerConfig tsc) {
        this.tsc = tsc;
    }


    public ThriftServer(String host, int port, int minThreads, int maxThreads, int timeout, int deployTimeout, TimeUnit timeUnit, ProtocolFactory protocolFactory) throws RPCException {
        ThriftServerConfig tsc = new ThriftServerConfig();
        tsc.setHost(host);
        tsc.setDeployTimeout(deployTimeout);
        tsc.setPort(port);
        tsc.setMinThreads(minThreads);
        tsc.setMaxThreads(maxThreads);
        tsc.setTimeout(timeout);
        tsc.setTimeUnit(timeUnit);
        tsc.setProtocol(protocolFactory);
        this.tsc = tsc;
    }

    /**
     * 无阻塞方式启动 server, 避免服务启动失败后让用户去检查
     *
     * @throws TTransportException
     * @throws ProcessorException
     */
    public boolean server() throws RPCException, InterruptedException {
        checkServerConfig();
        configurationServer();
        //启动 thrift 服务...
        executorService.submit(() ->
                                       server.serve()
        );
        //避免阻塞.轮训 server状态..
        Future<Boolean> future = executorService.submit(() -> {
            while (!isServer())
                TimeUnit.MILLISECONDS.sleep(2 << 4);
            return true;
        });
        //为任务发布加上超时时间,避免出现致命问题
        try {
            Boolean b = null;
            if (tsc.getDeployTimeout() > 0)
                b = future.get(tsc.getDeployTimeout(), tsc.getTimeUnit());
            else
                b = future.get();
            if (Objects.isNull(b) || b == false)
                if (isServer()) return true;
                else
                    return false;
        } catch (TimeoutException e) {
            throw new RPCDeployTimeoutExcepion("发布服务超时,Time-consuming " + tsc.getDeployTimeout());
        } catch (ExecutionException e) {
            throw new RPCException(e);
        }
        return true;
    }

    /**
     * &#x914d;&#x7f6e; thrift server ...
     *
     * @throws RPCDeployTimeoutExcepion
     * @throws ProcessorException
     */
    private void configurationServer() throws RPCException {
        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(
                tsc.getHost(),
                tsc.getPort()
        );
        TServerTransport t = null;
        try {
            t = new TServerSocket(inetSocketAddress);
        } catch (TTransportException e) {
            throw new RPCDeployTimeoutExcepion(e);
        }
        //获取ThreadPool 参数
        TThreadPoolServer.Args args = new TThreadPoolServer.Args(t);
        //添加处理器
        args.processor(processor);
        // 设置高压缩二进制协议
        args.protocolFactory(selectProtocolFactory(tsc.getProtocol()));
        // 设置线程池初始化数量
        args.minWorkerThreads(tsc.getMinThreads());
        // 最大允许并发,超过并发,线程阻塞.排队处理
        args.maxWorkerThreads(tsc.getMaxThreads());
        // 设置请求超时时间
        args.requestTimeout(tsc.getTimeout());
        // 设置请求超时时间单位
        args.requestTimeoutUnit(tsc.getTimeUnit());
        server = new TThreadPoolServer(args);
        // 设置注册的服务
        if (processors.isEmpty())
            throw new ProcessorException("Processor can not be empty");
        for (String p : this.processors.keySet()) {
            try {
                // 获取processor构造方法
                Class<?> iFace = Class.forName(p + "$Iface");
                Constructor<?> constructor = Class.forName(p + "$Processor").getConstructor(iFace);
                TProcessor serviceProcessor = (TProcessor) constructor.newInstance(this.processors.get(p).getProcessor());
                processor.registerProcessor(p, serviceProcessor);
            } catch (Exception e) {
                throw new RPCException(e);
            }
        }
    }

    /**
     * select protocol fatory...
     *
     * @param protocol
     * @return
     */
    private TProtocolFactory selectProtocolFactory(ProtocolFactory protocol) {
        TProtocolFactory protocolFactory = null;
        if (ProtocolFactory.BINARY.equals(protocol))
            protocolFactory = new TBinaryProtocol.Factory();
        if (ProtocolFactory.COMPACT.equals(protocol))
            protocolFactory = new TCompactProtocol.Factory();
        if (ProtocolFactory.SIMPLEJSON.equals(protocol))
            protocolFactory = new TSimpleJSONProtocol.Factory();
        if (ProtocolFactory.TUPLE.equals(protocol))
            protocolFactory = new TTupleProtocol.Factory();
        return protocolFactory;
    }

    /**
     * check server config is correct...
     *
     * @throws RPCException
     */
    private void checkServerConfig() throws RPCException {
        //if host is empty,set default host.
//        if (StringUtils.isEmpty(tsc.getHost()))
            tsc.setHost("127.0.0.1");
        if (tsc.getPort() > 65535 || tsc.getPort() < 1025)
            throw new RPCException("Port is out of range,please check your port is in [1025 ~ 65535]");
        if (tsc.getDeployTimeout() < 0)
            throw new RPCException("Deploy timeout must be greater than 0");
        if (tsc.getMaxThreads() <= 0)
            throw new RPCException("time out must be greater than 0");
        if (tsc.getMinThreads() <= 0)
            throw new RPCException("time out must be greater than 0");
        if (tsc.getTimeout() <= 0)
            throw new RPCException("time out must be greater than 0");
        if (Objects.isNull(tsc.getTimeUnit()))
            throw new RPCException("time unit can not be null");
        if (Objects.isNull(tsc.getProtocol()))
            throw new RPCException("Thrift protocol can not be null,please see org.shoper.thriftx.rpc.thrift.conf.ProtocolFactory");
    }

    private Map<String, InterfaceConfig> processors = new HashMap<>();

    /**
     * check thrift server is serving
     */
    public boolean isServer() {
        return server.isServing();
    }

    /**
     * registry server into thrift processor..
     *
     * @param inface
     */
    public void registy(InterfaceConfig inface) throws RPCException {
        if (isNull(inface))
            throw new NullPointerException();

        processors.put(inface.getStub().getName(), inface);
    }

    /**
     * stop thift server
     */
    public void stop() {
        if (server != null)
            server.stop();
        executorService.shutdown();
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }
}
