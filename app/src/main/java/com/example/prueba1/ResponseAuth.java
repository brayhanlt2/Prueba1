
package com.example.prueba1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAuth {

    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("idusuario")
    @Expose
    private String idusuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("num_documento")
    @Expose
    private String numDocumento;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nombre_usuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("permisos")
    @Expose
    private String permisos;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAuth() {
    }

    /**
     * 
     * @param nombre
     * @param nombreComercial
     * @param idusuario
     * @param estado
     * @param email
     * @param tipo
     * @param permisos
     * @param telefono
     * @param numDocumento
     * @param mensaje
     * @param nombreUsuario
     */
    public ResponseAuth(String estado, String mensaje, String idusuario, String nombre, String nombreComercial, String numDocumento, String telefono, String email, String nombreUsuario, String tipo, String permisos) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.numDocumento = numDocumento;
        this.telefono = telefono;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.tipo = tipo;
        this.permisos = permisos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

}
