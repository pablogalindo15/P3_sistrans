package uniandes.edu.co.proyecto.servicios;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Obc;
import uniandes.edu.co.proyecto.repositorio.ObcRepository;  


@Service
public class obcServicio {
    
    private ObcRepository obcRepository;

    public obcServicio(ObcRepository obcRepository)
    {
        this.obcRepository = obcRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public Collection<Obc> darObcsIdCuenta(Integer id) throws InterruptedException {
        Collection<Obc> obcs = obcRepository.darObcsIdCuenta(id); 
        Thread.sleep(30000);
        return obcs;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Collection<Obc> darObcsIdCuentaRC(Integer id) throws InterruptedException {
        Collection<Obc> obcs = obcRepository.darObcsIdCuenta(id); 
        Thread.sleep(30000);
        return obcs;
    }


}