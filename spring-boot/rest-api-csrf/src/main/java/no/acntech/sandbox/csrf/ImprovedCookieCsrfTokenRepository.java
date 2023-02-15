package no.acntech.sandbox.csrf;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.util.StringUtils;

public class ImprovedCookieCsrfTokenRepository implements CsrfTokenRepository {

    static final String CLIENT_CSRF_COOKIE_NAME = "CLIENT-XSRF-TOKEN";

    private final CookieCsrfTokenRepository delegate;

    public ImprovedCookieCsrfTokenRepository() {
        this.delegate = new CookieCsrfTokenRepository();
    }

    @Override
    public CsrfToken generateToken(final HttpServletRequest request) {
        return delegate.generateToken(request);
    }

    @Override
    public void saveToken(final CsrfToken token, final HttpServletRequest request, final HttpServletResponse response) {
        delegate.saveToken(token, request, response);

        String tokenValue = token == null ? "" : token.getToken();
        String cookiePath = StringUtils.hasText(delegate.getCookiePath()) ? delegate.getCookiePath() : getRequestContext(request);
        int cookieMaxAge = token == null ? 0 : -1; // Delete cookie if no token else session cookie

        Cookie cookie = new Cookie(CLIENT_CSRF_COOKIE_NAME, tokenValue);
        cookie.setSecure(request.isSecure());
        cookie.setPath(cookiePath);
        cookie.setMaxAge(cookieMaxAge); // Session cookie if token exists
        cookie.setHttpOnly(false); // Must be readable by clients

        response.addCookie(cookie);
    }

    @Override
    public CsrfToken loadToken(final HttpServletRequest request) {
        return delegate.loadToken(request);
    }

    private String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return StringUtils.hasText(contextPath) ? contextPath : "/";
    }
}
