package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Obp;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.repositorio.ObpRepository;
import uniandes.edu.co.proyecto.repositorio.PrestamoRepository;

import java.util.List;

@Controller
public class ObpController {

    @Autowired
    private ObpRepository obpRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @GetMapping("/obps")
    public String listarObps(Model model) {
        List<Obp> obps = obpRepository.findAll();
        model.addAttribute("obps", obps);
        return "obps";
    }

    @GetMapping("/obps/new")
    public String formularioNuevoObp(Model model) {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        model.addAttribute("obp", new Obp());
        model.addAttribute("prestamos", prestamos);
        return "nuevo-obp";
    }

    @PostMapping("/obps/save")
    public String guardarObp(@ModelAttribute Obp obp) {
        obpRepository.save(obp);
        return "redirect:/obps";
    }

    @GetMapping("/obps/{id}/edit")
    public String formularioEditarObp(@PathVariable("id") Integer id, Model model) {
        Obp obp = obpRepository.findById(id).orElse(null);
        List<Prestamo> prestamos = prestamoRepository.findAll();
        if (obp != null) {
            model.addAttribute("obp", obp);
            model.addAttribute("prestamos", prestamos);
            return "editar-obp";
        } else {
            return "redirect:/obps";
        }
    }

    @PostMapping("/obps/{id}/update")
    public String actualizarObp(@PathVariable("id") Integer id, @ModelAttribute Obp obp) {
        obp.setId(id);
        obpRepository.save(obp);
        return "redirect:/obps";
    }

    @GetMapping("/obps/{id}/delete")
    public String eliminarObp(@PathVariable("id") Integer id) {
        obpRepository.deleteById(id);
        return "redirect:/obps";
    }
}
