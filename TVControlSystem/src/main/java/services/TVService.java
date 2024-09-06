package main.java.services;

import main.java.models.TV;
import main.java.utils.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TVService {
    private List<TV> tvList;

    public TVService() {
        this.tvList = new ArrayList<>();
        FileManager.loadTVsFromFile(tvList);
    }

    // Crear nuevo televisor
    public void createTV(Scanner scanner) {
        System.out.println("Ingrese la descripción del televisor:");
        System.out.print(">: ");
        String description = scanner.nextLine();
        String id = TV.generateID();

        System.out.println("ID generado para el televisor: " + id + ". ¿Desea modificarlo? (y/n)");
        System.out.print(">: ");
        String modifyId = scanner.nextLine();
        if (modifyId.equalsIgnoreCase("y")) {
            System.out.println("Ingrese el nuevo ID:");
            System.out.print(">: ");
            id = scanner.nextLine();
        }

        while (!isValidID(id) || isDuplicateID(id)) {
            System.out.println("El ID no es válido o ya existe. Ingrese un ID válido (AA00-ZZ99):");
            System.out.print(">: ");
            id = scanner.nextLine();
        }

        TV newTV = new TV(id, description);
        tvList.add(newTV);
        System.out.println("Televisor creado con éxito.");
    }

    // Validación del ID del televisor
    private boolean isValidID(String id) {
        return id.matches("[A-Z]{2}[0-9]{2}");
    }

    private boolean isDuplicateID(String id) {
        for (TV tv : tvList) {
            if (tv.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Listar televisores en formato de tabla
    public void listTVs() {
        String headerFormat = "%-6s | %-8s | %-20s | %-10s | %-6s | %-7s%n";
        String rowFormat = "%-6s | %-8s | %-20s | %-10s | %-6d | %-7d%n";

        System.out.println();
        System.out.println(String.format(headerFormat, "TV", "Grupo", "Descripción", "Estado", "Canal", "Volumen"));
        System.out.println("-------+----------+----------------------+------------+--------+---------");

        for (TV tv : tvList) {
            String estado = tv.isOn() ? "Encendido" : "Apagado";
            System.out.printf(rowFormat, tv.getId(), tv.getGroup(), tv.getDescription(), estado, tv.getChannel(), tv.getVolume());
        }
        System.out.println();
    }

    // Controlar un televisor individual
    public void controlIndividualTV(Scanner scanner) {
        System.out.println("Ingrese el ID del televisor que desea controlar:");
        System.out.print(">: ");
        String id = scanner.nextLine();

        TV tv = findTVById(id);
        if (tv == null) {
            System.out.println("Televisor no encontrado.");
            return;
        }

        boolean controlling = true;
        while (controlling) {
            System.out.println("Seleccione una acción para el televisor " + tv.getId() + ":");
            System.out.println("********************************************");
            System.out.println("1. Apagar/Encender");
            System.out.println("2. Subir volumen [+]");
            System.out.println("3. Bajar volumen [-]");
            System.out.println("4. Configurar volumen");
            System.out.println("5. Seleccionar canal");
            System.out.println("6. Subir canal [+]");
            System.out.println("7. Bajar canal [-]");
            System.out.println("8. Cambiar descripción");
            System.out.println("9. Mostrar estado");
            System.out.println("10. Guardar cambios");
            System.out.println("11. Eliminar del sistema");
            System.out.println("12. Volver al menú principal");
            System.out.print(">: ");

            int action = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (action) {
                case 1:
                    tv.togglePower();
                    break;
                case 2:
                    changeVolume(tv, tv.getVolume() + 1);
                    break;
                case 3:
                    changeVolume(tv, tv.getVolume() - 1);
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo volumen (0-15):");
                    System.out.print(">: ");
                    int volume = scanner.nextInt();
                    changeVolume(tv, volume);
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo canal (1-999):");
                    System.out.print(">: ");
                    int channel = scanner.nextInt();
                    changeChannel(tv, channel);
                    break;
                case 6:
                    changeChannel(tv, tv.getChannel() + 1);
                    break;
                case 7:
                    changeChannel(tv, tv.getChannel() - 1);
                    break;
                case 8:
                    System.out.println("Ingrese la nueva descripción:");
                    System.out.print(">: ");
                    String description = scanner.nextLine();
                    tv.setDescription(description);
                    break;
                case 9:
                    System.out.println(tv);
                    break;
                case 10:
                    saveConfiguration();
                    System.out.println("Cambios guardados para el televisor " + tv.getId());
                    break;
                case 11:
                    System.out.println("¿Está seguro de que desea eliminar este televisor? (y/n)");
                    System.out.print(">: ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        tvList.remove(tv);
                        System.out.println("Televisor eliminado.");
                        controlling = false;
                    }
                    break;
                case 12:
                    controlling = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Controlar todos los televisores
    public void controlAllTVs(Scanner scanner) {
        System.out.println("Seleccione una acción para todos los televisores:");
        System.out.println("********************************************");
        System.out.println("1. Apagar/Encender todos");
        System.out.println("2. Configurar volumen para todos");
        System.out.println("3. Subir volumen [+] todos");
        System.out.println("4. Bajar volumen [-] todos");
        System.out.println("5. Configurar canal para todos");
        System.out.println("6. Subir canal [+] todos");
        System.out.println("7. Bajar canal [-] todos");
        System.out.println("8. Guardar configuración para todos");
        System.out.println("9. Limpiar configuración (requiere confirmación)");
        System.out.print(">: ");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (action) {
            case 1:
                togglePowerAll();
                break;
            case 2:
                System.out.println("Ingrese el nuevo volumen (0-15):");
                System.out.print(">: ");
                int volume = scanner.nextInt();
                if (volume >= 0 && volume <= 15) {
                    setVolumeAll(volume);
                } else {
                    System.out.println("Volumen no válido. Debe estar entre 0 y 15.");
                }
                break;
            case 3:
                changeVolumeAll(1);
                break;
            case 4:
                changeVolumeAll(-1);
                break;
            case 5:
                System.out.println("Ingrese el nuevo canal para todos (1-999):");
                System.out.print(">: ");
                int channel = scanner.nextInt();
                changeChannelAll(channel);
                break;
            case 6:
                changeChannelAll(1);
                break;
            case 7:
                changeChannelAll(-1);
                break;
            case 8:
                saveConfiguration();
                System.out.println("Configuración guardada para todos los televisores.");
                break;
            case 9:
                System.out.println("¿Está seguro de que desea limpiar la configuración? (y/n)");
                System.out.print(">: ");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    tvList.clear();
                    saveConfiguration();
                    System.out.println("Configuración limpiada.");
                }
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Métodos para modificar atributos globales de todos los televisores
    public void setVolumeAll(int volume) {
        for (TV tv : tvList) {
            tv.setVolume(volume);
        }
        System.out.println("Volumen cambiado a " + volume + " para todos los televisores.");
    }

    public void togglePowerAll() {
        for (TV tv : tvList) {
            tv.togglePower();
        }
        System.out.println("Todos los televisores han cambiado de estado.");
    }

    public void changeVolumeAll(int adjustment) {
        for (TV tv : tvList) {
            int newVolume = tv.getVolume() + adjustment;
            if (newVolume >= 0 && newVolume <= 15) {
                tv.setVolume(newVolume);
            }
        }
        System.out.println("Volumen ajustado para todos los televisores.");
    }

    public void changeChannelAll(int channel) {
        if (channel < 1 || channel > 999) {
            System.out.println("Error: El canal debe estar entre 1 y 999.");
            return;
        }
        for (TV tv : tvList) {
            tv.setChannel(channel);
        }
        System.out.println("Canal cambiado a " + channel + " para todos los televisores.");
    }

    // Métodos para cambiar atributos individuales de un TV
    public void changeVolume(String tvId, int volume) {
        TV tv = findTVById(tvId);
        if (tv != null) {
            changeVolume(tv, volume);
        } else {
            System.out.println("Televisor no encontrado.");
        }
    }

    public void changeChannel(String tvId, int channel) {
        TV tv = findTVById(tvId);
        if (tv != null) {
            changeChannel(tv, channel);
        } else {
            System.out.println("Televisor no encontrado.");
        }
    }

    public void changeDescription(String tvId, String newDescription) {
        TV tv = findTVById(tvId);
        if (tv != null) {
            tv.setDescription(newDescription);
            System.out.println("Descripción cambiada para el televisor " + tvId);
        } else {
            System.out.println("Televisor no encontrado.");
        }
    }

    public void changeID(String currentID, String newID) {
        TV tv = findTVById(currentID);
        if (tv != null && isValidID(newID) && !isDuplicateID(newID)) {
            tv.setId(newID);
            System.out.println("ID cambiado para el televisor " + currentID);
        } else {
            System.out.println("Error al cambiar el ID. Verifique que el ID nuevo es válido y no duplicado.");
        }
    }

    public void changeState(String tvId, boolean isOn) {
        TV tv = findTVById(tvId);
        if (tv != null) {
            tv.setPower(isOn);
            System.out.println("Estado cambiado para el televisor " + tvId);
        } else {
            System.out.println("Televisor no encontrado.");
        }
    }

    public void changeStateAll(boolean isOn) {
        for (TV tv : tvList) {
            tv.setPower(isOn);
        }
        System.out.println("Estado cambiado para todos los televisores.");
    }

    // Guardar la configuración actual
    public void saveConfiguration() {
        FileManager.saveTVsToFile(tvList);
    }

    // Métodos privados para modificar atributos de un televisor específico
    private void changeVolume(TV tv, int volume) {
        if (volume >= 0 && volume <= 15) {
            tv.setVolume(volume);
            System.out.println("Volumen establecido a [ " + volume + " ] para el televisor " + tv.getId());
        } else {
            System.out.println("Volumen no válido. Debe estar entre 0 y 15.");
        }
    }
    
    public void createTVFromCommand(String description) {
        String id = TV.generateID();

        // Asignar la descripción ingresada por el usuario desde el comando
        System.out.println("ID generado para el televisor: " + id);
        System.out.println("Descripción: " + description);

        // Verificar si el ID es válido o único
        while (!isValidID(id) || isDuplicateID(id)) {
            id = TV.generateID();  // Generar uno nuevo si ya existe o no es válido
        }

        // Crear el nuevo televisor
        TV newTV = new TV(id, description);
        tvList.add(newTV);
        System.out.println("Televisor creado con éxito con la descripción: " + description);
    }

    private void changeChannel(TV tv, int channel) {
        if (channel >= 1 && channel <= 999) {
            tv.setChannel(channel);
            System.out.println("Canal establecido a [ " + channel + " ] para el televisor " + tv.getId());
        } else {
            System.out.println("Canal no válido. Debe estar entre 1 y 999.");
        }
    }

    private TV findTVById(String id) {
        for (TV tv : tvList) {
            if (tv.getId().equals(id)) {
                return tv;
            }
        }
        return null;
    }
}
