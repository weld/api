/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.injection.spi.helpers;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.naming.Context;
import javax.naming.NamingException;

import jakarta.annotation.Resource;
import jakarta.enterprise.inject.spi.Annotated;
import jakarta.enterprise.inject.spi.AnnotatedParameter;
import jakarta.enterprise.inject.spi.InjectionPoint;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.injection.spi.ResourceInjectionServices;
import org.jboss.weld.injection.spi.ResourceReference;
import org.jboss.weld.injection.spi.ResourceReferenceFactory;

/**
 * Abstract subclass for {@link ResourceInjectionServices} defining several common capabilities.
 */
public abstract class AbstractResourceServices implements Service, ResourceInjectionServices {
    private static final String RESOURCE_LOOKUP_PREFIX = "java:comp/env";

    /**
     * Verifies that provided {@link InjectionPoint} has {@link Resource} annotation on it, extracts resource name from it and
     * then invokes {@link Context#lookup(String)}.
     *
     * @param injectionPoint injection points to inspect
     * @return resource
     */
    public Object resolveResource(InjectionPoint injectionPoint) {
        if (getResourceAnnotation(injectionPoint) == null) {
            throw new IllegalArgumentException("No @Resource annotation found on injection point " + injectionPoint);
        }
        if (injectionPoint.getMember() instanceof Method
                && ((Method) injectionPoint.getMember()).getParameterTypes().length != 1) {
            throw new IllegalArgumentException(
                    "Injection point represents a method which doesn't follow JavaBean conventions (must have exactly one parameter) "
                            + injectionPoint);
        }
        String name = getResourceName(injectionPoint);
        try {
            return getContext().lookup(name);
        } catch (NamingException e) {
            return handleNamingException(e, name);
        }
    }

    /**
     * Uses provided parameters to look up the resource via {@link Context#lookup(String)}
     *
     * @param jndiName jndi name of the resource, may be {@code null}
     * @param mappedName mappedName of the resource, may be {@code null}
     * @return looked up resource
     */
    public Object resolveResource(String jndiName, String mappedName) {
        String name = getResourceName(jndiName, mappedName);
        try {
            return getContext().lookup(name);
        } catch (NamingException e) {
            return handleNamingException(e, name);
        }
    }

    private Object handleNamingException(NamingException e, String name) {
        throw new RuntimeException("Error looking up " + name + " in JNDI", e);
    }

    /**
     * Returns a {@code String} representation of the resource name from provided parameters.
     * {@code mappedName} takes precedence over {@code jndiName} unless it is {@code null}.
     *
     * Throws {@link IllegalArgumentException} if both parameters are {@code null}.
     *
     * @param jndiName jndiName or null
     * @param mappedName mappedName or null
     * @return {@code String} representation of the resource name
     */
    protected String getResourceName(String jndiName, String mappedName) {
        if (mappedName != null) {
            return mappedName;
        } else if (jndiName != null) {
            return jndiName;
        } else {
            throw new IllegalArgumentException("Both jndiName and mappedName are null");
        }
    }

    /**
     * Returns the {@link Context} instance.
     *
     * @return {@link Context} instance
     */
    protected abstract Context getContext();

    /**
     * Retrieves the {@link Resource} annotation from provided {@link InjectionPoint} and extracts resource name from it.
     *
     * @param injectionPoint injection point to inspect
     * @return {@code String} representation of resource name
     */
    protected String getResourceName(InjectionPoint injectionPoint) {
        Resource resource = getResourceAnnotation(injectionPoint);

        String lookup = resource.lookup();
        if (!lookup.equals("")) {
            return lookup;
        }
        String mappedName = resource.mappedName();
        if (!mappedName.equals("")) {
            return mappedName;
        }
        String name = resource.name();
        if (!name.equals("")) {
            return RESOURCE_LOOKUP_PREFIX + "/" + name;
        }
        String propertyName;
        if (injectionPoint.getMember() instanceof Field) {
            propertyName = injectionPoint.getMember().getName();
        } else if (injectionPoint.getMember() instanceof Method) {
            propertyName = getPropertyName((Method) injectionPoint.getMember());
            if (propertyName == null) {
                throw new IllegalArgumentException("Injection point represents a method which doesn't follow "
                        + "JavaBean conventions (unable to determine property name) " + injectionPoint);
            }
        } else {
            throw new AssertionError("Unable to inject into " + injectionPoint);
        }
        String className = injectionPoint.getMember().getDeclaringClass().getName();
        return RESOURCE_LOOKUP_PREFIX + "/" + className + "/" + propertyName;
    }

    /**
     * Returns property name as a {@code String} extracted from given method by looking at its name and stripping its prefix.
     * May return {@code null} if the method name does not start with set/get/is prefix.
     *
     * @param method method to parse property name from
     * @return property name or null if the name cannot be parsed
     */
    public static String getPropertyName(Method method) {
        String methodName = method.getName();

        if (methodName.matches("^(set).*") && method.getParameterTypes().length == 1) {
            return Introspector.decapitalize(methodName.substring(3));
        } else if (methodName.matches("^(get).*") && method.getParameterTypes().length == 0) {
            return Introspector.decapitalize(methodName.substring(3));
        } else if (methodName.matches("^(is).*") && method.getParameterTypes().length == 0) {
            return Introspector.decapitalize(methodName.substring(2));
        } else {
            return null;
        }

    }

    /*
     * Trivial implementation that does *not* leverage bootstrap validation nor caching
     */
    @Override
    public ResourceReferenceFactory<Object> registerResourceInjectionPoint(final InjectionPoint injectionPoint) {
        return new ResourceReferenceFactory<Object>() {
            @Override
            public ResourceReference<Object> createResource() {
                return new SimpleResourceReference<Object>(resolveResource(injectionPoint));
            }
        };
    }

    /*
     * Trivial implementation that does *not* leverage bootstrap validation nor caching
     */
    @Override
    public ResourceReferenceFactory<Object> registerResourceInjectionPoint(final String jndiName,
            final String mappedName) {
        return new ResourceReferenceFactory<Object>() {
            @Override
            public ResourceReference<Object> createResource() {
                return new SimpleResourceReference<Object>(resolveResource(jndiName, mappedName));
            }
        };
    }

    /**
     * Parses the {@link Resource} annotation from given {@link InjectionPoint}.
     *
     * @param injectionPoint Injection point to parse the annotation from
     * @return Instance of {@link Resource} annotation if present, null otherwise
     */
    protected Resource getResourceAnnotation(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        if (annotated instanceof AnnotatedParameter<?>) {
            annotated = ((AnnotatedParameter<?>) annotated).getDeclaringCallable();
        }
        return annotated.getAnnotation(Resource.class);
    }

    public void cleanup() {
    }

}
