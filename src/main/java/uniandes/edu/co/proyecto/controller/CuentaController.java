package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

import java.util.List;

@Controller
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cuentas")
    public String listarCuentas(Model model) {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        model.addAttribute("cuentas", cuentas);
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String formularioNuevaCuenta(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("clientes", clientes);
        return "nueva-cuenta";
    }

    @PostMapping("/cuentas/save")
    public String guardarCuenta(@ModelAttribute Cuenta cuenta) {
        cuentaRepository.save(cuenta);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/edit")
    public String formularioEditarCuenta(@PathVariable("id") Integer id, Model model) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        List<Cliente> clientes = clienteRepository.findAll();
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            model.addAttribute("clientes", clientes);
            return "editar-cuenta";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/cuentas/{id}/update")
    public String actualizarCuenta(@PathVariable("id") Integer id, @ModelAttribute Cuenta cuenta) {
        cuenta.setId(id);
        cuentaRepository.save(cuenta);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/delete")
    public String eliminarCuenta(@PathVariable("id") Integer id) {
        cuentaRepository.deleteById(id);
        return "redirect:/cuentas";
    }
}
