package uniandes.edu.co.proyecto.servicios;

import java.util.Collection;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Obc;
import uniandes.edu.co.proyecto.repositorio.ObcRepository;

import jakarta.persistence.LockModeType;

import java.lang.Thread;
import java.util.List;  


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
        return obcs;
    }


}