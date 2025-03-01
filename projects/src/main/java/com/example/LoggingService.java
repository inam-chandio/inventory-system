package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggingService {
    private static final String LOG_FILE = "application.log";

    public static void logAction(String message) {
        logToFile("ACTION: " + message);
    }

    public static void logError(String message) {
        logToFile("ERROR: " + message);
    }

    public static void logWarning(String message) {
        logToFile("WARNING: " + message);
    }

    private static void logToFile(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            pw.println(timestamp + " " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
