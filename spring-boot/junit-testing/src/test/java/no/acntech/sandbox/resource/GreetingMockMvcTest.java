package no.acntech.sandbox.resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import no.acntech.sandbox.service.GreetingService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {GreetingResource.class, GreetingService.class})
@WebMvcTest
public class GreetingMockMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestTemplate restTemplateMock;

    @BeforeEach
    public void before() {
    }

    @Test
    void shallReturn500StatusForNoResult() throws Exception {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{});

        this.mockMvc
                .perform(get("/api/remote-greetings"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shallReturnTwoResults() throws Exception {
        when(restTemplateMock.getForObject(any(URI.class), any(Class.class)))
                .thenReturn(new String[]{"Jane", "Julie"});

        this.mockMvc
                .perform(get("/api/remote-greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}