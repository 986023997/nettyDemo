package org.ciapge.config.spring.config;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:50
 */
public class ServerConfig {
    protected String host;
    protected int port;

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
