package de.lojaw;

import net.bytebuddy.asm.Advice;

import java.awt.*;
import java.lang.reflect.Method;

public class TickInterceptor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Method method, @Advice.AllArguments Object[] args) {
        // Code vor der Methode
        Logger.logMessage("Vor der Methode: " + method.getName());

        // Rufen Sie hier drawStringOnScreen auf
        try {
            Object minecraftInstance = GameHelper.getMinecraftInstance();
            if (minecraftInstance != null) {
                GameHelper.drawStringOnScreen(minecraftInstance, "FPS", 10, 10, Color.WHITE.getRGB());
            }
        } catch (Exception e) {
            Logger.logMessage("Fehler in TickInterceptor enter: " + e.getMessage());
            e.printStackTrace();
        }

        // Optional: Zugriff auf die Methode Argumente
        if (args != null && args.length > 0) {
            Logger.logMessage("Argument Wert: " + args[0]);
        }
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Origin Method method) {
        // Code nach der Methode
        Logger.logMessage("Nach der Methode: " + method.getName());
    }
}
