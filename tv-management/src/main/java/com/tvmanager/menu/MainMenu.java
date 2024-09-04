package com.tvmanager.menu;

import com.tvmanager.model.TV;
import com.tvmanager.model.TVGroup;
import com.tvmanager.service.TVService;
import com.tvmanager.service.GroupService;
import com.tvmanager.service.FileManager;

import java.util.Scanner;

public class MainMenu {
    private final TVService tvService;
    private final GroupService groupService;
    private final Scanner scanner;

    public MainMenu() {
        this.tvService = new TVService();
        this.groupService = new GroupService();
        this.scanner = new Scanner(System.in);
        FileManager.loadData(groupService.getAllGroups());
    }

    public void show() {
        while (true) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Crear Televisor");
            System.out.println("2. Crear Grupo");
            System.out.println("3. Listar Televisores");
            System.out.println("4. Seleccionar Televisor");
            System.out.println("5. Seleccionar Grupo");
            System.out.println("6. Guardar y Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (option) {
                case 1:
                    createTV();
                    break;
                case 2:
                    createGroup();
                    break;
                case 3:
                    listTVs();
                    break;
                case 4:
                    selectTV();
                    break;
                case 5:
                    selectGroup();
                    break;
                case 6:
                    FileManager.saveData(groupService.getAllGroups());
                    System.out.println("Configuración guardada. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private void createTV() {
        System.out.print("Ingrese la descripción del televisor: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese el grupo del televisor (por defecto #G00): ");
        String group = scanner.nextLine();

        // Verificar si el grupo existe
        if (group.isEmpty() || !groupService.getAllGroups().containsKey(group)) {
            System.out.println("Grupo no válido o no encontrado. Asignando al grupo por defecto #G00.");
            group = "#G00"; // Asignar al grupo por defecto si no se especifica uno válido
        }

        // Crear el televisor
        TV newTV = new TV(description, group);
        tvService.addTV(newTV); // Agregar el televisor a la lista de todos los televisores

        // Asociar el televisor al grupo
        groupService.addTVToGroup(newTV, group);
        System.out.println("Televisor creado con ID: " + newTV.getId() + " en el grupo " + group);
    }

    private void createGroup() {
        System.out.print("Ingrese el ID del grupo (#GYX, donde Y es un número 0-9, X es un número 1-9): ");
        String groupId = scanner.nextLine();
        System.out.print("Ingrese la descripción del grupo: ");
        String description = scanner.nextLine();
        TVGroup newGroup = new TVGroup(groupId, description);
        groupService.addGroup(newGroup);
        System.out.println("Grupo creado con ID: " + newGroup.getGroupId());
    }

    private void listTVs() {
        System.out.println("Listado de Televisores:");
        System.out.println(String.format("%-6s %-10s %-20s %-10s %-7s %-7s", "ID", "Grupo", "Descripción", "Estado",
                "Canal", "Volumen"));
        for (TV tv : tvService.getAllTVs()) {
            System.out.println(tv);
        }
    }

    private void selectTV() {
        System.out.print("Ingrese el ID del televisor a seleccionar: ");
        String tvId = scanner.nextLine();
        TV tv = tvService.findTVById(tvId);
        if (tv != null) {
            TVMenu tvMenu = new TVMenu(tv, tvService);
            tvMenu.show();
        } else {
            System.out.println("Televisor no encontrado.");
        }
    }

    private void selectGroup() {
        System.out.print("Ingrese el ID del grupo a seleccionar: ");
        String groupId = scanner.nextLine();
        TVGroup group = groupService.findGroupById(groupId);
        if (group != null) {
            // Aquí iría la lógica para manejar el menú de grupo
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }
}
