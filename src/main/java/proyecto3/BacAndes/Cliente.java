package proyecto3.BacAndes;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    private ObjectId _id;
    private int num_doc;
    private String tipo_doc;
    private String nombre;
    private String dir;
    private String mail;
    private String tel;
    private String ciudad;
    private String clave;
    private String login;
    private String nacionalidad;
    private String dept;
    private String codigo_post;
    private String tipo;
    private String estado;
}
