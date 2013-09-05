package org.jboss.weld.servlet.spi.helpers;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jboss.weld.servlet.spi.HttpContextActivationFilter;

/**
 * A helper implementation of {@link HttpContextActivationFilter} that accepts every request that matches a predefined regular expression.
 *
 * @author Jozef Hartinger
 *
 */
public class RegexHttpContextActivationFilter implements HttpContextActivationFilter {

    private final Pattern pattern;

    public RegexHttpContextActivationFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    public RegexHttpContextActivationFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accepts(HttpServletRequest request) {
        final String path = request.getRequestURI().substring(request.getContextPath().length());
        return pattern.matcher(path).matches();
    }

    @Override
    public void cleanup() {
    }
}
