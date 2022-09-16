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
@Table(name = "scs_formularios")
public class ScsFormularios extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_formulario")
    private Long idScsFormulario;
    
    @NotEmpty
    @Column(name = "scs_formulario")
    private String scsFormulario;
 
    @NotEmpty
    @Column(name = "scs_codigo_formulario")
    private String scsCodigoFormulario;

    public Long getIdScsFormulario() {
        return idScsFormulario;
    }

    public void setIdScsFormulario(Long idScsFormulario) {
        this.idScsFormulario = idScsFormulario;
    }

    public String getScsFormulario() {
        return scsFormulario;
    }

    public void setScsFormulario(String scsFormulario) {
        this.scsFormulario = scsFormulario;
    }

    public String getScsCodigoFormulario() {
        return scsCodigoFormulario;
    }

    public void setScsCodigoFormulario(String scsCodigoFormulario) {
        this.scsCodigoFormulario = scsCodigoFormulario;
    }
  
    
}
