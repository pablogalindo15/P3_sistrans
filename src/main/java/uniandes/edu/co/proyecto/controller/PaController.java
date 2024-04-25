package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Pa;
import uniandes.edu.co.proyecto.repositorio.PaRepository;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;

import java.util.List;

@Controller
public class PaController {

    @Autowired
    private PaRepository paRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/pas")
    public String listarPas(Model model) {
        List<Pa> pas = paRepository.findAll();
        model.addAttribute("pas", pas);
        return "pas";
    }

    @GetMapping("/pas/new")
    public String formularioNuevoPa(Model model) {
        model.addAttribute("pa", new Pa());
        return "paNuevo";
    } 
 
    @PostMapping("/pas/new/save")
    public String guardarpa(@ModelAttribute Pa pa, Model model) {
        try {
            paRepository.save(pa);
            return "redirect:/pas";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "id oficina invalido");
            return "paNuevo"; 
        }
    }

    @GetMapping("/pas/{id}/edit")
    public String formularioEditarPa(@PathVariable("id") Integer id, Model model) {
        Pa pa = paRepository.findById(id).orElse(null);
        if (pa != null) {
            model.addAttribute("pa", pa);
            model.addAttribute("oficinas", oficinaRepository.findAll());
            return "editar-pa";
        } else {
            return "redirect:/pas";
        }
    }

    @PostMapping("/pas/{id}/update")
    public String actualizarPa(@PathVariable("id") Integer id, @ModelAttribute Pa pa) {
        pa.setId(id);
        paRepository.save(pa);
        return "redirect:/pas";
    }

    @GetMapping("/pas/{id}/delete")
    public String eliminarPa(@PathVariable("id") Integer id) {
        paRepository.deleteById(id);
        return "redirect:/pas";
    }
}
