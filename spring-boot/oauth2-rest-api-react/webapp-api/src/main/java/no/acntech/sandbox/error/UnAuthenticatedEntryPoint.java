package no.acntech.sandbox.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;

public class UnAuthenticatedEntryPoint implements AuthenticationEntryPoint {

    private final URI redirectUri;

    public UnAuthenticatedEntryPoint(final String registeredClientId) {
        Assert.hasText(registeredClientId, "Registered client ID is null or blank");
        this.redirectUri = UriComponentsBuilder.fromPath("/oauth2/authorization")
                .pathSegment(registeredClientId)
                .build()
                .toUri();
    }

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.LOCATION, redirectUri.toASCIIString());
    }
}
