/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.resources.spi;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;

/**
 * Represents metadata of a Java class. Weld uses this metadata to decide whether the class should be processed by Weld or not without loading the class.
 *
 * @author Jozef Hartinger
 *
 */
public interface ClassFileInfo {

    /**
     * Returns the name of this class.
     *
     * @return the FQCN of the class represented by this class file
     */
    String getClassName();

    /**
     * Returns the name of this class's superclass or java.lang.Object if this class does not have a superclass.
     *
     * @return the FQCN of the superclass of this class or java.lang.Object if this class does not have a superclass
     */
    String getSuperclassName();

    /**
     * Indicates whether an annotation of the specified annotation type is directly present on this class.
     *
     * @param annotationType the specified annotation type
     * @return true if an annotation of a specified type is directly present on this class, false otherwise
     */
    boolean isAnnotationDeclared(Class<? extends Annotation> annotationType);

    /**
     * Indicates whether this class contains an annotation of the specified annotation type. A class is said to contain the annotation if any of these applies:
     *
     * <ul>
     * <li>The annotation or an annotation annotated with the annotation is present on the class</li>
     * <li>The annotation or an annotation annotated with the annotation, which is annotated with {@link Inherited}, is present on a direct or indirect
     * superclass of the given class</li>
     * <li>The annotation or an annotation annotated with the annotation is present on a field or method declared by the given class or any direct or indirect
     * superclass of the given class</li>
     * <li>The annotation or an annotation annotated with the annotation is present on a parameter of a method declared by the given class or any direct or
     * indirect superclass of the given class</li>
     * <li>The annotation or an annotation annotated with the annotation is present on a constructor declared by the given class</li>
     * <li>The annotation or an annotation annotated with the annotation is present on a parameter of a constructor declared by the given class</li>
     * </ul>
     *
     * @param annotationType the specified annotation type
     * @return true if the class contains the annotation, false otherwise
     */
    boolean containsAnnotation(Class<? extends Annotation> annotationType);

    /**
     * Returns the class access and property modifiers, as defined in http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.1-200-E.1
     *
     * @return class access and property modifiers
     */
    int getModifiers();

    /**
     * Indicates whether this class has a CDI constructor.
     *
     * @return true if this class has a constructor annotated with {@link javax.inject.Inject} or a no-arg constructor, false otherwise
     */
    boolean hasCdiConstructor();

    /**
     * Indicates whether this class is either the same as, or is a superclass of the specified class.
     *
     * @param javaClass the specified class
     * @return true if this class is either the same as, or is a superclass of the specified class, false otherwise
     */
    boolean isAssignableFrom(Class<?> javaClass);

    /**
     * Indicates whether this class is either the same as, or is a subclass of the specified class.
     *
     * @param javaClass the specified class
     * @return true if this class is either the same as, or is a subclass of the specified class, false otherwise
     */
    boolean isAssignableTo(Class<?> javaClass);

    /**
     * Indicates whether this class is vetoed from CDI processing.
     *
     * @return true if the {@link javax.enterprise.inject.Vetoed} annotation is present on this class or the class's package, false otherwise
     */
    boolean isVetoed();

    /**
     * <b>Deprecated</b> - use {@code getClassNestingType()} instead.
     *
     * Indicates whether this class is a top-level class or an inner class.
     *
     * @return true if this class is a top-level class, false otherwise
     */
    default boolean isTopLevelClass() {
        return getNestingType().equals(NestingType.TOP_LEVEL);
    }

    /**
     * Returns the nesting type of the class using {@link NestingType} enum.
     * A class can be either top level, nested inner, nested local, nested anonymous or nested static.
     *
     * @return Class {@link NestingType}
     */
    NestingType getNestingType();

    /**
     * Helper enum which lists all possible nesting types of a class.
     */
    public enum NestingType {
        TOP_LEVEL,
        NESTED_INNER,
        NESTED_LOCAL,
        NESTED_ANONYMOUS,
        NESTED_STATIC
    }
}
