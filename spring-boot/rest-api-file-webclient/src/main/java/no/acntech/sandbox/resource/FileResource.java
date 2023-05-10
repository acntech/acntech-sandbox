package no.acntech.sandbox.resource;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "/api/files")
@RestController
public class FileResource {

    private final WebClient webClient;

    public FileResource(final WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public Flux<DataBuffer> get(final HttpServletResponse response) throws IOException {
        final var dataBufferFlux = webClient.get().uri("/files")
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gandalf.jpg");
        return DataBufferUtils.write(dataBufferFlux, response.getOutputStream());
    }
}
