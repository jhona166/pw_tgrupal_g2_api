package uce.edu.web.api.service.to;

public class ElementoTo {
    
    private String nombre;
    private String tipo;
    private String datos;
    private Integer elementoPadre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDatos() {
        return datos;
    }
    public void setDatos(String datos) {
        this.datos = datos;
    }
    public Integer getElementoPadre() {
        return elementoPadre;
    }
    public void setElementoPadre(Integer elementoPadre) {
        this.elementoPadre = elementoPadre;
    }
}
