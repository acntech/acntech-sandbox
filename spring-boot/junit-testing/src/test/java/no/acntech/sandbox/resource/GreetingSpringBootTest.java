package no.acntech.sandbox.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import no.acntech.sandbox.model.GreetingDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"app.integration.names.url=http://dummy"}
)
public class GreetingSpringBootTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private RestTemplate restTemplateMock;

    @Test
    void shallReturn500StatusForNoResult() {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{});

        var entity = testRestTemplate.getForEntity("http://localhost:" + port + "/api/remote-greetings", String.class);

        assertThat(entity)
                .isNotNull()
                .extracting(ResponseEntity::getStatusCode)
                .isEqualTo(HttpStatusCode.valueOf(500));
    }

    @Test
    void shallReturnTwoResults() {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{"Jane", "Julie"});

        var entity = testRestTemplate.getForEntity("http://localhost:" + port + "/api/remote-greetings", GreetingDto[].class);

        assertThat(entity)
                .isNotNull();
        assertThat(entity.getStatusCode())
                .isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(entity.getBody())
                .hasSize(2);
    }
}