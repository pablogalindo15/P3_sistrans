package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ModelAndView listarClientes() {
        List<Cliente> clientes = clienteService.allClientes();
        return new ModelAndView("clientes").addObject("clientes", clientes);
    }

    @GetMapping("/nuevo")
    public ModelAndView mostrarFormularioNuevoCliente() {
        return new ModelAndView("clienteNuevo").addObject("cliente", new Cliente());
    }

    @PostMapping("/new/save")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }
}
