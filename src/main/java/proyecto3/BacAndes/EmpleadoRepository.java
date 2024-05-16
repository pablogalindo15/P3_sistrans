package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, ObjectId> {
    List<Empleado> findByCargo(String cargo);
}