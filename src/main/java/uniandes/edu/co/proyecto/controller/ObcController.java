package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Obc;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.ObcRepository;

import java.util.List;

@Controller
public class ObcController {

    @Autowired
    private ObcRepository obcRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/obcs")
    public String listarObcs(Model model) {
        List<Obc> obcs = obcRepository.findAll();
        model.addAttribute("obcs", obcs);
        return "obcs";
    }

    @GetMapping("/obcs/new")
    public String formularioNuevoObc(Model model) {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("obc", new Obc());
        model.addAttribute("cuentas", cuentas);
        return "nuevo-obc";
    }

    @PostMapping("/obcs/save")
    public String guardarObc(@ModelAttribute Obc obc) {
        obcRepository.save(obc);
        return "redirect:/obcs";
    }

    @GetMapping("/obcs/{id}/edit")
    public String formularioEditarObc(@PathVariable("id") Integer id, Model model) {
        Obc obc = obcRepository.findById(id).orElse(null);
        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (obc != null) {
            model.addAttribute("obc", obc);
            model.addAttribute("cuentas", cuentas);
            return "editar-obc";
        } else {
            return "redirect:/obcs";
        }
    }

    @PostMapping("/obcs/{id}/update")
    public String actualizarObc(@PathVariable("id") Integer id, @ModelAttribute Obc obc) {
        obc.setId(id);
        obcRepository.save(obc);
        return "redirect:/obcs";
    }

    @GetMapping("/obcs/{id}/delete")
    public String eliminarObc(@PathVariable("id") Integer id) {
        obcRepository.deleteById(id);
        return "redirect:/obcs";
    }
}
