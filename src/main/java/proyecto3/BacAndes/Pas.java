package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "pas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pas {
    @Id
    private ObjectId _id;
    private String tipo;
    private ObjectId id_oficina;  
}