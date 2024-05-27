package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;



@Repository
public interface ObcsRepository extends MongoRepository<Obcs, ObjectId> {

    @Query("{_id: ?0}")
    @Update("{$inc: {'saldo': ?1}}")
    void consignacion(String id_cuenta, int incremento);

    @Query("{_id: ?0}")
    @Update("{$inc: {'saldo': ?1}}")
    void retiro(String id_cuenta, int incremento);

}

// Add the @Document annotation to the Obcs class declaration

    

