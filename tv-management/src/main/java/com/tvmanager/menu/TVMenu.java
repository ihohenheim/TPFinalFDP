package com.tvmanager.menu;

import com.tvmanager.model.TV;
import com.tvmanager.service.TVService;

import java.util.Scanner;

public class TVMenu {
    private final TV tv;
    private final TVService tvService;
    private final Scanner scanner;

    public TVMenu(TV tv, TVService tvService) {
        this.tv = tv;
        this.tvService = tvService;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("----- Menú de Televisor -----");
            System.out.println("1. Apagar/Encender");
            System.out.println("2. Subir Volumen");
            System.out.println("3. Bajar Volumen");
            System.out.println("4. Seleccionar Canal");
            System.out.println("5. Subir Canal");
            System.out.println("6. Bajar Canal");
            System.out.println("7. Cambiar Descripción");
            System.out.println("8. Mostrar Estado");
            System.out.println("9. Eliminar del Grupo");
            System.out.println("10. Eliminar del Sistema");
            System.out.println("11. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    tvService.togglePower(tv);
                    System.out.println("Estado de energía cambiado.");
                    break;
                case 2:
                    tvService.increaseVolume(tv);
                    System.out.println("Volumen incrementado.");
                    break;
                case 3:
                    tvService.decreaseVolume(tv);
                    System.out.println("Volumen disminuido.");
                    break;
                case 4:
                    System.out.print("Ingrese el canal (1-999): ");
                    int channel = scanner.nextInt();
                    tvService.changeChannel(tv, channel);
                    System.out.println("Canal cambiado a " + channel);
                    break;
                case 5:
                    tvService.increaseChannel(tv);
                    System.out.println("Canal incrementado.");
                    break;
                case 6:
                    tvService.decreaseChannel(tv);
                    System.out.println("Canal disminuido.");
                    break;
                case 7:
                    System.out.print("Ingrese la nueva descripción: ");
                    String newDescription = scanner.nextLine();
                    tv.setDescription(newDescription);
                    System.out.println("Descripción cambiada.");
                    break;
                case 8:
                    System.out.println(tv);
                    break;
                case 9:
                    // Lógica para eliminar del grupo
                    break;
                case 10:
                    // Lógica para eliminar del sistema
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
