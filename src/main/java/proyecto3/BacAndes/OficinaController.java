package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ModelAndView listarOficinas() {
        List<Oficina> oficinas = oficinaService.allOficinas();
        return new ModelAndView("oficinas").addObject("oficinas", oficinas);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevaOficina() {
        List<Empleado> gerentes = empleadoService.allGerentesDeOficina();
        return new ModelAndView("oficinaNueva").addObject("oficina", new Oficina()).addObject("gerentes", gerentes);
    }

    @PostMapping("/new/save")
    public ModelAndView crearOficina(@ModelAttribute Oficina oficina) {
        oficinaService.saveOficina(oficina);
        List<Oficina> oficinas = oficinaService.allOficinas();
        return new ModelAndView("oficinas").addObject("oficinas", oficinas);
    }
}