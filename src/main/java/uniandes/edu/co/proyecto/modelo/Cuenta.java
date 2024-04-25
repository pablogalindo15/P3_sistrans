package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.persistence.Column;

@Entity
@Table(name="cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private Integer numero;
    private Integer saldo;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente id_cliente;

    @Column(name = "fecha_ultima_transaccion")
    private LocalDate fechaUltimaTransaccion; // Nuevo campo para la fecha de la última transacción

    public Cuenta() {;}

    public Cuenta(Integer id, String tipo, Integer numero, Integer saldo, String estado, Cliente id_cliente, LocalDate fechaUltimaTransaccion) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
        this.saldo = saldo;
        this.estado = estado;
        this.id_cliente = id_cliente;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFechaUltimaTransaccion() {
        return fechaUltimaTransaccion;
    }

    public void setFechaUltimaTransaccion(LocalDate fechaUltimaTransaccion) {
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }
}
