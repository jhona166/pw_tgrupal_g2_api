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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "elemento")
public class Elemento {
     @Id
    @GeneratedValue(generator = "seq_elemento", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_elemento", sequenceName = "seq_elemento", allocationSize = 1)
    
    @Column(name="elem_id")
    private Long id;
    @Column(name="elem_nombre")
    private String nombre;
    @Column(name="elem_tipo")
    private String tipo; // "CARPETA" o "ARCHIVO"
    

    @Lob
    @Column(columnDefinition = "BYTEA", nullable = true)
    private byte[] datos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elemento_padre_id")
    private Elemento elementoPadre;
    
    @OneToMany(mappedBy = "elementoPadre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Elemento> subElementos;

    


    public Elemento(Long id, String nombre, String tipo, byte[] datos, Elemento elementoPadre,
            List<Elemento> subElementos) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.datos = datos;
        this.elementoPadre = elementoPadre;
        this.subElementos = subElementos;
    }

    public Elemento() {
    }

    //SET and GET
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Elemento getElementoPadre() {
        return elementoPadre;
    }

    public void setElementoPadre(Elemento elementoPadre) {
        this.elementoPadre = elementoPadre;
    }

    public List<Elemento> getSubElementos() {
        return subElementos;
    }

    public void setSubElementos(List<Elemento> subElementos) {
        this.subElementos = subElementos;
    }
    
    // Getters y Setters


    
}
