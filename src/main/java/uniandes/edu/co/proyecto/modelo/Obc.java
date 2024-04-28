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
@Table(name="obcs")
public class Obc {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    private Integer valor;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id")
    private Cuenta id_cuenta;
    public Obc(){;}

    public Obc(Integer id, Date fecha, Integer valor, String tipo, Cuenta id_cuenta) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
        this.id_cuenta = id_cuenta;
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

    public Cuenta getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Cuenta id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
}
