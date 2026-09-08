package model;

public class Visitante {

    private String codigoEntrada;
    private String nombre;
    private int edad;
    private int atraccionesVisitadas;
    private int puntosAcumulados;

    public Visitante(
            String codigoEntrada,
            String nombre,
            int edad,
            int atraccionesVisitadas,
            int puntosAcumulados) {

        this.setCodigoEntrada(codigoEntrada);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setAtraccionesVisitadas(atraccionesVisitadas);
        this.setPuntosAcumulados(puntosAcumulados);
    }

    // get y set de codigo de entrada
    public String getCodigoEntrada() {
        return this.codigoEntrada;
    }

    private void setCodigoEntrada(String codigoEntrada) {
        if (codigoEntrada == null || codigoEntrada.isBlank()) {
            throw new IllegalArgumentException("El código de entrada no debe ser vacío");
        }
        this.codigoEntrada = codigoEntrada;
    }

    //get y set de nombre
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del visitante no debe ser vacío");
        }
        this.nombre = nombre;
    }

    // get y set de edad
    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que cero");
        }
        this.edad = edad;
    }

    //get y set de atracciones visitadas
    public int getAtraccionesVisitadas() {
        return this.atraccionesVisitadas;
    }

    public void setAtraccionesVisitadas(int atraccionesVisitadas) {
        if (atraccionesVisitadas < 0) {
            throw new IllegalArgumentException("La cantidad de atracciones visitadas no debe ser negativa");
        }
        this.atraccionesVisitadas = atraccionesVisitadas;
    }

    // get y set de puntos acumulados
    public int getPuntosAcumulados() {
        return this.puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        if (puntosAcumulados < 0) {
            throw new IllegalArgumentException("Los puntos acumulados no deben ser negativos" );
        }
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public String toString() {
        return 
            "Código de entrada: " + this.codigoEntrada
                    + "\nNombre: " + this.nombre
                    + "\nEdad: " + this.edad
                    + "\nAtracciones visitadas: " + this.atraccionesVisitadas
                    + "\nPuntos acumulados: " + this.puntosAcumulados;
    }
}