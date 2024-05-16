package proyecto3.BacAndes;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> allCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> singleCuenta(ObjectId id) {
        return cuentaRepository.findById(id);
    }

    public void saveCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public void deleteCuenta(ObjectId id) {
        cuentaRepository.deleteById(id);
    }

    // Método para desactivar una cuenta
    public void desactivarCuenta(ObjectId id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        if (cuenta.getEstado().equals("activa")) {
            cuenta.setEstado("desactivada");
            cuentaRepository.save(cuenta);
        }
    }

    // Método para cerrar una cuenta
    public void cerrarCuenta(ObjectId id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        if (cuenta.getEstado().equals("activa") && cuenta.getSaldo() == 0) {
            cuenta.setEstado("cerrada");
            cuentaRepository.save(cuenta);
        }
}

}