package org.shoper.soper.register.conf;

/**
 * Created by ShawnShoper on 16/6/19.
 */
public enum RegistryProtocol {
    ZOOKEEPER("Zookeeper");
    private String name;

    RegistryProtocol(String name) {
        this.name = name;
    }

    public static RegistryProtocol getProtocol(String name) {
        if(ZOOKEEPER.name.equalsIgnoreCase(name))
            return ZOOKEEPER;
        throw new IllegalArgumentException("Non support this protocol");
    }

}
