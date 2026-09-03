package model;

import constants.EstadoPunto;

public class PuntoAcceso {

    private String codigo;
    private String nombre;
    private String ubicacion;
    private int capacidadMaximaHora;
    private EstadoPunto estado;

    public PuntoAcceso(
            String codigo,
            String nombre,
            String ubicacion,
            int capacidadMaximaHora,
            EstadoPunto estado) {

        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setUbicacion(ubicacion);
        this.setCapacidadMaximaHora(capacidadMaximaHora);
        this.setEstado(estado);
    }

    // Get y set de codigo
    public String getCodigo() {
        return this.codigo;
    }

    private void setCodigo(String codigo){
        if(codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("El código no puede ser vacío");
        }
        this.codigo = codigo;
    }

    //get y set de nombre
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede ser vacío");
        }
        this.nombre = nombre;
    }

    //get y set de ubicacion
    public String getUbicacion(){
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion){
        if(ubicacion == null || ubicacion.isBlank()){
            throw new IllegalArgumentException("La ubicacion no puede ser vacía");
        }
        this.ubicacion = ubicacion;
    }

    //get y set de capacidad maxima por hora
    public int getCapacidadMaximaHora() {
        return this.capacidadMaximaHora;
    }

    public void setCapacidadMaximaHora(int capacidadMaximaHora) {
        if (capacidadMaximaHora <= 0) {
            throw new IllegalArgumentException("La capacidad maxima por hora debe ser mayor a caro");
        }
        this.capacidadMaximaHora = capacidadMaximaHora;
    }

    //get y set de estado punto
    public EstadoPunto getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoPunto estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado de punto no puede ser null");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return 
            "Código: " + this.codigo 
            + "\nNombre: " + this.nombre 
            + "\nUbicación: " + this.ubicacion 
            + "\nCapacidad máxima por hora: " + this.capacidadMaximaHora 
            + "\nEstado: " + this.estado;
    }
}