package de.lojaw;

import net.bytebuddy.asm.Advice;
import java.lang.reflect.Method;

public class FlipFrameInterceptor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Method method) {
        // Code vor der Methode
        Logger.logMessage("Vor der Methode: " + method.getName());
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Origin Method method) {
        // Code nach der Methode
        Logger.logMessage("Nach der Methode: " + method.getName());
    }
}