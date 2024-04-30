package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Empleado;

import java.util.Collection;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query(value = "SELECT * FROM empleados", nativeQuery = true)
    Collection<Empleado> darEmpleados();

    @Query(value = "SELECT * FROM empleados WHERE id = :id", nativeQuery = true)
    Empleado darEmpleado(@Param("id") long id);

    @Query(value = "SELECT CLAVE FROM empleados WHERE login = :login", nativeQuery = true)
    String darClave(@Param("login") String login);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM empleados WHERE id = :id", nativeQuery = true)
    void eliminarEmpleado(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE empleados SET nombre = :nombre, dir = :dir, mail = :mail, tel = :tel, ciudad = :ciudad, clave = :clave, login = :login, nacionalidad = :nacionalidad, dept = :dept, codigo_post = :codigo_post, tipo = :tipo, estado = :estado, cargo = :cargo, id_oficina = :id_oficina WHERE id = :id", nativeQuery = true)
    void actualizarEmpleado(@Param("id") long id, @Param("nombre") String nombre, @Param("dir") String dir, @Param("mail") String mail, @Param("tel") Integer tel, @Param("ciudad") String ciudad, @Param("clave") String clave, @Param("login") String login, @Param("nacionalidad") String nacionalidad, @Param("dept") String dept, @Param("codigo_post") Integer codigo_post, @Param("tipo") String tipo, @Param("estado") String estado, @Param("cargo") String cargo, @Param("id_oficina") Integer id_oficina);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO empleados (id, num_doc, tipo_doc, nombre, dir, mail, tel, ciudad, clave, login, nacionalidad, dept, codigo_post, tipo, estado, cargo, id_oficina) VALUES ( parranderos_sequence.nextval , :num_doc, :tipo_doc, :nombre, :dir, :mail, :tel, :ciudad, :clave, :login, :nacionalidad, :dept, :codigo_post, :tipo, :estado, :cargo, :id_oficina)", nativeQuery = true)
    void insertarEmpleado(@Param("num_doc") Integer num_doc, @Param("tipo_doc") String tipo_doc, @Param("nombre") String nombre, @Param("dir") String dir, @Param("mail") String mail, @Param("tel") Integer tel, @Param("ciudad") String ciudad, @Param("clave") String clave, @Param("login") String login, @Param("nacionalidad") String nacionalidad, @Param("dept") String dept, @Param("codigo_post") Integer codigo_post, @Param("tipo") String tipo, @Param("estado") String estado, @Param("cargo") String cargo, @Param("id_oficina") Integer id_oficina);
}
