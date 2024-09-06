package main.java.utils;

import main.java.models.TV;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class FileManager {
    private static final String CONFIG_FILE_PATH = "resources/config.txt";
    private static final String EXPORT_FILE_PATH = "resources/exported_tvs.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void loadTVsFromFile(List<TV> tvList) {
        File file = new File(CONFIG_FILE_PATH);
        if (!file.exists()) {
            System.out.println("Archivo de configuración no encontrado. Se creará uno nuevo al guardar.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Leer la línea de fecha y hora
            if (line != null) {
                System.out.println("Configuración cargada desde: " + line);
            }

            while ((line = br.readLine()) != null) {
                String[] tvData = line.split(",");
                if (tvData.length == 6) {
                    String id = tvData[0];
                    String description = tvData[1];
                    String group = tvData[2];
                    int volume = Integer.parseInt(tvData[3]);
                    int channel = Integer.parseInt(tvData[4]);
                    boolean isOn = Boolean.parseBoolean(tvData[5]);

                    TV tv = new TV(id, description);
                    tv.setVolume(volume);
                    tv.setChannel(channel);
                    tv.setPower(isOn);
                    tvList.add(tv);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la configuración: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al procesar los datos del televisor: " + e.getMessage());
        }
    }

    public static void saveTVsToFile(List<TV> tvList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
            String currentDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());
            bw.write("Configuración guardada el: " + currentDate);
            bw.newLine();

            for (TV tv : tvList) {
                String tvData = String.join(",",
                        tv.getId(),
                        tv.getDescription(),
                        tv.getGroup(),
                        String.valueOf(tv.getVolume()),
                        String.valueOf(tv.getChannel()),
                        String.valueOf(tv.isOn()));
                bw.write(tvData);
                bw.newLine();
            }

            System.out.println("Configuración guardada correctamente en " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error al guardar la configuración: " + e.getMessage());
        }
    }

    public static void exportTVList(List<TV> tvList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EXPORT_FILE_PATH))) {
            for (TV tv : tvList) {
                String tvData = String.join(",",
                        tv.getId(),
                        tv.getDescription(),
                        tv.getGroup(),
                        String.valueOf(tv.getVolume()),
                        String.valueOf(tv.getChannel()),
                        String.valueOf(tv.isOn()));
                bw.write(tvData);
                bw.newLine();
            }

            System.out.println("Lista de televisores exportada correctamente a " + EXPORT_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error al exportar la lista de televisores: " + e.getMessage());
        }
    }

    public static void clearConfigFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
            bw.write("");  // Escribir un string vacío para limpiar el archivo
            System.out.println("Archivo de configuración limpiado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al limpiar el archivo de configuración: " + e.getMessage());
        }
    }
}
