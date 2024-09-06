package main.java;

import main.java.services.TVService;
import main.java.commands.CommandHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TVService tvService = new TVService();
        CommandHandler commandHandler = new CommandHandler(tvService);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("***********************************");
            System.out.println("1. Crear nuevo Televisor");
            System.out.println("2. Listar Televisores");
            System.out.println("3. Controlar Televisor Individual");
            System.out.println("4. Controlar Todos los Televisores");
            System.out.println("5. Guardar configuración");
            System.out.println("6. Línea de Comandos");
            System.out.println("7. Salir");
            System.out.print(">: ");  // Indicador de espera

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    tvService.createTV(scanner);
                    break;
                case 2:
                    tvService.listTVs();
                    break;
                case 3:
                    tvService.controlIndividualTV(scanner);
                    break;
                case 4:
                    tvService.controlAllTVs(scanner);
                    break;
                case 5:
                    tvService.saveConfiguration();
                    break;
                case 6:
                    commandHandler.startCommandLine(scanner);
                    break;
                case 7:
                    System.out.println("¿Desea guardar la configuración antes de salir? (y/n)");
                    String saveBeforeExit = scanner.nextLine();
                    if (saveBeforeExit.equalsIgnoreCase("y")) {
                        tvService.saveConfiguration();
                    }
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }
}