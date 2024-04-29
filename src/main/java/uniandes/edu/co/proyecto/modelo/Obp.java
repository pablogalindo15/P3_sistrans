package uniandes.edu.co.proyecto.modelo;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="obps")
public class Obp {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    private Integer valor;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
    private Prestamo id_prestamo;
    public Obp(){;}
    
    public Obp(Integer id, Date fecha, Integer valor, String tipo, Prestamo id_prestamo) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
        this.id_prestamo = id_prestamo;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Prestamo getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Prestamo id_prestamo) {
        this.id_prestamo = id_prestamo;
    }
}

