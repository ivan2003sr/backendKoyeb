package com.portfolio.ivan200sr.security.service;

import com.portfolio.ivan200sr.security.entity.Rol;
import com.portfolio.ivan200sr.security.enums.RolNombre;
import com.portfolio.ivan200sr.security.repository.iRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        irolRepository.save(rol);
    }

}
