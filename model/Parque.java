package model;

import constants.EstadoPunto;
import java.util.ArrayList;

public class Parque {

    private String nombre;
    private String codigoIdentificacion;
    private String nombreEncargado;

    private PuntoAcceso[] puntosAcceso;
    private ArrayList<Visitante> visitantes;

    public Parque(String nombre, String codigoIdentificacion, String nombreEncargado) {

        this.setNombre(nombre);
        this.setCodigoIdentificacion(codigoIdentificacion);
        this.setNombreEncargado(nombreEncargado);
        this.puntosAcceso = new PuntoAcceso[5];
        this.visitantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del parque no debe ser vacío");
        }
        this.nombre = nombre;
    }

    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    private void setCodigoIdentificacion( String codigoIdentificacion) {

        if (codigoIdentificacion == null || codigoIdentificacion.isBlank()) {
            throw new IllegalArgumentException("El código de identificación no debe ser vacío");
        }
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        if (nombreEncargado == null || nombreEncargado.isBlank()) {
            throw new IllegalArgumentException("El nombre del encargado no debe ser vacío");
        }
        this.nombreEncargado = nombreEncargado;
    }

    public void addPuntoAcceso( int posicion, PuntoAcceso puntoAcceso) {
        validarPosicion(posicion);

            if (puntosAcceso[posicion] != null) {
                throw new IllegalArgumentException("La posición seleccionada ya está esta en uso");
            }
            if (puntoAcceso == null) {
                throw new IllegalArgumentException("El punto de acceso no debe ser null");
            }

            puntosAcceso[posicion] = puntoAcceso;
    }

    public PuntoAcceso findPuntoAccesoByPosition(int posicion) {

        validarPosicion(posicion);
        return puntosAcceso[posicion];
    }

    public PuntoAcceso[] findAllPuntosAcceso() {
        return puntosAcceso;
    }

    public void updatePuntoAcceso( int posicion, int nuevaCapacidad, EstadoPunto nuevoEstado) {

        validarPosicion(posicion);

        PuntoAcceso puntoAcceso = puntosAcceso[posicion];

        if (puntoAcceso == null) {
            throw new IllegalArgumentException("No existe un punto de acceso en esa posición");
        }

        puntoAcceso.setCapacidadMaximaHora( nuevaCapacidad);

        puntoAcceso.setEstado(nuevoEstado);
    }

    public void deletePuntoAcceso(int posicion) {
        validarPosicion(posicion);

        if (puntosAcceso[posicion] == null) {
            throw new IllegalArgumentException("No existe un punto de acceso en esa posición");
        }

        puntosAcceso[posicion] = null;
    }

    public void addVisitante(Visitante visitante) {
        if (visitante == null) {
            throw new IllegalArgumentException("El visitante no debe ser null");
        }

        Visitante visitanteEncontrado = findVisitanteById( visitante.getCodigoEntrada());

        if (visitanteEncontrado != null) {
            throw new IllegalArgumentException("Ya existe un visitante con ese código");
        }

        visitantes.add(visitante);
    }

    public Visitante findVisitanteById(
            String codigoEntrada) {

        if (codigoEntrada == null || codigoEntrada.isBlank()) {
            throw new IllegalArgumentException("El código de entrada no debe serx vacío");
        }
        for (Visitante visitante : visitantes) {
            if (visitante.getCodigoEntrada().equals(codigoEntrada)) {
                return visitante;
            }
        }
        return null;
    }

    public ArrayList<Visitante> findAllVisitantes() {
        return visitantes;
    }

    public void updateVisitante( String codigoEntrada, String nuevoNombre, int nuevaEdad,
            int nuevasAtraccionesVisitadas, int nuevosPuntosAcumulados) {

        Visitante visitante =
                findVisitanteById(codigoEntrada);

        if (visitante == null) {
            throw new IllegalArgumentException("No existe un visitante con ese código");
        }

        visitante.setNombre(nuevoNombre);
        visitante.setEdad(nuevaEdad);
        visitante.setAtraccionesVisitadas(nuevasAtraccionesVisitadas);
        visitante.setPuntosAcumulados(nuevosPuntosAcumulados);
    }

    public void deleteVisitante(String codigoEntrada) {
        Visitante visitante = findVisitanteById(codigoEntrada);

        if (visitante == null) {
            throw new IllegalArgumentException("No existe un visitante con ese código");
        }
        visitantes.remove(visitante);
    }

    public int countPuntosAccesoHabilitados() {
        int cantidad = 0;

        for (PuntoAcceso puntoAcceso : puntosAcceso) {
            if (puntoAcceso != null) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int countEspaciosDisponibles() {
        return puntosAcceso.length - countPuntosAccesoHabilitados();
    }

    public PuntoAcceso findPuntoAccesoMayorCapacidad() {
        PuntoAcceso puntoMayor = null;

        for (PuntoAcceso puntoAcceso : puntosAcceso) {
            if (puntoAcceso != null) {
                if (puntoMayor == null || 
                    puntoAcceso.getCapacidadMaximaHora() > puntoMayor.getCapacidadMaximaHora()) {
                    puntoMayor = puntoAcceso;
                }
            }
        }

        return puntoMayor;
    }

    public int countVisitantes() {
        return visitantes.size();
    }

    public Visitante findVisitanteMayorPuntaje() {
        if (visitantes.isEmpty()) {
            return null;
        }

        Visitante visitanteMayor = visitantes.get(0);

        for (Visitante visitante : visitantes) {
            if (visitante.getPuntosAcumulados() > visitanteMayor.getPuntosAcumulados()) {
                visitanteMayor = visitante;
            }
        }
        return visitanteMayor;
    }

    public Visitante findVisitanteMasAtracciones() {
        if (visitantes.isEmpty()) {
            return null;
        }

        Visitante visitanteMayor = visitantes.get(0);

        for (Visitante visitante : visitantes) {
            if (visitante.getAtraccionesVisitadas() > visitanteMayor.getAtraccionesVisitadas()) {
                visitanteMayor = visitante;
            }
        }

        return visitanteMayor;
    }

    public double calculatePromedioEdad() {
        if (visitantes.isEmpty()) {
            return 0;
        }

        int sumaEdades = 0;

        for (Visitante visitante : visitantes) {
            sumaEdades += visitante.getEdad();
        }

        return (double) sumaEdades / visitantes.size();
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= puntosAcceso.length) {
            throw new IndexOutOfBoundsException( "La posición debe estar entre 0 y " + (puntosAcceso.length - 1));
        }
    }

    @Override
    public String toString() {
        return "Nombre del parque: " + nombre
                + "\nCódigo de identificación:" + codigoIdentificacion
                + "\nNombre del encargado: " + nombreEncargado;
    }
}