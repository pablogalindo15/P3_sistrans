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
@Table(name="prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer monto;
    private Integer interes;
    private Integer cuotas;
    private Date dia_pago;
    private String estado;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente id_cliente;
    public Prestamo(){;}
    
    public Prestamo(Integer id, Integer monto, Integer interes, Integer cuotas, Date dia_pago, String estado, String tipo, Cliente id_cliente) {
        this.id = id;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.dia_pago = dia_pago;
        this.estado = estado;
        this.tipo = tipo;
        this.id_cliente = id_cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getInteres() {
        return interes;
    }

    public void setInteres(Integer interes) {
        this.interes = interes;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Date getDia_pago() {
        return dia_pago;
    }

    public void setDia_pago(Date dia_pago) {
        this.dia_pago = dia_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }
}
