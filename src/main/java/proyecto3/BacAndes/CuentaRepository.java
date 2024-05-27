package proyecto3.BacAndes;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

import org.bson.types.ObjectId;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta, ObjectId> {

    @Query("{ 'idCliente' : ?0 }")
    List<Cuenta> findCuentasByIdCliente(String idCliente);
    
}