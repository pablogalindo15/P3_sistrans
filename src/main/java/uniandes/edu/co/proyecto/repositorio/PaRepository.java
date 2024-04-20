package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Pa;

import java.util.Collection;

public interface PaRepository extends JpaRepository<Pa, Integer> {

    @Query(value = "SELECT * FROM pas", nativeQuery = true)
    Collection<Pa> darPas();

    @Query(value = "SELECT * FROM pas WHERE id = :id", nativeQuery = true)
    Pa darPa(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pas WHERE id = :id", nativeQuery = true)
    void eliminarPa(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pas SET tipo = :tipo, id_oficina = :id_oficina WHERE id = :id", nativeQuery = true)
    void actualizarPa(@Param("id") long id, @Param("tipo") String tipo, @Param("id_oficina") Integer id_oficina);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pas (id, tipo, id_oficina) VALUES ( parranderos_sequence.nextval , :tipo, :id_oficina)", nativeQuery = true)
    void insertarPa(@Param("tipo") String tipo, @Param("id_oficina") Integer id_oficina);
}
