package uniandes.edu.co.proyecto.servicios;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Transferencia;
import uniandes.edu.co.proyecto.repositorio.TransferenciaRepository;  


@Service
public class transferenciaServicio {
    
    private TransferenciaRepository transferenciaRepository;

    public transferenciaServicio(TransferenciaRepository transferenciaRepository)
    {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Transferencia> darTransferenciasSerializable(Integer id) throws InterruptedException {
        Collection<Transferencia> transferencias = transferenciaRepository.darTransferenciasIdCuenta(id); 
        Thread.sleep(30000);

        return transferencias;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<Transferencia> darTransferenciaRC(Integer id) throws InterruptedException {
        Collection<Transferencia> transferencias = transferenciaRepository.darTransferenciasIdCuenta(id); 
        Thread.sleep(30000);
        return transferencias;
    }
}

