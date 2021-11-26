package org.ciapge.config.spring.config;

/**
 * @author 朱林
 * @description
 * @date 2021/11/25 22:49
 */
public class ProviderConfig {
    private String service;
    private String ref;
    private String alias;

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
}
