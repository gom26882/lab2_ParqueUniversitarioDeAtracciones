package controller;

import constants.EstadoPunto;
import model.Parque;
import model.PuntoAcceso;
import view.ParqueView;

public class PuntoAccesoController {

    private ParqueView parqueView;

    public PuntoAccesoController(ParqueView parqueView) {
        this.parqueView = parqueView;
    }

    public void addPuntoAcceso(Parque parque) {
        validarParque(parque);

        int posicion = parqueView.leerEntero("Ingrese posición (0-4): ");
        String codigo = parqueView.leerTexto("Ingrese código: ");
        String nombre = parqueView.leerTexto("Ingrese nombre: ");
        String ubicacion = parqueView.leerTexto("Ingrese ubicación: ");
        int capacidad = parqueView.leerEntero("Ingrese capacidad máxima por hora: ");

        PuntoAcceso puntoAcceso = new PuntoAcceso(
                codigo, nombre, ubicacion, capacidad, EstadoPunto.ACTIVO
        );

        parque.addPuntoAcceso(posicion, puntoAcceso);
        parqueView.mostrarMensaje("Punto de acceso habilitado correctamente");
    }

    public void findAllPuntosAcceso(Parque parque) {
        validarParque(parque);

        PuntoAcceso[] puntosAcceso = parque.findAllPuntosAcceso();
        boolean hayPuntos = false;

        for (int i = 0; i < puntosAcceso.length; i++) {
            if (puntosAcceso[i] != null) {
                parqueView.mostrarMensaje(
                        "\nPosición: " + i + "\n" + puntosAcceso[i]
                );
                hayPuntos = true;
            }
        }

        if (!hayPuntos) {
            parqueView.mostrarMensaje("No hay puntos de acceso habilitados");
        }
    }

    public void findPuntoAccesoByPosition(Parque parque) {
        validarParque(parque);

        int posicion = parqueView.leerEntero("Ingrese posición (0-4): ");
        PuntoAcceso puntoAcceso = parque.findPuntoAccesoByPosition(posicion);

        if (puntoAcceso == null) {
            parqueView.mostrarMensaje("No existe un punto de acceso en esa posición");
            return;
        }

        parqueView.mostrarMensaje(puntoAcceso.toString());
    }

    public void updatePuntoAcceso(Parque parque) {
        validarParque(parque);

        int posicion = parqueView.leerEntero("Ingrese posición (0-4): ");
        int capacidad = parqueView.leerEntero("Ingrese nueva capacidad máxima por hora: ");
        int opcionEstado = parqueView.leerEntero("Ingrese estado (1 = ACTIVO, 2 = INACTIVO): ");

        EstadoPunto estado;

        if (opcionEstado == 1) {
            estado = EstadoPunto.ACTIVO;
        } else if (opcionEstado == 2) {
            estado = EstadoPunto.INACTIVO;
        } else {
            throw new IllegalArgumentException("El estado seleccionado no es válid");
        }

        parque.updatePuntoAcceso(posicion, capacidad, estado);
        parqueView.mostrarMensaje("Punto de acceso modificado correctamente");
    }

    public void deletePuntoAcceso(Parque parque) {
        validarParque(parque);

        int posicion = parqueView.leerEntero("Ingrese posición (0-4): ");

        parque.deletePuntoAcceso(posicion);
        parqueView.mostrarMensaje("Punto de acceso cerrado correctamente");
    }

    private void validarParque(Parque parque) {
        if (parque == null) {
            throw new IllegalArgumentException("Primero debe crear un parque");
        }
    }
}