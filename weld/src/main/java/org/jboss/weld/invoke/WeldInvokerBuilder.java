package org.jboss.weld.invoke;

import jakarta.enterprise.invoke.Invoker;
import jakarta.enterprise.invoke.InvokerBuilder;

/**
 * Builder of {@link Invoker}s that allows configuring input lookups, input and output
 * transformations, and invoker wrapping. The method for which the invoker is built is
 * called the <em>target method</em>. If a lookup is configured, the corresponding input
 * of the invoker is ignored and an instance is looked up from the CDI container before
 * the target method is invoked. If a transformation is configured, the corresponding input
 * or output of the invoker is modified in certain way before or after the target method
 * is invoked. If a wrapper is configured, the invoker is passed to custom code for getting
 * invoked. As a result, the built {@code Invoker} instance may have more complex behavior
 * than just directly calling the target method.
 * <p>
 * Transformations and wrapping are expressed by ordinary methods that must have
 * a pre-defined signature, as described below. Such methods are called
 * <em>transformers</em> and <em>wrappers</em>.
 * <p>
 * Invokers may only be built during deployment. It is not possible to build new invokers
 * at application runtime.
 *
 * <h2>Example</h2>
 *
 * Before describing in detail how lookups, transformers and wrappers work, let's take
 * a look at an example. Say we have the following bean with a method:
 *
 * <pre>
 * class MyService {
 *     String hello(String name) {
 *         return "Hello " + name + "!";
 *     }
 * }
 * </pre>
 *
 * And we want to build an invoker that looks up {@code MyService} from the CDI container,
 * always passes the argument to {@code hello()} as all upper-case, and repeats the return
 * value twice. To transform the argument, we can use the zero-parameter method
 * {@code String.toUpperCase()}, and to transform the return value, we write a transformer
 * as a simple {@code static} method:
 *
 * <pre>
 * class Transformations {
 *     static String repeatTwice(String str) {
 *         return str + " " + str;
 *     }
 * }
 * </pre>
 *
 * Then, assuming we have obtained the {@code InvokerBuilder} for {@code MyService.hello()},
 * we can set up the lookup and transformations and build an invoker like so:
 *
 * <pre>
 * builder.setInstanceLookup()
 *         .setArgumentTransformer(0, String.class, "toUpperCase")
 *         .setReturnValueTransformer(Transformations.class, "repeatTwice")
 *         .build();
 * </pre>
 *
 * The resulting invoker will be equivalent to the following class:
 *
 * <pre>
 * class TheInvoker implements Invoker&lt;MyService, String&gt; {
 *     String invoke(MyService ignored, Object[] arguments) {
 *         MyService instance = CDI.current().select(MyService.class).get();
 *         String argument = (String) arguments[0];
 *         String transformedArgument = argument.toUpperCase();
 *         String result = instance.hello(transformedArgument);
 *         String transformedResult = Transformations.repeatTwice(result);
 *         return transformedResult;
 *     }
 * }
 * </pre>
 *
 * The caller of this invoker may pass {@code null} as the target instance, because
 * the invoker will lookup the target instance on its own. Therefore, calling
 * {@code invoker.invoke(null, new Object[] {"world"})} will return
 * {@code "Hello WORLD! Hello WORLD!"}.
 *
 * <h2>General requirements</h2>
 *
 * To refer to a transformer or a wrapper, all methods in this builder accept:
 * 1. the {@code Class} that that declares the method, and 2. the {@code String} name
 * of the method.
 * <p>
 * Transformers may be {@code static}, in which case they must be declared directly
 * on the given class, or they may be instance methods, in which case they may be declared
 * on the given class or inherited from any of its supertypes.
 * <p>
 * It is possible to register only one transformer of each kind, or for each argument
 * position in case of argument transformers. Attempting to register a second transformer
 * of the same kind, or for the same argument position, leads to an exception.
 * <p>
 * Wrappers must be {@code static} and must be declared directly on the given class.
 * It is possible to register only one wrapper. Attempting to register a second wrapper
 * leads to an exception.
 * <p>
 * It is a deployment problem if no method with given name and valid signature is found,
 * or if multiple methods with given name and different valid signatures are found. It is
 * a deployment problem if a registered transformer or wrapper is not {@code public}.
 * <p>
 * Transformers and wrappers may declare the {@code throws} clause. The declared exception
 * types are ignored when searching for the method.
 * <p>
 * For the purpose of the specification of transformers and wrappers below, the term
 * <em>any-type</em> is recursively defined as: the {@code java.lang.Object} class type,
 * or a type variable that has no bound, or a type variable whose first bound is
 * <em>any-type</em>.
 *
 * <h2>Input lookups</h2>
 *
 * For the target instance and for each argument, it is possible to specify that the value
 * passed to {@code Invoker.invoke()} should be ignored and a value should be looked up
 * from the CDI container instead.
 * <p>
 * For the target instance, a CDI lookup is performed with the required type equal to the bean
 * class of the bean to which the target method belongs, and required qualifiers equal to the set
 * of all qualifier annotations present on the bean class of the bean to which the target method
 * belongs. When the target method is {@code static}, the target instance lookup is skipped.
 * <p>
 * For an argument, a CDI lookup is performed with the required type equal to the type of
 * the corresponding parameter of the target method, and required qualifiers equal to the set
 * of all qualifier annotations present on the corresponding parameter of the target method.
 * <p>
 * Implementations are required to resolve all lookups during deployment. It is a deployment
 * problem if the lookup ends up unresolved or ambiguous.
 * <p>
 * If the looked up bean is {@code @Dependent}, it is guaranteed that the instance will be
 * destroyed after the target method is invoked but before the the invoker returns. The order
 * in which the looked up {@code @Dependent} beans are destroyed is not specified.
 * <p>
 * The order in which input lookups are performed in not specified and must not be relied upon.
 *
 * <h2>Input transformations</h2>
 *
 * The target method has 2 kinds of inputs: the target instance (unless the target method is
 * {@code static}, in which case the target instance is ignored and should be {@code null}
 * by convention) and arguments. These inputs correspond to the parameters of
 * {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}.
 * <p>
 * Each input can be transformed by a transformer that has one of the following signatures,
 * where {@code X} and {@code Y} are types:
 *
 * <ul>
 * <li>{@code static X transform(Y value)}</li>
 * <li>{@code static X transform(Y value, Consumer<Runnable> cleanup)}</li>
 * <li>{@code X transform()} &ndash; in this case, {@code Y} is the type of the class that
 * declares the transformer</li>
 * </ul>
 *
 * An input transformer must produce a type that can be consumed by the target method.
 * Specifically: when {@code X} is <em>any-type</em>, it is not type checked during deployment.
 * Otherwise, it is a deployment problem if {@code X} is not assignable to the corresponding type
 * in the declaration of the target method (that is the bean class in case of target instance
 * transformers, or the corresponding parameter type in case of argument transformers). {@code Y}
 * is not type checked during deployment, so that input transformers may consume arbitrary types.
 * <p>
 * When a transformer is registered for given input, it is called before the target method is
 * invoked, and the outcome of the transformer is used in the invocation instead of the original
 * value passed to the invoker by its caller.
 * <p>
 * If the transformer declares the {@code Consumer<Runnable>} parameter, and the execution
 * of the transformer calls {@code Consumer.accept()} with some {@code Runnable}, it is
 * guaranteed that the {@code Runnable} will be called after the target method is invoked but
 * before the invoker returns. These {@code Runnable}s are called <em>cleanup tasks</em>.
 * The order of cleanup task execution is not specified. Passing a {@code null} cleanup task
 * to the {@code Consumer} is permitted, but has no effect.
 * <p>
 * If an input transformation is configured for an input for which a lookup is also configured,
 * the lookup is performed first and the transformation is applied to the looked up value.
 * If the looked up bean for some input is {@code @Dependent}, it is guaranteed that all
 * cleanup tasks registered by a transformer for that input are called before that looked up
 * {@code @Dependent} bean is destroyed.
 * <p>
 * The order in which input transformations are performed in not specified and must not
 * be relied upon.
 *
 * <h2>Output transformations</h2>
 *
 * The target method has 2 kinds of outputs: the return value and the thrown exception. These
 * outputs correspond to the return value of {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}
 * or its thrown exception, respectively.
 * <p>
 * Each output can be transformed by a transformer that has one of the following signatures,
 * where {@code X} and {@code Y} are types:
 *
 * <ul>
 * <li>{@code static X transform(Y value)}</li>
 * <li>{@code X transform()} &ndash; in this case, {@code Y} is the type of the class that
 * declares the transformer</li>
 * </ul>
 *
 * An output transformer must consume a type that can be produced by the target method.
 * Specifically: when {@code Y} is <em>any-type</em>, it is not type checked during deployment.
 * Otherwise, it is a deployment problem if {@code Y} is not assignable from the return type of
 * the target method in case of return value transformers, or from {@code java.lang.Throwable}
 * in case of exception transformers. {@code X} is not type checked during deployment, so that
 * output transformers may produce arbitrary types.
 * <p>
 * When a transformer is registered for given output, it is called after the target method
 * is invoked, and the outcome of the transformer is passed back to the caller of the invoker
 * instead of the original output produced by the target method.
 * <p>
 * If the target method returns normally, any registered exception transformer is ignored; only
 * the return value transformer is called. The return value transformer may throw, in which case
 * the invoker will rethrow the exception. If the invoker is supposed to return normally,
 * the return value transformer must return normally.
 * <p>
 * Similarly, if the target method throws, any registered return value transformer is ignored;
 * only the exception transformer is called. The exception transformer may return normally,
 * in which case the invoker will return the return value of the exception transformer. If
 * the invoker is supposed to throw an exception, the exception transformer must throw.
 *
 * <h2>Invoker wrapping</h2>
 *
 * An invoker, possibly utilizing input lookups and input/output transformations, may be wrapped
 * by a custom piece of code for maximum flexibility. A wrapper must have the following signature,
 * where {@code X}, {@code Y} and {@code Z} are types:
 *
 * <ul>
 * <li>{@code static Z wrap(X instance, Object[] arguments, Invoker<X, Y> invoker)}</li>
 * </ul>
 *
 * A wrapper must operate on a matching instance type. Specifically: when {@code X} is
 * <em>any-type</em>, it is not type checked during deployment. Otherwise, it is a deployment
 * problem if {@code X} is not assignable from the class type of the bean class to which
 * the target method belongs. {@code Y} and {@code Z} are not type checked during deployment.
 * <p>
 * When a wrapper is registered, 2 invokers for the same method are created. The <em>inner</em>
 * invoker applies all lookups and transformations, as described in previous sections, and
 * invokes the target method. The <em>outer</em> invoker calls the wrapper with the passed
 * instance and arguments and an instance of the inner invoker. The outer invoker is returned
 * by this invoker builder.
 * <p>
 * In other words, the outer invoker is equivalent to the following class:
 *
 * <pre>
 * class InvokerWrapper implements Invoker&lt;X, Z&gt; {
 *     Z invoke(X instance, Object[] arguments) {
 *         // obtain the invoker as if no wrapper existed
 *         Invoker&lt;X, Y&gt; invoker = obtainInvoker();
 *         return SomeClass.wrap(instance, arguments, invoker);
 *     }
 * }
 * </pre>
 *
 * If the wrapper returns normally, the outer invoker returns its return value, unless the wrapper
 * is declared {@code void}, in which case the outer invoker returns {@code null}. If the wrapper
 * throws an exception, the outer invoker rethrows it directly.
 * <p>
 * The wrapper is supposed to call the invoker it is passed, but does not necessarily have to.
 * The wrapper may call the invoker multiple times. The wrapper must not use the invoker
 * in any other way; specifically, it is forbidden to store the invoker instance anywhere
 * or pass it to other methods that do not follow these rules. Doing so leads to non-portable
 * behavior.
 *
 * <h2>Type checking</h2>
 *
 * An invoker created by this builder has relaxed type checking rules, when compared to
 * the description in {@link Invoker#invoke(Object, Object[]) Invoker.invoke()}, depending
 * on configured lookups, transformers and wrapper. Some types are checked during
 * deployment, as described in previous sections. Other types are checked during invocation,
 * at the very least due to the type checks performed implicitly by the JVM. The lookups,
 * transformers and the wrapper must arrange the inputs and outputs so that when the method
 * is eventually invoked, the rules described in
 */
