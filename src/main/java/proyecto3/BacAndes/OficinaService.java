package proyecto3.BacAndes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<Oficina> allOficinas() {
        return oficinaRepository.findAll();
    }

    public void saveOficina(Oficina oficina) {
        oficinaRepository.save(oficina);
    }
}