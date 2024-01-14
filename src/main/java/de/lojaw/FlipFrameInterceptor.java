package de.lojaw;

import net.bytebuddy.asm.Advice;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;

public class FlipFrameInterceptor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Method method) {
        // Code vor der Methode
        Logger.logMessage("Vor der Methode: " + method.getName());
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Origin Method method) {
        Logger.logMessage("OnMethodExit - Thread Name: " + Thread.currentThread().getName());
        Logger.logMessage("OnMethodExit - Thread ID: " + Thread.currentThread().getId());
        try {
            // Prüfen, ob ein OpenGL-Kontext aktuell ist
            if (GL.getCapabilities() == null) {
                Logger.logMessage("Kein aktueller OpenGL-Kontext verfügbar.");
                return;
            }

            // OpenGL-Befehle zum Zeichnen einer Linie
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3f(-0.8f, -0.8f, 0f); // Startpunkt
            GL11.glVertex3f(0.8f, 0.8f, 0f);   // Endpunkt
            GL11.glEnd();

            // Überprüfen auf OpenGL-Fehler
            int error = GL11.glGetError();
            if (error != GL11.GL_NO_ERROR) {
                Logger.logMessage("OpenGL-Fehler: " + error);
            }
        } catch (Exception e) {
            Logger.logMessage("Fehler beim Ausführen von OpenGL-Befehlen: " + e.getMessage());
        } finally {
            // Code nach der Methode
            Logger.logMessage("Nach der Methode: " + method.getName());
        }
    }
}