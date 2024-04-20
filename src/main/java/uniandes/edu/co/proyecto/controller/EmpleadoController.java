package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.repositorio.OficinaRepository;

import java.util.List;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/empleados")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("empleados", empleados);
        return "empleados";
    }

    @GetMapping("/empleados/new")
    public String formularioNuevoEmpleado(Model model) {
        List<Oficina> oficinas = oficinaRepository.findAll();
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("oficinas", oficinas);
        return "nuevo-empleado";
    }

    @PostMapping("/empleados/save")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{id}/edit")
    public String formularioEditarEmpleado(@PathVariable("id") Integer id, Model model) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        List<Oficina> oficinas = oficinaRepository.findAll();
        if (empleado != null) {
            model.addAttribute("empleado", empleado);
            model.addAttribute("oficinas", oficinas);
            return "editar-empleado";
        } else {
            return "redirect:/empleados";
        }
    }

    @PostMapping("/empleados/{id}/update")
    public String actualizarEmpleado(@PathVariable("id") Integer id, @ModelAttribute Empleado empleado) {
        empleado.setId(id);
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{id}/delete")
    public String eliminarEmpleado(@PathVariable("id") Integer id) {
        empleadoRepository.deleteById(id);
        return "redirect:/empleados";
    }
}
