package controller;

import constants.EstadoPunto;
import model.Parque;
import model.PuntoAcceso;
import model.Visitante;
import view.ParqueView;

import java.util.InputMismatchException;


public class ParqueController {

    private Parque parqueActual;
    private ParqueView parqueView;

    public ParqueController(ParqueView parqueView) {
        this.parqueView = parqueView;
        this.parqueActual = null;
    }

    public void iniciar() {
        int opcion = 0;

        try {
            do {
                try {
                    parqueView.mostrarMenu();
                    opcion = parqueView.leerOpcion();

                    switch (opcion) {

                            case 1:
                                crearParque();
                                break;

                            case 2:
                                addPuntoAcceso();
                                break;

                            case 3:
                                findAllPuntosAcceso();
                                break;

                            case 4:
                                findPuntoAccesoByPosition();
                                break;

                            case 5:
                                updatePuntoAcceso();
                                break;

                            case 6:
                                deletePuntoAcceso();
                                break;

                            case 7:
                                addVisitante();
                                break;

                            case 8:
                                findAllVisitantes();
                                break;

                            case 9:
                                findVisitanteById();
                                break;

                            case 10:
                                updateVisitante();
                                break;

                            case 11:
                                deleteVisitante();
                                break;

                            case 12:
                                mostrarReporte();
                                break;

                            case 13:
                                parqueView.mostrarMensaje("Programa finalizado adios :)");
                                break;

                            default:
                                parqueView.mostrarMensaje(
                                        "Seleccione una opción entre 1 y 13"
                                );
                        }

                } catch (InputMismatchException e) {
                    parqueView.mostrarMensaje("debe ingresar un número");
                    parqueView.limpiarEntrada();
                    opcion = 0;
                } 
            } while (opcion != 13);
        } finally {
            parqueView.cerrarScanner();
        }
    }

    // Métodos que están dentro del switch 

        private void crearParque() {
        try {
            String nombre = parqueView.leerTexto("Ingrese nombre del parque: ");
            String codigo = parqueView.leerTexto("Ingrese código de identificación: ");
            String encargado = parqueView.leerTexto("Ingrese nombre del encargado: ");

            parqueActual = new Parque(nombre, codigo, encargado);

            parqueView.mostrarMensaje("Parque creado correctamente");

        } catch (IllegalArgumentException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void addPuntoAcceso() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            int posicion = parqueView.leerEntero("Ingrese posición (0-4): ");
            String codigo = parqueView.leerTexto("Ingrese código: ");
            String nombre = parqueView.leerTexto("Ingrese nombre: ");
            String ubicacion = parqueView.leerTexto("Ingrese ubicación: ");
            int capacidad = parqueView.leerEntero(
                    "Ingrese capacidad máxima por hora: "
            );

            PuntoAcceso puntoAcceso = new PuntoAcceso(
                    codigo,
                    nombre,
                    ubicacion,
                    capacidad,
                    EstadoPunto.ACTIVO
            );

            parqueActual.addPuntoAcceso(posicion, puntoAcceso);

            parqueView.mostrarMensaje(
                    "Punto de acceso habilitado correctamente"
            );

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void findAllPuntosAcceso() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        PuntoAcceso[] puntos = parqueActual.findAllPuntosAcceso();

        boolean hayPuntos = false;

        for (int i = 0; i < puntos.length; i++) {

            if (puntos[i] != null) {

                parqueView.mostrarMensaje(
                        "\nPosición: " + i +
                        "\n" + puntos[i]
                );

                hayPuntos = true;
            }
        }

        if (!hayPuntos) {
            parqueView.mostrarMensaje(
                    "No hay puntos de acceso habilitados"
            );
        }
    }


    private void findPuntoAccesoByPosition() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            int posicion = parqueView.leerEntero(
                    "Ingrese posición (0-4): "
            );

            PuntoAcceso puntoAcceso =
                    parqueActual.findPuntoAccesoByPosition(posicion);

            if (puntoAcceso == null) {

                parqueView.mostrarMensaje(
                        "No existe un punto de acceso en esa posición"
                );

            } else {

                parqueView.mostrarMensaje(
                        puntoAcceso.toString()
                );
            }

        } catch (IndexOutOfBoundsException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void updatePuntoAcceso() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            int posicion = parqueView.leerEntero(
                    "Ingrese posición (0-4): "
            );

            int capacidad = parqueView.leerEntero(
                    "Ingrese nueva capacidad máxima por hora: "
            );

            int opcionEstado = parqueView.leerEntero(
                    "Ingrese estado (1 = ACTIVO, 2 = INACTIVO): "
            );

            EstadoPunto estado;

            if (opcionEstado == 1) {
                estado = EstadoPunto.ACTIVO;

            } else if (opcionEstado == 2) {
                estado = EstadoPunto.INACTIVO;

            } else {
                throw new IllegalArgumentException(
                        "Estado inválido"
                );
            }

            parqueActual.updatePuntoAcceso(
                    posicion,
                    capacidad,
                    estado
            );

            parqueView.mostrarMensaje(
                    "Punto de acceso modificado correctamente"
            );

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void deletePuntoAcceso() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            int posicion = parqueView.leerEntero(
                    "Ingrese posición (0-4): "
            );

            parqueActual.deletePuntoAcceso(posicion);

            parqueView.mostrarMensaje(
                    "Punto de acceso cerrado correctamente"
            );

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void addVisitante() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            String codigo = parqueView.leerTexto(
                    "Ingrese código de entrada: "
            );

            String nombre = parqueView.leerTexto(
                    "Ingrese nombre: "
            );

            int edad = parqueView.leerEntero(
                    "Ingrese edad: "
            );

            int atracciones = parqueView.leerEntero(
                    "Ingrese cantidad de atracciones visitadas: "
            );

            int puntos = parqueView.leerEntero(
                    "Ingrese puntos acumulados: "
            );

            Visitante visitante = new Visitante(
                    codigo,
                    nombre,
                    edad,
                    atracciones,
                    puntos
            );

            parqueActual.addVisitante(visitante);

            parqueView.mostrarMensaje(
                    "Visitante registrado correctamente"
            );

        } catch (IllegalArgumentException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void findAllVisitantes() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        if (parqueActual.findAllVisitantes().isEmpty()) {

            parqueView.mostrarMensaje(
                    "No hay visitantes registrados"
            );

            return;
        }

        for (Visitante visitante : parqueActual.findAllVisitantes()) {

            parqueView.mostrarMensaje(
                    "\n" + visitante
            );
        }
    }


