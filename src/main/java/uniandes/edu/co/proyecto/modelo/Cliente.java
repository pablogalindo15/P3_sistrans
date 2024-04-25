package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer num_doc;
    private String tipo_doc;
    private String nombre;
    private String dir;
    private String mail;
    private Integer tel;
    private String ciudad;
    private String clave;
    private String login;
    private String nacionalidad;
    private String dept;
    private Integer codigo_post;
    private String tipo;
    private String estado;

    public Cliente(){;}
    public Cliente(Integer id, Integer num_doc, String tipo_doc, String nombre, String dir, String mail, Integer tel, String ciudad, String clave, String login, String nacionalidad, String dept, Integer codigo_post, String tipo, String estado) {
        this.id = id;
        this.num_doc = num_doc;
        this.tipo_doc = tipo_doc;
        this.nombre = nombre;
        this.dir = dir;
        this.mail = mail;
        this.tel = tel;
        this.ciudad = ciudad;
        this.clave = clave;
        this.login = login;
        this.nacionalidad = nacionalidad;
        this.dept = dept;
        this.codigo_post = codigo_post;
        this.tipo = tipo;
        this.estado = estado;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(Integer num_doc) {
        this.num_doc = num_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getCodigo_post() {
        return codigo_post;
    }

    public void setCodigo_post(Integer codigo_post) {
        this.codigo_post = codigo_post;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
