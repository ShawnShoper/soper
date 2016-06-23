package org.shoper.soper.register.conf;


import org.shoper.soper.config.RegistriesConfigParser;

import java.util.List;
import java.util.Map;

import static org.shoper.soper.config.RegistriesConfigParser.parseProtocol;

/**
 * Created by ShawnShoper 16/5/5.
 */
public class RegistryConfig {
    private static final long serialVersionUID = 5508512956753757169L;

    public static final String NO_AVAILABLE = "N/A";
    private List<RegistryInfo> registryInfos;
    // 注册中心登录用户名
    private String username;
    // 注册中心登录密码
    private String password;
    // 注册中心协议
    private RegistryProtocol protocol;
    // 客户端实现
    private String transporter;
    private String server;
    private String client;
    private String cluster;
    private String group;
    private String version;
    // 注册中心请求超时时间(毫秒)
    private Integer timeout;
    // 注册中心会话超时时间(毫秒)
    private Integer session;
    // 动态注册中心列表存储文件
    private String file;
    // 停止时等候完成通知时间
    private Integer wait;
    // 启动时检查注册中心是否存在
    private Boolean check;
    // 在该注册中心上注册是动态的还是静态的服务
    private Boolean dynamic;
    // 在该注册中心上服务是否暴露
    private Boolean register;
    // 在该注册中心上服务是否引用
    private Boolean subscribe;
    // 自定义参数
    private Map<String, String> parameters;
    // 是否为缺省
    private Boolean isDefault;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public static String getNoAvailable() {
        return NO_AVAILABLE;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegistryProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(RegistryProtocol protocol) {
        this.protocol = protocol;
    }

    public List<RegistryInfo> getRegistryInfos() {
        return registryInfos;
    }

    public void setRegistryInfos(List<RegistryInfo> registryInfos) {
        this.registryInfos = registryInfos;
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getWait() {
        return wait;
    }

    public void setWait(Integer wait) {
        this.wait = wait;
    }

    public RegistryConfig() {

    }

    //Example: zookeeper://192.168.0.1:2181@192.168.0.2:2181
    public RegistryConfig(String address) {
        RegistryProtocol protocol = parseProtocol(address);
        this.setProtocol(protocol);
        List<RegistryInfo> registryInfos = RegistriesConfigParser.parser(address);
        this.setRegistryInfos(registryInfos);
    }

    public static class RegistryInfo {
        private String host;
        private String port;
        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }

}
