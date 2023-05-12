package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequestMapping(path = "/api/files")
@RestController
public class FileResource {

    @PostMapping
    public ResponseEntity<Void> post(@RequestParam("file") @NotNull final MultipartFile multipartFile) throws IOException {
        final var fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        final var file = Paths.get(System.getProperty("java.io.tmpdir"), fileName);
        Files.copy(multipartFile.getInputStream(), file, StandardCopyOption.REPLACE_EXISTING);
        return ResponseEntity.ok().build();
    }
}
