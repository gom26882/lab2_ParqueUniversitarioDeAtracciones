package controller;

import model.Parque;
import model.Visitante;
import view.ParqueView;

import java.util.ArrayList;

public class VisitanteController {

    private ParqueView parqueView;

    public VisitanteController(ParqueView parqueView) {
        this.parqueView = parqueView;
    }

    public void addVisitante(Parque parque) {
        validarParque(parque);

        String codigo = parqueView.leerTexto("Ingrese código de entrada: ");
        String nombre = parqueView.leerTexto("Ingrese nombre: ");
        int edad = parqueView.leerEntero("Ingrese edad: ");
        int atracciones = parqueView.leerEntero("Ingrese cantidad de atracciones visitadas: ");
        int puntos = parqueView.leerEntero("Ingrese puntos acumulados: ");
        Visitante visitante = new Visitante(codigo, nombre, edad, atracciones, puntos);

        parque.addVisitante(visitante);
        parqueView.mostrarMensaje("Visitante registrado correctamente");
    }

    public void findAllVisitantes(Parque parque) {
        validarParque(parque);

        ArrayList<Visitante> visitantes =
                parque.findAllVisitantes();

        if (visitantes.isEmpty()) {
            parqueView.mostrarMensaje("No hay visitantes registrados");
            return;
        }

        for (Visitante visitante : visitantes) { parqueView.mostrarMensaje(
                    "\n" + visitante
            );
        }
    }

    public void findVisitanteById(Parque parque) {
        validarParque(parque);

        String codigo = parqueView.leerTexto("Ingrese código de entrada: ");
        Visitante visitante = parque.findVisitanteById(codigo);

        if (visitante == null) {
            parqueView.mostrarMensaje("No existe un visitante con ese código");
            return;
        }

        parqueView.mostrarMensaje(visitante.toString());
    }

    public void updateVisitante(Parque parque) {
        validarParque(parque);

        String codigo = parqueView.leerTexto("Ingrese código del visitante: ");
        String nombre = parqueView.leerTexto("Ingrese nuevo nombre: ");
        int edad = parqueView.leerEntero("Ingrese nueva edad: ");
        int atracciones = parqueView.leerEntero("Ingrese nueva cantidad de atracciones visitadas: ");
        int puntos = parqueView.leerEntero("Ingrese nuevos puntos acumulados: ");
        parque.updateVisitante(codigo, nombre, edad, atracciones, puntos);
        parqueView.mostrarMensaje("Visitante modificado correctamente");
    }

    public void deleteVisitante(Parque parque) {
        validarParque(parque);

        String codigo = parqueView.leerTexto("Ingrese código del visitante: ");

        parque.deleteVisitante(codigo);
        parqueView.mostrarMensaje("Visitante eliminado correctamente");
    }

    private void validarParque(Parque parque) {
        if (parque == null) {
            throw new IllegalArgumentException("Primero se debe crear un parque");
        }
    }
}
