package main.java.commands;

import main.java.services.TVService;

import java.util.Arrays;
import java.util.Scanner;

public class CommandHandler {
    private TVService tvService;

    public CommandHandler(TVService tvService) {
        this.tvService = tvService;
    }

    public void startCommandLine(Scanner scanner) {
        System.out.println("Entrando en línea de comandos. Escriba 'man' para obtener una lista de comandos. \nEscriba 'exit' para volver al menú principal.");

        while (true) {
            System.out.print(">: ");  // Indicador de espera de comando
            String input = scanner.nextLine().trim();

            // Comando 'exit' para salir de la línea de comandos
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Comando 'man' para mostrar la lista de comandos disponibles
            if (input.equalsIgnoreCase("man")) {
                showCommandList();
                continue;
            }

            // Lógica para manejar los otros comandos
            handleCommand(input);
        }
    }

    // Método para mostrar la lista de comandos disponibles
    private void showCommandList() {
        System.out.println("Lista de comandos y sus argumentos:");
        System.out.println("  CVol <ID> <volumen>          - Cambia el volumen de un televisor específico.");
        System.out.println("  CVolAll <volumen>            - Cambia el volumen de todos los televisores.");
        System.out.println("  CCha <ID> <canal>            - Cambia el canal de un televisor específico.");
        System.out.println("  CChaAll <canal>              - Cambia el canal de todos los televisores.");
        System.out.println("  CDes <ID> <descripcion>      - Cambia la descripción de un televisor específico.");
        System.out.println("  CID <ID_actual> <ID_nuevo>   - Cambia el ID de un televisor.");
        System.out.println("  CEst <ID> <ON/OFF>           - Cambia el estado de encendido/apagado de un televisor.");
        System.out.println("  CEstAll <ON/OFF>             - Cambia el estado de todos los televisores.");
        System.out.println("  showAll                      - Muestra la lista de todos los televisores.");
        System.out.println("  SetNTV <Descripción>         - Crea un nuevo televisor con la descripción proporcionada.");
        System.out.println("  save                         - Guarda la configuración actual.");
        System.out.println("  exit                         - Salir de la línea de comandos.");
    }

    // Método que maneja los comandos ingresados
    private void handleCommand(String input) {
        // Separar el comando y sus argumentos
        String[] commandParts = input.split(" ");
        String command = commandParts[0].toLowerCase();

        switch (command) {
            case "cvol":
                // Cambiar el volumen de un televisor específico
                if (commandParts.length == 3) {
                    String tvId = commandParts[1];
                    try {
                        int volume = Integer.parseInt(commandParts[2]);
                        if (volume >= 0 && volume <= 15) {
                            tvService.changeVolume(tvId, volume);
                        } else {
                            System.out.println("Error: El volumen debe estar entre 0 y 15.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Volumen no válido. Debe ser un número.");
                    }
                } else {
                    System.out.println("Uso: CVol <ID> <volumen>");
                }
                break;
            case "cvolall":
                // Cambiar el volumen de todos los televisores
                if (commandParts.length == 2) {
                    try {
                        int volume = Integer.parseInt(commandParts[1]);
                        if (volume >= 0 && volume <= 15) {
                            tvService.setVolumeAll(volume);
                        } else {
                            System.out.println("Error: El volumen debe estar entre 0 y 15.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Volumen no válido. Debe ser un número.");
                    }
                } else {
                    System.out.println("Uso: CVolAll <volumen>");
                }
                break;
            case "ccha":
                // Cambiar el canal de un televisor específico
                if (commandParts.length == 3) {
                    String tvId = commandParts[1];
                    try {
                        int channel = Integer.parseInt(commandParts[2]);
                        if (channel >= 1 && channel <= 999) {
                            tvService.changeChannel(tvId, channel);
                        } else {
                            System.out.println("Error: El canal debe estar entre 1 y 999.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Canal no válido. Debe ser un número.");
                    }
                } else {
                    System.out.println("Uso: CCha <ID> <canal>");
                }
                break;
            case "cchaall":
                // Cambiar el canal de todos los televisores
                if (commandParts.length == 2) {
                    try {
                        int channel = Integer.parseInt(commandParts[1]);
                        if (channel >= 1 && channel <= 999) {
                            tvService.changeChannelAll(channel);
                        } else {
                            System.out.println("Error: El canal debe estar entre 1 y 999.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Canal no válido. Debe ser un número.");
                    }
                } else {
                    System.out.println("Uso: CChaAll <canal>");
                }
                break;
            case "cdes":
                // Cambiar la descripción de un televisor específico
                if (commandParts.length >= 3) {
                    String tvId = commandParts[1];
                    String description = String.join(" ", Arrays.copyOfRange(commandParts, 2, commandParts.length));
                    tvService.changeDescription(tvId, description);
                } else {
                    System.out.println("Uso: CDes <ID> <descripcion>");
                }
                break;
            case "cid":
                // Cambiar el ID de un televisor
                if (commandParts.length == 3) {
                    String currentId = commandParts[1];
                    String newId = commandParts[2];
                    tvService.changeID(currentId, newId);
                } else {
                    System.out.println("Uso: CID <ID_actual> <ID_nuevo>");
                }
                break;
            case "cest":
                // Cambiar el estado de un televisor específico
                if (commandParts.length == 3) {
                    String tvId = commandParts[1];
                    String state = commandParts[2].toUpperCase();
                    if (state.equals("ON") || state.equals("OFF")) {
                        tvService.changeState(tvId, state.equals("ON"));
                    } else {
                        System.out.println("Error: El estado debe ser ON o OFF.");
                    }
                } else {
                    System.out.println("Uso: CEst <ID> <ON/OFF>");
                }
                break;
            case "cestall":
                // Cambiar el estado de todos los televisores
                if (commandParts.length == 2) {
                    String state = commandParts[1].toUpperCase();
                    if (state.equals("ON") || state.equals("OFF")) {
                        tvService.changeStateAll(state.equals("ON"));
                    } else {
                        System.out.println("Error: El estado debe ser ON o OFF.");
                    }
                } else {
                    System.out.println("Uso: CEstAll <ON/OFF>");
                }
                break;
            case "showall":
                // Mostrar la lista de todos los televisores
                tvService.listTVs();
                break;
            case "setntv":
                if (commandParts.length >= 2) {
                    // Unir todas las palabras restantes como la descripción
                    String description = String.join(" ", Arrays.copyOfRange(commandParts, 1, commandParts.length));
                    tvService.createTVFromCommand(description);
                } else {
                    System.out.println("Uso: SetNTV <Descripción>");
                }
                break;
            case "save":
                tvService.saveConfiguration();
                System.out.println("Configuración guardada.");
                break;
            default:
                System.out.println("Comando no reconocido. Escriba 'man' para ver la lista de comandos.");
        }
    }
}
