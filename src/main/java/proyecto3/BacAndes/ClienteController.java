package proyecto3.BacAndes;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return new ResponseEntity<List<Cliente>>(clienteService.allClientes(),HttpStatus.OK);}

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> getSingleCliente(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Cliente>>(clienteService.singleCliente(id),HttpStatus.OK);
    }
    
}

