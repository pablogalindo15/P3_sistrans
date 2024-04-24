package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="oficinas")
public class Oficina {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String dir;
    private String num_pa;
    public Oficina(){;}

    public Oficina(Integer id, String nombre, String dir, String num_pa) {
        this.id = id;
        this.nombre = nombre;
        this.dir = dir;
        this.num_pa = num_pa;
    }
    
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getNum_pa() {
        return num_pa;
    }

    public void setNum_pa(String num_pa) {
        this.num_pa = num_pa;
    }
    
}