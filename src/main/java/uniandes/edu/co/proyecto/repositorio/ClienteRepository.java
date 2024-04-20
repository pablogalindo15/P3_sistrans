package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    @Query(value = "SELECT * FROM clientes", nativeQuery = true)
    Collection<Cliente> darClientes();

    @Query(value = "SELECT * FROM clientes WHERE id = :id", nativeQuery = true)
    Cliente darCliente(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (id, num_doc, tipo_doc, nombre, dir, mail, tel, ciudad, clave, login, nacionalidad, dept, codigo_post, tipo, estado) VALUES ( parranderos_sequence.nextval , :num_doc, :tipo_doc, :nombre, :dir, :mail, :tel, :ciudad, :clave, :login, :nacionalidad, :dept, :codigo_post, :tipo, :estado)", nativeQuery = true)
    void insertarCliente(@Param("num_doc") Integer num_doc, @Param("tipo_doc") String tipo_doc, @Param("nombre") String nombre,
    @Param("dir") String dir, @Param("mail") String mail, @Param("tel") Integer tel, @Param("ciudad") String ciudad,
    @Param("clave") String clave, @Param("login") String login, @Param("nacionalidad") String nacionalidad, @Param("dept") String dept,
    @Param("codigo_post") Integer codigo_post, @Param("tipo") String tipo, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE clientes SET num_doc = :num_doc, tipo_doc = :tipo_doc, nombre = :nombre, dir = :dir, mail = :mail, tel = :tel, ciudad = :ciudad, clave = :clave, login = :login, nacionalidad = :nacionalidad, dept = :dept, codigo_post = :codigo_post, tipo = :tipo, estado = :estado WHERE id = :id", nativeQuery = true)
    void actualizarCliente(@Param("id") long id, @Param("num_doc") Integer num_doc, @Param("tipo_doc") String tipo_doc, @Param("nombre") String nombre,
                    @Param("dir") String dir, @Param("mail") String mail, @Param("tel") Integer tel, @Param("ciudad") String ciudad,
                    @Param("clave") String clave, @Param("login") String login, @Param("nacionalidad") String nacionalidad, @Param("dept") String dept,
                    @Param("codigo_post") Integer codigo_post, @Param("tipo") String tipo, @Param("estado") String estado);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM clientes WHERE id = :id", nativeQuery = true)
    void eliminarCliente(@Param("id") long id);
    
}
