package view;

import java.util.Scanner;

public class ParqueView {

    private Scanner scanner;

    public ParqueView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n------------------------------------");
        System.out.println(" Parque de atracciones");
        System.out.println("--------------------------------------");
        System.out.println("1. Agregar parque");
        System.out.println("2. Habilitar punto de acceso");
        System.out.println("3. Consultar puntos de acceso");
        System.out.println("4. Consultar un punto de acceso");
        System.out.println("5. Modificar punto de acceso");
        System.out.println("6. Cerrar punto de acceso");
        System.out.println("7. Agregar visitante");
        System.out.println("8. Consultar visitantes");
        System.out.println("9. Buscar visitante");
        System.out.println("10. Modificar visitante");
        System.out.println("11. Eliminar visitante");
        System.out.println("12. Mostrar reporte del parque");
        System.out.println("13. Salir");
        System.out.println("-------------------------------------");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine();

        return opcion;
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);

        return scanner.nextLine();
    }

    public int leerEntero(String mensaje) {
        System.out.print(mensaje);

        int numero = scanner.nextInt();
        scanner.nextLine();

        return numero;
    }

    public void limpiarEntrada() {
        scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}