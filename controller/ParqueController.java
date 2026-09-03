package controller;

import model.Parque;
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
                            // crearParque();
                            break;

                        case 2:
                            // addPuntoAcceso();
                            break;

                        case 3:
                            // findAllPuntosAcceso();
                            break;

                        case 4:
                            // findPuntoAccesoByPosition();
                            break;

                        case 5:
                            // updatePuntoAcceso();
                            break;

                        case 6:
                            // deletePuntoAcceso();
                            break;

                        case 7:
                            // addVisitante();
                            break;

                        case 8:
                            // findAllVisitantes();
                            break;

                        case 9:
                            // findVisitanteById();
                            break;

                        case 10:
                            // updateVisitante();
                            break;

                        case 11:
                            // deleteVisitante();
                            break;

                        case 12:
                            // mostrarReporte();
                            break;

                        case 13:
                            parqueView.mostrarMensaje("Programa finalizado adios :)");
                            break;

                        default:
                            parqueView.mostrarMensaje("Seleccione una opción entre 1 y 13");
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
}