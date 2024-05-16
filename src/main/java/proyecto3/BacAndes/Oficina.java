package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="oficinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oficina {
    private ObjectId _id;
    private String nombre;
    private String dir;
    private int num_pa;
    private ObjectId id_gerente;
}
