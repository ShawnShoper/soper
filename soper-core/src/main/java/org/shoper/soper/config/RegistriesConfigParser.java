package org.shoper.soper.config;

import org.shoper.soper.register.conf.RegistryConfig;
import org.shoper.soper.register.conf.RegistryProtocol;
import org.shoper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RegistriesConfig 解析器
 * Created by ShawnShoper on 16/6/19.
 */
public class RegistriesConfigParser {
    public static RegistryProtocol parseProtocol(String address) {
        String protocol = null;
        try {
            protocol = address.substring(0, address.indexOf(":"));
        } catch (StringIndexOutOfBoundsException e) {
            //Do nothing
        }
        if (StringUtil.isEmpty(protocol))
            throw new NullPointerException("Address not specified protocol");
        return RegistryProtocol.getProtocol(protocol);
    }

    /**
     * 解析RegistryInfo
     */
    public static List<RegistryConfig.RegistryInfo> parser(String address) {
        if (Objects.isNull(address))
            throw new NullPointerException("Address can not be null...");
        List<RegistryConfig.RegistryInfo> registryConfigs = new ArrayList<>();
        return registryConfigs;
    }
}
