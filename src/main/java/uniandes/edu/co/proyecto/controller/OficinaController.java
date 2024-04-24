package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;

import java.util.List;

@Controller
public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/oficinas")
    public String listarOficinas(Model model) {
        List<Oficina> oficinas = oficinaRepository.findAll();
        model.addAttribute("oficinas", oficinas);
        return "oficinas";
    }

    @GetMapping("/oficinas/new")
    public String formularioNuevaOficina(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinaNueva";
    }

    @PostMapping("/oficinas/new/save")
    public String guardarOficina(@ModelAttribute Oficina oficina, Model model) {
        try {
            oficinaRepository.save(oficina);
            return "redirect:/oficinas";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Gerente no válido, ingrese un ID de gerente válido");
            return "oficinaNueva"; // Devuelve a la página de formulario con el mensaje de error
        }
    }

    @GetMapping("/oficinas/{id}/edit")
    public String formularioEditarOficina(@PathVariable("id") Integer id, Model model) {
        Oficina oficina = oficinaRepository.findById(id).orElse(null);
        if (oficina != null) {
            model.addAttribute("oficina", oficina);
            return "editarOficina"; // Asegúrate de que el nombre del archivo HTML es correcto
        } else {
            return "redirect:/oficinas";
        }
    }

    @PostMapping("/oficinas/{id}/update")
    public String actualizarOficina(@PathVariable("id") Integer id, @ModelAttribute Oficina oficina) {
        oficina.setId(id);
        oficinaRepository.save(oficina);
        return "redirect:/oficinas";
    }

    @GetMapping("/oficinas/{id}/delete")
    public String eliminarOficina(@PathVariable("id") Integer id) {
        oficinaRepository.deleteById(id);
        return "redirect:/oficinas";
    }
}
