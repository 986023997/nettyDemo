package org.ciapge.domain;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:49
 */
public class RpcProviderConfig {
    private String service;
    private String ref;
    private String alias;
    private String host;
    private int port;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
