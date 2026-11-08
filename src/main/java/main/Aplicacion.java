package main;

import java.util.Scanner;
import model.core.Restaurante;

public class Aplicacion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Sabor & Tradición", "900.123.456-7", "Armenia, Quindío");
        int opcion;
        do {
            System.out.println("\n---- Menú SABOR & TRADICIÓN ----");
            System.out.println("1. Registrar producto en el menú");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Calcular precio de venta de productos");
            System.out.println("4. Verificar condiciones de calidad de productos");
            System.out.println("5. Consultar productos disponibles y rechazados");
            System.out.println("------------------------------------");
            System.out.println("6. Crear pedido");
            System.out.println("7. Gestionar servicio de domicilio");
            System.out.println("8. Confirmar pedido");
            System.out.println("9. Calcular valor total de un pedido");
            System.out.println("------------------------------------");
            System.out.println("10. Consultar historial de pedidos de un cliente");
            System.out.println("11. Actualizar estado de un pedido");
            System.out.println("12. Generar reporte general de ventas");
            System.out.println("13. Salir");
            System.out.print("\nIngrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("--- 1. REGISTRAR PRODUCTO EN EL MENÚ ---");
                    break;
                case 2:
                    System.out.println("--- 2. REGISTRAR CLIENTE ---");
                    break;
                case 3:
                    System.out.println("--- 3. CALCULAR PRECIO DE VENTA DE PRODUCTOS ---");
                    break;
                case 4:
                    System.out.println("--- 4. VERIFICAR CONDICIONES DE CALIDAD ---");
                    break;
                case 5:
                    System.out.println("--- 5. CONSULTAR PRODUCTOS DISPONIBLES Y RECHAZADOS ---");
                    break;
                case 6:
                    System.out.println("--- 6. CREAR PEDIDO ---");
                    break;
                case 7:
                    System.out.println("--- 7. GESTIONAR SERVICIO DE DOMICILIO ---");
                    break;
                case 8:
                    System.out.println("--- 8. CONFIRMAR PEDIDO ---");
                    break;
                case 9:
                    System.out.println("--- 9. CALCULAR VALOR TOTAL DE UN PEDIDO ---");
                    break;
                case 10:
                    System.out.println("--- 10. CONSULTAR HISTORIAL DE PEDIDOS DE UN CLIENTE ---");
                    break;
                case 11:
                    System.out.println("--- 11. ACTUALIZAR ESTADO DE UN PEDIDO ---");
                    break;
                case 12:
                    System.out.println("--- 12. GENERAR REPORTE GENERAL DE VENTAS ---");
                    break;
                case 13:
                    System.out.println("--- SALIENDO DEL SISTEMA... ---");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 13);
        scanner.close();
    }
}
