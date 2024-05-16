package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ModelAndView listarCuentas() {
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("cuentas").addObject("cuentas", cuentas);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevaCuenta() {
        return new ModelAndView("cuentaNueva").addObject("cuenta", new Cuenta());
    }

    @PostMapping("/new/save")
    public ModelAndView crearCuenta(@ModelAttribute Cuenta cuenta) {
        cuentaService.saveCuenta(cuenta);
        List<Cuenta> cuentas = cuentaService.allCuentas();
        return new ModelAndView("cuentas").addObject("cuentas", cuentas);
    }
}