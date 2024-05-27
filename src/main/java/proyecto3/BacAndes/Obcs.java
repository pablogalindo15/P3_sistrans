package proyecto3.BacAndes;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="obcs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Obcs {
    private ObjectId _id;
    private Date fecha;
    private Integer valor;
    private String tipo;
    private ObjectId id_cuenta;
}
