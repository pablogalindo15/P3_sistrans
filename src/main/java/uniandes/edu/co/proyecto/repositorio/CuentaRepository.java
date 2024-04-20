package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cuenta;

import java.util.Collection;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query(value = "SELECT * FROM cuentas", nativeQuery = true)
    Collection<Cuenta> darCuentas();

    @Query(value = "SELECT * FROM cuentas WHERE id = :id", nativeQuery = true)
    Cuenta darCuenta(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cuentas WHERE id = :id", nativeQuery = true)
    void eliminarCuenta(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET tipo = :tipo, numero = :numero, saldo = :saldo, estado = :estado, id_cliente = :id_cliente WHERE id = :id", nativeQuery = true)
    void actualizarCuenta(@Param("id") long id, @Param("tipo") String tipo, @Param("numero") Integer numero, @Param("saldo") Integer saldo, @Param("estado") String estado, @Param("id_cliente") Integer id_cliente);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (id, tipo, numero, saldo, estado, id_cliente) VALUES ( parranderos_sequence.nextval , :tipo, :numero, :saldo, :estado, :id_cliente)", nativeQuery = true)
    void insertarCuenta(@Param("tipo") String tipo, @Param("numero") Integer numero, @Param("saldo") Integer saldo, @Param("estado") String estado, @Param("id_cliente") Integer id_cliente);
}
