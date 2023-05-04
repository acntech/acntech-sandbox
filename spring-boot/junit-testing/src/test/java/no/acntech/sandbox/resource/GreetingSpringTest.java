package no.acntech.sandbox.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import no.acntech.sandbox.service.GreetingService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestPropertySource(properties = {"app.integration.names.url=http://dummy"})
@ContextConfiguration(classes = {GreetingService.class})
@ExtendWith(SpringExtension.class)
public class GreetingSpringTest {

    @MockBean
    private RestTemplate restTemplateMock;
    @Autowired
    private GreetingService greetingService;

    @Test
    void shallThrowExceptionForNoResult() {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{});

        var thrownException = assertThrows(ResponseStatusException.class, () -> greetingService.getRemoteGreeting(2));

        assertThat(thrownException)
                .isNotNull()
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void shallReturnTwoResults() {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{"Jane", "Julie"});

        var greetings = greetingService.getRemoteGreeting(2);

        assertThat(greetings)
                .hasSize(2);
    }
}