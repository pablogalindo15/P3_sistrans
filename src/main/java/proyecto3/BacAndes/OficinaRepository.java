package proyecto3.BacAndes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends MongoRepository<Oficina, String> {
}