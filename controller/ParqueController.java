package controller;

import java.util.InputMismatchException;
import model.Parque;
import model.PuntoAcceso;
import model.Visitante;
import view.ParqueView;

public class ParqueController {

    private Parque parqueActual;
    private ParqueView parqueView;
    private PuntoAccesoController puntoAccesoController;
    private VisitanteController visitanteController;

    public ParqueController(ParqueView parqueView) {
        this.parqueView = parqueView;
        this.parqueActual = null;
        this.puntoAccesoController = new PuntoAccesoController(parqueView);
        this.visitanteController = new VisitanteController(parqueView);
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
                            puntoAccesoController.addPuntoAcceso(parqueActual);
                            break;

                        case 3:
                            puntoAccesoController.findAllPuntosAcceso(parqueActual);
                            break;

                        case 4:
                            puntoAccesoController.findPuntoAccesoByPosition(parqueActual);
                            break;

                        case 5:
                            puntoAccesoController.updatePuntoAcceso(parqueActual);
                            break;

                        case 6:
                            puntoAccesoController.deletePuntoAcceso(parqueActual);
                            break;

                        case 7:
                            visitanteController.addVisitante(parqueActual);
                            break;

                        case 8:
                            visitanteController.findAllVisitantes(parqueActual);
                            break;

                        case 9:
                            visitanteController.findVisitanteById(parqueActual);
                            break;

                        case 10:
                            visitanteController.updateVisitante(parqueActual);
                            break;

                        case 11:
                            visitanteController.deleteVisitante(parqueActual);
                            break;

                        case 12:
                            mostrarReporte();
                            break;

                        case 13:
                            parqueView.mostrarMensaje("Programa finalizado, adiós :)");
                            break;

                        default:
                            parqueView.mostrarMensaje("Seleccione una opción entre 1 y 13");
                    }

                } catch (InputMismatchException e) {
                    parqueView.mostrarMensaje("Debe ingresar un número valido");
                    parqueView.limpiarEntrada();
                    opcion = 0;

                } catch (IllegalArgumentException e) {
                    parqueView.mostrarMensaje("error: " + e.getMessage());

                } catch (IndexOutOfBoundsException e) {
                    parqueView.mostrarMensaje("error: " + e.getMessage());
                }

            } while (opcion != 13);

        } finally {
            parqueView.cerrarScanner();
        }
    }

    private void crearParque() {
        String nombre = parqueView.leerTexto("Ingrese nombre del parque: ");
        String codigo = parqueView.leerTexto("Ingrese código de identificación: ");
        String encargado = parqueView.leerTexto("Ingrese nombre del encargado: ");

        parqueActual = new Parque(nombre, codigo, encargado);

        parqueView.mostrarMensaje("Parque creado correctamente ");
    }

    private void mostrarReporte() {
        validarParqueActual();

        parqueView.mostrarMensaje("\n---------- reporte del parque ----------");

        int puntosHabilitados = parqueActual.countPuntosAccesoHabilitados();
        int espaciosDisponibles = parqueActual.countEspaciosDisponibles();
        int cantidadVisitantes = parqueActual.countVisitantes();

        parqueView.mostrarMensaje("Puntos de acceso habilitados: " + puntosHabilitados);

        parqueView.mostrarMensaje("Espacios disponibles: " + espaciosDisponibles);

        PuntoAcceso puntoMayor = parqueActual.findPuntoAccesoMayorCapacidad();

        if (puntoMayor == null) {
            parqueView.mostrarMensaje("No hay puntos de acceso habilitados");
        } else {
            parqueView.mostrarMensaje("\nPunto de acceso con mayor capacidad:\n" + puntoMayor);
        }

        parqueView.mostrarMensaje("\nCantidad de visitantes registrados: " + cantidadVisitantes);

        Visitante visitanteMayorPuntaje = parqueActual.findVisitanteMayorPuntaje();

        if (visitanteMayorPuntaje == null) {
            parqueView.mostrarMensaje("No hay visitantes registrados");
        } else {
            parqueView.mostrarMensaje("\nVisitante con mayor puntaje:\n"+ visitanteMayorPuntaje);
        }

        Visitante visitanteMasAtracciones =
                parqueActual.findVisitanteMasAtracciones();

        if (visitanteMasAtracciones != null) {
            parqueView.mostrarMensaje("\nVisitante con más atracciones visitadas:\n" + visitanteMasAtracciones);
        }

        if (cantidadVisitantes > 0) {
            double promedioEdad = parqueActual.calculatePromedioEdad();
            parqueView.mostrarMensaje("\nPromedio de edad: " + promedioEdad);

        } else {
            parqueView.mostrarMensaje("No es posible calcular el promedio de edad");
        }

        parqueView.mostrarMensaje("-----------------------------------------");
    }

    private void validarParqueActual() {
        if (parqueActual == null) {
            throw new IllegalArgumentException(
                    "porfavor cree un parque primero"
            );
        }
    }
}