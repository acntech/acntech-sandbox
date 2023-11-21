package no.acntech.sandbox.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.UUID;

public class RedisSecurityContextRepository implements SecurityContextRepository {

    private static final String SESSION_COOKIE_NAME = "oidc_session";
    private static final int SESSION_COOKIE_EXPIRE_SECONDS = -1;
    private final RedisTemplate<String, SecurityContext> redisTemplate;

    public RedisSecurityContextRepository(final RedisTemplate<String, SecurityContext> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {
        var request = requestResponseHolder.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                    .map(Cookie::getValue)
                    .findFirst()
                    .map(redisTemplate.opsForValue()::get)
                    .orElseGet(SecurityContextHolder::createEmptyContext);
        }
    }

    @Override
    public void saveContext(final SecurityContext context,
                            final HttpServletRequest request,
                            final HttpServletResponse response) {
        var cookies = request.getCookies();
        var sessionId = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .map(Cookie::getValue)
                .findFirst()
                .orElseGet(UUID.randomUUID()::toString);
        var cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        cookie.setMaxAge(SESSION_COOKIE_EXPIRE_SECONDS);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        redisTemplate.opsForValue().set(sessionId, context);
    }

    @Override
    public boolean containsContext(final HttpServletRequest request) {
        var cookies = request.getCookies();
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .map(Cookie::getValue)
                .findFirst()
                .map(redisTemplate::hasKey)
                .orElse(false);
    }
}
