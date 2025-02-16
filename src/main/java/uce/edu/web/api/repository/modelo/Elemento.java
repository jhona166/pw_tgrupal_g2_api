package uce.edu.web.api.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "elemento")
public class Elemento {
    
    //Metadatos del elemento
    @Id
    @GeneratedValue(generator = "seq_elemento", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_elemento", sequenceName = "seq_elemento", allocationSize = 1)
    @Column(name="elem_id")
    private Integer id;
    @Column(name="elem_nombre")
    private String nombre;
    @Column(name="elem_tipo")
    private String tipo; // "CARPETA" o "ARCHIVO"
    
    //Contenido binario del elemento
    
    public Elemento() {
    }

    @Column(columnDefinition = "BYTEA", nullable = true,name="elem_datos")
    private byte[] datos; 
    
    //Jerarquia del grafo
    //Los elementos pieden ser archivos o carpetas
    //Folosofia unil like: "Everything is a file"
    //Si son archivos se considera nodo hoja
    //Si son carpeta pueden tener elementos hijo
    
    /*       [Raiz]
     *    /         \
     *   [Archivo]   [Carpeta]
     *              |         \
     *              [Archivo] [Archivo]
    */


    //Elemento padre del elemento actual
    //Solo guarda el id para evitar consultas circulares infinitas
    @JoinColumn(name = "elemento_padre_id")
    private Integer elementoPadre;
    
    //Elementos hijos del elemento
    @OneToMany(mappedBy = "elementoPadre", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @Column(name="elem_sub_elementos")
    private List<Elemento> subElementos;



    //SET and GET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    public Integer getElementoPadre() {
        return elementoPadre;
    }

    public void setElementoPadre(Integer elementoPadre) {
        this.elementoPadre = elementoPadre;
    }

    public List<Elemento> getSubElementos() {
        return subElementos;
    }

    public void setSubElementos(List<Elemento> subElementos) {
        this.subElementos = subElementos;
    }
    
   


    
}
