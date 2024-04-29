package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Obc;
import uniandes.edu.co.proyecto.modelo.Transferencia;
import uniandes.edu.co.proyecto.modelo.TransferenciaPK;

import java.sql.Date;
import java.util.Collection;

public interface TransferenciaRepository extends JpaRepository<Transferencia, TransferenciaPK> {

    @Query(value = "SELECT * FROM transferencias", nativeQuery = true)
    Collection<Transferencia> darTransferencias();

    @Query(value = "SELECT * FROM transferencias WHERE id_cuenta_1 = :id_cuenta_1 AND id_cuenta_2 = :id_cuenta_2 AND fecha = :fecha AND valor = :valor AND tipo = :tipo", nativeQuery = true)
    Transferencia darTransferenciaPorId(@Param("id_cuenta_1") Integer id_cuenta_1, @Param("id_cuenta_2") Integer id_cuenta_2, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo);


    @Query(value = "SELECT * FROM transferencias WHERE id_cuenta_1 = :id  AND FECHA >= TRUNC(SYSDATE) - 30", nativeQuery = true)
    Collection<Transferencia> darTransferenciasIdCuenta(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM transferencias WHERE id_cuenta_1 = :id_cuenta_1 AND id_cuenta_2 = :id_cuenta_2 AND fecha = :fecha AND valor = :valor AND tipo = :tipo", nativeQuery = true)
    void eliminarTransferencia(@Param("id_cuenta_1") Integer id_cuenta_1, @Param("id_cuenta_2") Integer id_cuenta_2, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo);

    @Modifying  
    @Transactional
    @Query(value = "UPDATE transferencias SET id_cuenta_1 = :id_cuenta_1_actualizado, id_cuenta_2 = :id_cuenta_2_actualizado, fecha = :fecha_actualizado, valor = :valor_actualizado, tipo = :tipo_actualizado WHERE id_cuenta_1 = :id_cuenta_1 AND id_cuenta_2 = :id_cuenta_2 AND fecha = :fecha AND valor = :valor AND tipo = :tipo", nativeQuery = true)
    void actualizarTransferencia(@Param("id_cuenta_1") Integer id_cuenta_1, @Param("id_cuenta_2") Integer id_cuenta_2, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo, @Param("id_cuenta_1_actualizado") Integer id_cuenta_1_actualizado, @Param("id_cuenta_2_actualizado") Integer id_cuenta_2_actualizado, @Param("fecha_actualizado") Date fecha_actualizado, @Param("valor_actualizado") Integer valor_actualizado, @Param("tipo_actualizado") String tipo_actualizado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transferencias (id_cuenta_1, id_cuenta_2, fecha, valor, tipo) VALUES (:id_cuenta_1, :id_cuenta_2, :fecha, :valor, :tipo)", nativeQuery = true)
    void insertarTransferencia(@Param("id_cuenta_1") Integer id_cuenta_1, @Param("id_cuenta_2") Integer id_cuenta_2, @Param("fecha") Date fecha, @Param("valor") Integer valor, @Param("tipo") String tipo);

}
