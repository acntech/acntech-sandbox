package no.acntech.sandbox.cache;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * The default behavior of Spring Security is to save the original request URL in the default request cache
 * ({@link HttpSessionRequestCache}) and redirect back to that URL after successful OIDC authentication.
 * This doesn't work well for SPA frontends where the request typically is an API call. This bean overrides the
 * default behavior to instead save the URL from the {@code Referer} HTTP header, if it is present in the request.
 * The {@code Referer} URL will be the URL of the SPA frontend, as visible in the browser address bar.
 */
@Component
public class ReferrerAwareHttpSessionRequestCache extends HttpSessionRequestCache {

    @Override
    public void saveRequest(final HttpServletRequest request, final HttpServletResponse response) {
        final var referrer = request.getHeader("referer");
        final var redirectUrl = StringUtils.hasText(referrer) ? referrer : request.getRequestURL().toString();
        request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(redirectUrl));
    }
}
