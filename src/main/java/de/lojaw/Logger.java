package de.lojaw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Logger {
    private static final String LOG_FILE_PATH = "C:\\Users\\jpsch\\Desktop\\Minecraft\\JavaAgent\\src\\main\\java\\de\\lojaw\\log.txt";
    private static boolean checkForDuplicates = true; // Aktivieren/Deaktivieren der Überprüfung

    public static void logMessage(String message) {
        if (checkForDuplicates && logContainsMessage(message)) {
            return; // Wenn die Nachricht bereits vorhanden ist, fügen Sie sie nicht erneut hinzu
        }

        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            printWriter.println(timestamp + " - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean logContainsMessage(String message) {
        try (Stream<String> stream = Files.lines(Paths.get(LOG_FILE_PATH))) {
            return stream.anyMatch(line -> line.contains(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setCheckForDuplicates(boolean check) {
        checkForDuplicates = check;
    }
}
