package org.ciapge.config.spring.config;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:48
 */
public class ConsumerConfig<T> {
    private String service;
    private String alias;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
