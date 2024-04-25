package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="pas")
public class Pa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_oficina", referencedColumnName = "id")
    private Oficina id_oficina;
    
    public Pa(){;}
    
    public Pa(Integer id, String tipo, Oficina id_oficina) {
        this.id = id;
        this.tipo = tipo;
        this.id_oficina = id_oficina;
    }

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

    public Oficina getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(Oficina id_oficina) {
        this.id_oficina = id_oficina;
    }
}
