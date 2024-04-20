package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Obp;

import java.sql.Date;
import java.util.Collection;

public interface ObpRepository extends JpaRepository<Obp, Integer> {

    @Query(value = "SELECT * FROM obps", nativeQuery = true)
    Collection<Obp> darObps();

    @Query(value = "SELECT * FROM obps WHERE id = :id", nativeQuery = true)
    Obp darObp(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM obps WHERE id = :id", nativeQuery = true)
    void eliminarObp(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE obps SET fecha = :fecha, valor = :valor, tipo = :tipo, id_prestamo = :id_prestamo WHERE id = :id", nativeQuery = true)
    void actualizarObp(@Param("id") long id, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo, @Param("id_prestamo") Integer id_prestamo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO obps (id, fecha, valor, tipo, id_prestamo) VALUES ( parranderos_sequence.nextval , :fecha, :valor, :tipo, :id_prestamo)", nativeQuery = true)
    void insertarObp(@Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo, @Param("id_prestamo") Integer id_prestamo);
}
