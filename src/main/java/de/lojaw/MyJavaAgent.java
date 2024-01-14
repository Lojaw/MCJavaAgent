package de.lojaw;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import java.lang.instrument.Instrumentation;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public class MyJavaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        TypePool typePool = TypePool.Default.ofSystemLoader();

/*        new ByteBuddy()
                .redefine(typePool.describe("ekt").resolve(),
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .constructor(ElementMatchers.takesArguments(5)) // Anzahl der Argumente des Konstruktors
                .intercept(Advice.to(de.lojaw.WindowInterceptor.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent());*/

        /*new ByteBuddy()
                .redefine(typePool.describe("com.mojang.blaze3d.systems.RenderSystem").resolve(),
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .visit(Advice.to(FlipFrameInterceptor.class).on(ElementMatchers.named("flipFrame").and(ElementMatchers.takesArguments(long.class))))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent());*/

/*        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.mojang.blaze3d.systems.RenderSystem"))
                .transform((builder, typeDescription, classLoader, javaModule, module) ->
                        builder.visit(Advice.to(FlipFrameInterceptor.class).on(ElementMatchers.named("flipFrame").and(ElementMatchers.takesArguments(long.class))))
                )
                .installOn(inst);*/

        new AgentBuilder.Default()
                .type(ElementMatchers.named("eqv"))
                .transform((builder, typeDescription, classLoader, javaModule, module) -> builder
                        .method(ElementMatchers.named("d")
                                .and(ElementMatchers.isPrivate())
                                .and(ElementMatchers.takesArguments(boolean.class)))
                        .intercept(Advice.to(TickInterceptor.class))
                )
                .installOn(inst);


    }
}

