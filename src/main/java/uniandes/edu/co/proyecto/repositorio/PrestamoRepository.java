package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Prestamo;

import java.sql.Date;
import java.util.Collection;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamos();

    @Query(value = "SELECT * FROM prestamos WHERE id = :id", nativeQuery = true)
    Prestamo darPrestamo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM prestamos WHERE id = :id", nativeQuery = true)
    void eliminarPrestamo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET monto = :monto, interes = :interes, cuotas = :cuotas, dia_pago = :dia_pago, estado = :estado, tipo = :tipo, id_cliente = :id_cliente WHERE id = :id", nativeQuery = true)
    void actualizarPrestamo(@Param("id") long id, @Param("monto") Integer monto, @Param("interes") Integer interes, @Param("cuotas") Integer cuotas, @Param("dia_pago") Date dia_pago, @Param("estado") String estado, @Param("tipo") String tipo, @Param("id_cliente") Integer id_cliente);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prestamos (id, monto, interes, cuotas, dia_pago, estado, tipo, id_cliente, monto_inicial) VALUES ( parranderos_sequence.nextval , :monto, :interes, :cuotas, :dia_pago, :estado, :tipo, :id_cliente)", nativeQuery = true)
    void insertarPrestamo(@Param("monto") Integer monto, @Param("interes") Integer interes, @Param("cuotas") Integer cuotas, @Param("dia_pago") Date dia_pago, @Param("estado") String estado, @Param("tipo") String tipo, @Param("id_cliente") Integer id_cliente);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE PRESTAMOS  SET MONTO = :valor - monto*0.01*INTERES WHERE ID = :id", nativeQuery = true)
    void pagarCuota(@Param("valor") Integer valor, @Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PRESTAMOS  SET MONTO = 0, estado='cerrado' WHERE ID = :id", nativeQuery = true)
    void cerrarPrestamo(@Param("id") long id);
}
