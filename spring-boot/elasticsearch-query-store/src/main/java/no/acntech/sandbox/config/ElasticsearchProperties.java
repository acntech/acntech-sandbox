package no.acntech.sandbox.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchProperties {

    @NotEmpty
    private String[] hosts;

    public String[] getHosts() {
        return hosts;
    }

    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }
}
