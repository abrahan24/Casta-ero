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
@Table(name = "scs_tipos_contratos")
public class ScsTiposContratos extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_contrato")
    private Long idScsTipoContrato;
    
    @NotEmpty
    @Column(name = "scs_tipo_contrato")
    private String scsTipoContrato;

    public Long getIdScsTipoContrato() {
        return idScsTipoContrato;
    }

    public void setIdScsTipoContrato(Long idScsTipoContrato) {
        this.idScsTipoContrato = idScsTipoContrato;
    }

    public String getScsTipoContrato() {
        return scsTipoContrato;
    }

    public void setScsTipoContrato(String scsTipoContrato) {
        this.scsTipoContrato = scsTipoContrato;
    }
   
    
    
}
