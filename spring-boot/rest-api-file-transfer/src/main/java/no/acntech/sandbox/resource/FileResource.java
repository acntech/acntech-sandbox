package no.acntech.sandbox.resource;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

@RequestMapping(path = "/api/files")
@RestController
public class FileResource {

    private final WebClient webClient;

    public FileResource(final WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public ResponseEntity<Resource> get() throws IOException {
        final var dataBufferFlux = webClient.get().uri("/files")
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        final var inputStream = new PipedInputStream();
        final var outputStream = new PipedOutputStream(inputStream);
        DataBufferUtils.write(dataBufferFlux, outputStream)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnComplete(() -> {
                    try {
                        outputStream.close();
                    } catch (IOException ignored) {
                    }
                })
                .subscribe(DataBufferUtils.releaseConsumer());
        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gandalf.jpg");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(inputStream));
    }
}
