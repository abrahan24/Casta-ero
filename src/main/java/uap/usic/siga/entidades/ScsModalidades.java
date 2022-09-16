package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_modalidades")
public class ScsModalidades extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_modalidad")
    private Long idScsModalidad;
    
    @NotEmpty
    @Column(name = "scs_modalidad")
    private String scsModalidad;

    public Long getIdScsModalidad() {
        return idScsModalidad;
    }

    public void setIdScsModalidad(Long idScsModalidad) {
        this.idScsModalidad = idScsModalidad;
    }

    public String getScsModalidad() {
        return scsModalidad;
    }

    public void setScsModalidad(String scsModalidad) {
        this.scsModalidad = scsModalidad;
    }
  
    
}