    private void findVisitanteById() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            String codigo = parqueView.leerTexto(
                    "Ingrese código de entrada: "
            );

            Visitante visitante =
                    parqueActual.findVisitanteById(codigo);

            if (visitante == null) {

                parqueView.mostrarMensaje(
                        "No existe un visitante con ese código"
                );

            } else {

                parqueView.mostrarMensaje(
                        visitante.toString()
                );
            }

        } catch (IllegalArgumentException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void updateVisitante() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            String codigo = parqueView.leerTexto(
                    "Ingrese código del visitante: "
            );

            String nombre = parqueView.leerTexto(
                    "Ingrese nuevo nombre: "
            );

            int edad = parqueView.leerEntero(
                    "Ingrese nueva edad: "
            );

            int atracciones = parqueView.leerEntero(
                    "Ingrese nueva cantidad de atracciones visitadas: "
            );

            int puntos = parqueView.leerEntero(
                    "Ingrese nuevos puntos acumulados: "
            );

            parqueActual.updateVisitante(
                    codigo,
                    nombre,
                    edad,
                    atracciones,
                    puntos
            );

            parqueView.mostrarMensaje(
                    "Visitante modificado correctamente"
            );

        } catch (IllegalArgumentException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void deleteVisitante() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        try {
            String codigo = parqueView.leerTexto(
                    "Ingrese código del visitante: "
            );

            parqueActual.deleteVisitante(codigo);

            parqueView.mostrarMensaje(
                    "Visitante eliminado correctamente"
            );

        } catch (IllegalArgumentException e) {
            parqueView.mostrarMensaje(e.getMessage());
        }
    }


    private void mostrarReporte() {
        if (parqueActual == null) {
            parqueView.mostrarMensaje("Primero debe crear un parque");
            return;
        }

        parqueView.mostrarMensaje(
                "\n---------- REPORTE DEL PARQUE ----------"
        );

        parqueView.mostrarMensaje(
                "Puntos de acceso habilitados: "
                        + parqueActual.countPuntosAccesoHabilitados()
        );

        parqueView.mostrarMensaje(
                "Espacios disponibles: "
                        + parqueActual.countEspaciosDisponibles()
        );


        PuntoAcceso puntoMayor =
                parqueActual.findPuntoAccesoMayorCapacidad();

        if (puntoMayor != null) {

            parqueView.mostrarMensaje(
                    "\nPunto de acceso con mayor capacidad:\n"
                            + puntoMayor
            );

        } else {

            parqueView.mostrarMensaje(
                    "No hay puntos de acceso habilitados"
            );
        }


        parqueView.mostrarMensaje(
                "\nCantidad de visitantes registrados: "
                        + parqueActual.countVisitantes()
        );


        Visitante mayorPuntaje =
                parqueActual.findVisitanteMayorPuntaje();

        if (mayorPuntaje != null) {

            parqueView.mostrarMensaje(
                    "\nVisitante con mayor cantidad de puntos:\n"
                            + mayorPuntaje
            );

        } else {

            parqueView.mostrarMensaje(
                    "No hay visitantes registrados"
            );
        }


        Visitante masAtracciones =
                parqueActual.findVisitanteMasAtracciones();

        if (masAtracciones != null) {

            parqueView.mostrarMensaje(
                    "\nVisitante con más atracciones visitadas:\n"
                            + masAtracciones
            );
        }


        parqueView.mostrarMensaje(
                "\nPromedio de edad: "
                        + parqueActual.calculatePromedioEdad()
        );

        parqueView.mostrarMensaje(
                "----------------------------------------"
        );
    }
}