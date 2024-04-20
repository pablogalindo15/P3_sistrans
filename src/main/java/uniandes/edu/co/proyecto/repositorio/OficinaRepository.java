package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Oficina;
 
public interface OficinaRepository extends JpaRepository<Oficina, Integer> {

    @Query(value = "SELECT * FROM oficinas", nativeQuery = true)
    Collection<Oficina> darOficinas();

    @Query(value = "SELECT * FROM oficinas WHERE id = :id", nativeQuery = true)
    Oficina darOficina(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO oficinas (nombre, dir, num_pa) VALUES (:nombre, :dir, :numPa)", nativeQuery = true)
    void insertarOficina(@Param("nombre") String nombre, @Param("dir") String dir, @Param("numPa") String numPa);

    @Modifying
    @Transactional
    @Query(value = "UPDATE oficinas SET nombre = :nombre, dir = :dir, num_pa = :numPa WHERE id = :id", nativeQuery = true)
    void actualizarOficina(@Param("id") int id, @Param("nombre") String nombre, @Param("dir") String dir, @Param("numPa") String numPa);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM oficinas WHERE id = :id", nativeQuery = true)
    void eliminarOficina(@Param("id") int id);
}
