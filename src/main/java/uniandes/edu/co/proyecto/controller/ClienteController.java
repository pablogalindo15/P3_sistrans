package uniandes.edu.co.proyecto.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String listarClientes(@RequestParam(required = false) Integer id, Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        Stream<Cliente> filteredClientes = clientes.stream();
    
        if (id!= null) {
            filteredClientes = filteredClientes.filter(c -> id.equals(c.getId()));
        }
    
        List<Cliente> sortedClientes = filteredClientes
           .sorted(Comparator.comparing(Cliente::getNombre))
           .collect(Collectors.toList());
    
        model.addAttribute("clientes", sortedClientes);
        return "clientes";
    }

    @GetMapping("/clientes/new")
    public String clienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clienteNuevo";
    }

    @GetMapping("/clientes/menu")
    public String clientesMenu(){
        return("clientesMenu");
    }

    @GetMapping("/empleados/menu")
    public String EmpleadosMenu(){
        return("empleadosMenu");
    }

    @PostMapping("/clientes/new/save")
    public String clienteGuardar(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/edit")
    public String clienteEditarForm(@PathVariable("id") Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "clienteEditar";
        } else {
            return "redirect:/clientes";
        }
    }

    @PostMapping("/clientes/{id}/edit/save")
    public String clienteEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute Cliente cliente) {
        cliente.setId(id);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/delete")
    public String clienteEliminar(@PathVariable("id") Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
 
}
