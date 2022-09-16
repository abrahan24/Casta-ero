package uap.usic.siga.servicios;

import uap.usic.siga.entidades.InsSedes;

public interface InsSedesService {
    
      boolean updateInsSedes(InsSedes ins);

    InsSedes getInsSedes(Long id);    
}
