package org.jboss.weld.servlet.spi.test;

import org.jboss.weld.servlet.spi.HttpContextActivationFilter;
import org.jboss.weld.servlet.spi.helpers.RegexHttpContextActivationFilter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HttpContextActivationFilterTest {

    @Test
    public void test1() {
        HttpContextActivationFilter filter = new RegexHttpContextActivationFilter("/foo.*");
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/foo", "/app")));
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/foo/bar", "/app")));
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/foofoo", "/app")));
        Assert.assertFalse(filter.accepts(new MockHttpServletRequest("/app/bar", "/app")));
        Assert.assertFalse(filter.accepts(new MockHttpServletRequest("/app/bar/foo", "/app")));
    }

    @Test
    public void test2() {
        HttpContextActivationFilter filter = new RegexHttpContextActivationFilter("/.*\\.jsf");
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/foo.jsf", "/app")));
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/bar.jsf", "/app")));
        Assert.assertTrue(filter.accepts(new MockHttpServletRequest("/app/foo/bar/baz.jsf", "/app")));
        Assert.assertFalse(filter.accepts(new MockHttpServletRequest("/app/foo.jpg", "/app")));
        Assert.assertFalse(filter.accepts(new MockHttpServletRequest("/app/foo/bar/baz.css", "/app")));
    }
}
