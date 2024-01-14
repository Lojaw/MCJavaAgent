package de.lojaw;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GameHelper {

    // Statische Methode, um die Minecraft-Instanz zu erhalten
    public static Object getMinecraftInstance() {
        Logger.logMessage("Aufruf von getMinecraftInstance");
        try {
            // Laden Sie die Klasse mit dem obfuskierten Namen
            Class<?> minecraftClass = Class.forName("eqv");

            // Rufen Sie die obfuskierte statische Methode auf
            // Ersetzen Sie "O" mit dem tatsächlichen obfuskierten Methodennamen
            return minecraftClass.getDeclaredMethod("O").invoke(null);
        } catch (Exception e) {
            Logger.logMessage("Fehler in getMinecraftInstance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Ergänzen Sie die Methode getGuiGraphics
    private static Object getGuiGraphics(Object minecraftInstance) {
        Logger.logMessage("Aufruf von getGuiGraphics");
        try {
            Class<?> guiGraphicsClass = Class.forName("esf"); // Ersetzen Sie "esf" durch den aktuellen obfuskierten Namen

            // Holen Sie die PoseStack- und BufferSource-Instanzen
            Object poseStackInstance = getPoseStackInstance();
            Object bufferSourceInstance = getBufferSourceInstance();

            // Definieren des Konstruktors für guiGraphicsClass
            Constructor<?> constructor = guiGraphicsClass.getDeclaredConstructor(minecraftInstance.getClass(), poseStackInstance.getClass(), bufferSourceInstance.getClass());

            // Erstellen einer neuen Instanz von guiGraphics
            Object guiGraphicsInstance = constructor.newInstance(minecraftInstance, poseStackInstance, bufferSourceInstance);

            return guiGraphicsInstance; // Rückgabe des guiGraphics-Objekts
        } catch (Exception e) {
            Logger.logMessage("Fehler in getGuiGraphics: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Object getPoseStackInstance() {
        Logger.logMessage("Aufruf von getPoseStackInstance");
        try {
            // Laden Sie die Klasse mit dem obfuskierten Namen
            Class<?> poseStackClass = Class.forName("elp"); // Ersetzen Sie "elp" durch den aktuellen obfuskierten Namen

            // Hier nehmen wir an, dass PoseStack einen parameterlosen Konstruktor hat
            // Wenn der Konstruktor Parameter erfordert, müssen Sie diese hier angeben
            Constructor<?> constructor = poseStackClass.getDeclaredConstructor();

            // Erstellen und zurückgeben einer neuen Instanz von PoseStack
            return constructor.newInstance();
        } catch (Exception e) {
            Logger.logMessage("Fehler in getPoseStackInstance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Object getBufferSourceInstance() {
        Logger.logMessage("Aufruf von getBufferSourceInstance");
        try {
            // Laden Sie die Klasse mit dem obfuskierten Namen
            Class<?> bufferSourceClass = Class.forName("foe"); // Ersetzen Sie "foe" durch den aktuellen obfuskierten Namen

            // Erstellen Sie eine Instanz von BufferBuilder
            Object bufferBuilderInstance = getBufferBuilderInstance(2097152); // Verwenden Sie eine geeignete Größe

            // Rufen Sie die statische Methode 'immediate' oder deren obfuskierte Entsprechung auf
            Method immediateMethod = bufferSourceClass.getDeclaredMethod("immediate", bufferBuilderInstance.getClass());
            return immediateMethod.invoke(null, bufferBuilderInstance);

        } catch (Exception e) {
            Logger.logMessage("Fehler in getBufferSourceInstance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Object getBufferBuilderInstance(int size) {
        Logger.logMessage("Aufruf von getBufferBuilderInstance");
        try {
            // Laden Sie die Klasse mit dem obfuskierten Namen
            Class<?> bufferBuilderClass = Class.forName("elk"); // Ersetzen Sie "elk" durch den aktuellen obfuskierten Namen

            // Erstellen Sie eine Instanz von BufferBuilder mit der angegebenen Größe
            Constructor<?> constructor = bufferBuilderClass.getDeclaredConstructor(int.class);
            return constructor.newInstance(size);

        } catch (Exception e) {
            Logger.logMessage("Fehler in getBufferBuilderInstance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void drawStringOnScreen(Object minecraftInstance, String text, int x, int y, int color) {
        Logger.logMessage("Aufruf von drawStringOnScreen");
        try {
            // Erhalten Sie die guiGraphics- und font-Instanzen
            Object guiGraphicsInstance = getGuiGraphics(minecraftInstance);
            Object fontInstance = getFontInstance(minecraftInstance);

            // Stellen Sie sicher, dass beide Instanzen nicht null sind
            if (guiGraphicsInstance == null || fontInstance == null) {
                throw new IllegalStateException("guiGraphicsInstance oder fontInstance ist null");
            }

            // Rufen Sie die drawString-Methode auf
            Method drawStringMethod = guiGraphicsInstance.getClass().getDeclaredMethod("a", fontInstance.getClass(), String.class, int.class, int.class, int.class);
            drawStringMethod.invoke(guiGraphicsInstance, fontInstance, text, x, y, color);
        } catch (Exception e) {
            Logger.logMessage("Fehler in drawStringOnScreen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Object getFontInstance(Object minecraftInstance) {
        Logger.logMessage("Aufruf von getFontInstance");
        try {
            // Ersetzen Sie "esd" und "h" durch die aktuellen obfuskierten Namen
            Field fontField = minecraftInstance.getClass().getDeclaredField("h");
            fontField.setAccessible(true); // Falls das Feld nicht öffentlich ist
            return fontField.get(minecraftInstance);
        } catch (Exception e) {
            Logger.logMessage("Fehler in getFontInstance: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
