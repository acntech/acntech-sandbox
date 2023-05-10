package no.acntech.sandbox.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RequestMapping(path = "/api/files")
@RestController
public class FileResource {

    private final Resource resource;

    public FileResource(@Value("classpath:files/gandalf.jpg") final Resource resource) {
        this.resource = resource;
    }

    @GetMapping
    public ResponseEntity<Resource> get() throws IOException {
        final var file = resource.getFile();
        final var inputStreamResource = new InputStreamResource(new FileInputStream(file));
        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gandalf.jpg");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }
}
