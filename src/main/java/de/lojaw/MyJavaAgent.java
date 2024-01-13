package de.lojaw;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;
import java.lang.instrument.Instrumentation;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

public class MyJavaAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        TypePool typePool = TypePool.Default.ofSystemLoader();

        new ByteBuddy()
                .redefine(typePool.describe("ekt").resolve(),
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .constructor(ElementMatchers.takesArguments(5)) // Anzahl der Argumente des Konstruktors
                .intercept(Advice.to(de.lojaw.MeinInterceptor.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }
}

