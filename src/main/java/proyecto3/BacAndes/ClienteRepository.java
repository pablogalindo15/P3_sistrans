package proyecto3.BacAndes;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;
@Repository
public interface ClienteRepository extends MongoRepository<Cliente,ObjectId>{
    
}
