package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<Empleado> allEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Oficina> allOficinas() {
        return oficinaRepository.findAll();
    }

    public List<Empleado> allGerentesDeOficina() {
        return empleadoRepository.findByCargo("gerente de oficina");
    }


    public void saveEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }
}