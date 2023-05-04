package no.acntech.sandbox.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import no.acntech.sandbox.service.GreetingService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GreetingTest {

    @Mock
    private RestTemplate restTemplateMock;
    @InjectMocks
    private GreetingService greetingService;

    @BeforeEach
    public void before() {
        ReflectionTestUtils.setField(greetingService, "namesUrl", "http://whatever");
    }

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