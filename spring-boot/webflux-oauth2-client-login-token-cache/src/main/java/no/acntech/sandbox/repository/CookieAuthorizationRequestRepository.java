package no.acntech.sandbox.repository;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.client.web.server.ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import no.acntech.sandbox.serialize.Base64Deserializer;
import no.acntech.sandbox.serialize.Base64Serializer;

public class CookieAuthorizationRequestRepository implements ServerAuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "oidc_authorization_request";
    private static final int AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS = 180;

    @Override
    public Mono<OAuth2AuthorizationRequest> loadAuthorizationRequest(final ServerWebExchange exchange) {
        Assert.notNull(exchange, "exchange cannot be null");
        var state = exchange.getRequest().getQueryParams().getFirst(OAuth2ParameterNames.STATE);
        if (state == null) {
            return Mono.empty();
        }
        return Mono.just(exchange)
                .map(ServerWebExchange::getRequest)
                .map(ServerHttpRequest::getCookies)
                .mapNotNull(cookies -> cookies.getFirst(AUTHORIZATION_REQUEST_COOKIE_NAME))
                .map(HttpCookie::getValue)
                .map(value -> Base64Deserializer.deserialize(value, OAuth2AuthorizationRequest.class));
    }

    @Override
    public Mono<Void> saveAuthorizationRequest(final OAuth2AuthorizationRequest authorizationRequest,
                                               final ServerWebExchange exchange) {
        Assert.notNull(authorizationRequest, "authorizationRequest cannot be null");
        Assert.notNull(exchange, "exchange cannot be null");
        return Mono.just(authorizationRequest)
                .map(Base64Serializer::serialize)
                .map(value -> ResponseCookie.from(AUTHORIZATION_REQUEST_COOKIE_NAME, value)
                        .maxAge(AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS)
                        .httpOnly(true)
                        .path("/")
                        .build())
                .doOnNext(cookie -> exchange.getResponse().addCookie(cookie))
                .then(Mono.empty());
    }

    @Override
    public Mono<OAuth2AuthorizationRequest> removeAuthorizationRequest(final ServerWebExchange exchange) {
        Assert.notNull(exchange, "exchange cannot be null");
        return Mono.just(ResponseCookie.from(AUTHORIZATION_REQUEST_COOKIE_NAME)
                        .maxAge(0)
                        .httpOnly(true)
                        .path("/")
                        .build())
                .doOnNext(cookie -> exchange.getResponse().addCookie(cookie))
                .then(loadAuthorizationRequest(exchange));
    }
}
