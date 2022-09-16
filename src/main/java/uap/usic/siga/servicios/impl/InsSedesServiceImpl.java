package uap.usic.siga.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.modelos.InsSedesRepository;
import uap.usic.siga.servicios.InsSedesService;

@Service
@Transactional
public class InsSedesServiceImpl implements InsSedesService{
    
     
     @Autowired
    private InsSedesRepository insSedesRepository;

    @Override
    public boolean updateInsSedes(InsSedes ins) {
        return insSedesRepository.update(ins);
    }

    @Override
    public InsSedes getInsSedes(Long id) {
        return insSedesRepository.findOne(id);
    }

}
