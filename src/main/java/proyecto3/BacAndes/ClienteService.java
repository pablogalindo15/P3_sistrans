package proyecto3.BacAndes;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> allClientes(){
        return clienteRepository.findAll();

    }

    public Optional<Cliente> singleCliente(ObjectId id){
        return clienteRepository.findById(id);
    }
    
}