public interface WeldInvokerBuilder<T> extends InvokerBuilder<T> {
    @Override
    WeldInvokerBuilder<T> withInstanceLookup();

    @Override
    WeldInvokerBuilder<T> withArgumentLookup(int position);

    /**
     * Registers a transformer for the instance on which the invoker will be called.
     * <p>
     * Transformer method needs to be a static method of an accessible class.
     *
     * @param clazz Class which declares the transformer method
     * @param methodName transformer method name represented as a String
     * @return self
     */
    WeldInvokerBuilder<T> withInstanceTransformer(Class<?> clazz, String methodName);

    /**
     * Registers a transformer for a single argument of the target method.
     * <p>
     * Transformer method needs to be a static method of an accessible class.
     *
     * @param position position of an argument in the invoker's target method that should be transformed
     * @param clazz Class which declares the transformer method
     * @param methodName transformer method name represented as a String
     * @return self
     */
    WeldInvokerBuilder<T> withArgumentTransformer(int position, Class<?> clazz, String methodName);

    /**
     * Registers a transformer for return value of the target method.
     * <p>
     * Transformer method needs to be a static method of an accessible class.
     *
     * @param clazz Class which declares the transformer method
     * @param methodName transformer method name represented as a String
     * @return self
     */
    WeldInvokerBuilder<T> withReturnValueTransformer(Class<?> clazz, String methodName);

    /**
     * Registers an exception transformer for a possible exception thrown by the target method.
     * <p>
     * Transformer method needs to be a static method of an accessible class.
     *
     * @param clazz Class which declares the transformer method
     * @param methodName transformer method name represented as a String
     * @return self
     */
    WeldInvokerBuilder<T> withExceptionTransformer(Class<?> clazz, String methodName);

    /**
     * Registers an invocation wrapper for given invoker.
     * <p>
     * Transformer method needs to be a static method of an accessible class.
     *
     * @param clazz Class which declares the transformer method
     * @param methodName transformer method name represented as a String
     * @return self
     */
    WeldInvokerBuilder<T> withInvocationWrapper(Class<?> clazz, String methodName);
}
