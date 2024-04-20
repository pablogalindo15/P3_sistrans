package uniandes.edu.co.proyecto.modelo;
import java.sql.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name="transferencias")
public class Transferencia {
    @EmbeddedId
    private TransferenciaPK pk;
    public Transferencia(){;}

    public Transferencia(Cuenta id_cuenta_1, Cuenta id_cuenta_2,Date fecha ,Integer valor,String tipo )
    {
        this.pk = new TransferenciaPK(id_cuenta_1,id_cuenta_2,fecha,valor,tipo );
    }

    public TransferenciaPK getPk() {
        return pk;
    }

    public void setPk(TransferenciaPK pk) {
        this.pk = pk;
    }

}
