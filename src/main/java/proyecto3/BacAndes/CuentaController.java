package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView listarCuentas() {
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("cuentas").addObject("cuentas", cuentas);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevaCuenta() {
        List<Cliente> clientes = clienteService.allClientes();
        return new ModelAndView("cuentaNueva").addObject("cuenta", new Cuenta()).addObject("clientes", clientes);
    }

    @PostMapping("/new/save")
    public ModelAndView crearCuenta(@ModelAttribute Cuenta cuenta) {
        cuentaService.saveCuenta(cuenta);
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("cuentas").addObject("cuentas", cuentas);
    }

    @GetMapping("/desactivar/{id}")
    public ModelAndView desactivarCuenta(@PathVariable("id") ObjectId id) {
        cuentaService.desactivarCuenta(id);
        return new ModelAndView("redirect:/cuentas");
    }

    @GetMapping("/cerrar/{id}")
    public ModelAndView cerrarCuenta(@PathVariable("id") ObjectId id) {
        cuentaService.cerrarCuenta(id);
        return new ModelAndView("redirect:/cuentas");
    }
}