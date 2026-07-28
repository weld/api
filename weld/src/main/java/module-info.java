module org.jboss.weld.api {
    requires transitive jakarta.cdi;
    requires static jakarta.servlet;

    exports org.jboss.weld.bootstrap.event;
    exports org.jboss.weld.context;
    exports org.jboss.weld.context.activator;
    exports org.jboss.weld.context.api;
    exports org.jboss.weld.context.bound;
    exports org.jboss.weld.context.ejb;
    exports org.jboss.weld.context.http;
    exports org.jboss.weld.context.unbound;
    exports org.jboss.weld.events;
    exports org.jboss.weld.inject;
    exports org.jboss.weld.interceptor;
    exports org.jboss.weld.invoke;
    exports org.jboss.weld.proxy;
}
