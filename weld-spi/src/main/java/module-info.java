module org.jboss.weld.spi {
    requires transitive org.jboss.weld.api;
    requires static java.naming;
    requires static java.desktop;
    requires static jakarta.cdi.el;
    requires static jakarta.el;
    requires static jakarta.persistence;
    requires static jakarta.transaction;
    requires static jakarta.servlet;
    requires static jakarta.validation;
    requires static jakarta.ejb;

    exports org.jboss.weld.bootstrap.api;
    exports org.jboss.weld.bootstrap.api.helpers;
    exports org.jboss.weld.bootstrap.spi;
    exports org.jboss.weld.bootstrap.spi.helpers;
    exports org.jboss.weld.configuration.spi;
    exports org.jboss.weld.configuration.spi.helpers;
    exports org.jboss.weld.construction.api;
    exports org.jboss.weld.ejb.api;
    exports org.jboss.weld.ejb.spi;
    exports org.jboss.weld.ejb.spi.helpers;
    exports org.jboss.weld.injection.spi;
    exports org.jboss.weld.injection.spi.helpers;
    exports org.jboss.weld.manager.api;
    exports org.jboss.weld.resources.spi;
    exports org.jboss.weld.resources.spi.helpers;
    exports org.jboss.weld.security.spi;
    exports org.jboss.weld.serialization.spi;
    exports org.jboss.weld.serialization.spi.helpers;
    exports org.jboss.weld.servlet.api;
    exports org.jboss.weld.servlet.api.helpers;
    exports org.jboss.weld.servlet.spi;
    exports org.jboss.weld.servlet.spi.helpers;
    exports org.jboss.weld.transaction.spi;
}
