package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/pas")
public class PasController {

    @Autowired
    private PasService pasService;

    @GetMapping
    public ModelAndView listarPas() {
        List<Pas> pasList = pasService.allPas();
        return new ModelAndView("pas").addObject("pas", pasList);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevoPas() {
        List<Oficina> oficinas = pasService.allOficinas();
        return new ModelAndView("pasNuevo").addObject("pas", new Pas()).addObject("oficinas", oficinas);
    }

    @PostMapping("/new/save")
    public ModelAndView crearPas(@ModelAttribute Pas pas) {
        pasService.savePas(pas);
        List<Pas> pasList = pasService.allPas();
        return new ModelAndView("pas").addObject("pas", pasList);
    }
}