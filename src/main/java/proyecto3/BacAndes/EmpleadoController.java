package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ModelAndView listarEmpleados() {
        List<Empleado> empleados = empleadoService.allEmpleados();
        return new ModelAndView("empleados").addObject("empleados", empleados);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevoEmpleado() {
        return new ModelAndView("empleadoNuevo").addObject("empleado", new Empleado());
    }

    @PostMapping("/new/save")
    public ModelAndView crearEmpleado(@ModelAttribute Empleado empleado) {
        empleadoService.saveEmpleado(empleado);
        List<Empleado> empleados = empleadoService.allEmpleados();
        return new ModelAndView("empleados").addObject("empleados", empleados);
    }
}