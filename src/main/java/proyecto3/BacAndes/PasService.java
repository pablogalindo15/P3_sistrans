package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasService {

    @Autowired
    private PasRepository pasRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    public List<Pas> allPas() {
        return pasRepository.findAll();
    }

    public List<Oficina> allOficinas() {
        return oficinaRepository.findAll();
    }

    public void savePas(Pas pas) {
        pasRepository.save(pas);
    }

    public void deletePas(ObjectId id) {
        pasRepository.deleteById(id);
    }
}