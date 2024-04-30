package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Obc;

import java.sql.Date;
import java.util.Collection;

public interface ObcRepository extends JpaRepository<Obc, Integer> {

    @Query(value = "SELECT * FROM obcs", nativeQuery = true)
    Collection<Obc> darObcs();

    @Query(value = "SELECT * FROM obcs WHERE id = :id", nativeQuery = true)
    Collection<Obc> darObcsId(@Param("id") long id);

    @Query(value = "SELECT * FROM obcs WHERE id_cuenta = :id  AND FECHA >= TRUNC(SYSDATE) - 30 FOR UPDATE", nativeQuery = true)
    Collection<Obc> darObcsIdCuenta(@Param("id") long id);

    @Query(value = "SELECT * FROM obcs WHERE id = :id", nativeQuery = true)
    Obc darObc(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM obcs WHERE id = :id", nativeQuery = true)
    void eliminarObc(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE obcs SET fecha = :fecha, valor = :valor, tipo = :tipo, id_cuenta = :id_cuenta WHERE id = :id", nativeQuery = true)
    void actualizarObc(@Param("id") long id, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo, @Param("id_cuenta") Integer id_cuenta);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO obcs (id, fecha, valor, tipo, id_cuenta) VALUES ( parranderos_sequence.nextval , :fecha, :valor, :tipo, :id_cuenta)", nativeQuery = true)
    void insertarObc(@Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo, @Param("id_cuenta") Integer id_cuenta);
}
