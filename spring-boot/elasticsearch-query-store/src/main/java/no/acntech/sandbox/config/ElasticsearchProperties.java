package no.acntech.sandbox.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticsearchProperties {

    @NotEmpty
    private List<URI> hosts;

    public List<URI> getHosts() {
        return hosts;
    }

    public void setHosts(List<URI> hosts) {
        this.hosts = hosts;
    }
}
