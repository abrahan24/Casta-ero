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
@Table(name = "scs_boletas_respaldatorias")
public class ScsBoletasRespaldatorias extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_boleta_respaldatoria")
    private Long idScsBoletaRespaldatoria;
    
    @NotEmpty
    @Column(name = "scs_boleta_respaldatorioa")
    private String scsBoletaRespaldatoria;
 
    @NotEmpty
    @Column(name = "scs_codigo_boleta_respaldatoria")
    private String scsCodigoBoletaRespaldatoria;

    public Long getIdScsBoletaRespaldatoria() {
        return idScsBoletaRespaldatoria;
    }

    public void setIdScsBoletaRespaldatoria(Long idScsBoletaRespaldatoria) {
        this.idScsBoletaRespaldatoria = idScsBoletaRespaldatoria;
    }

    public String getScsBoletaRespaldatoria() {
        return scsBoletaRespaldatoria;
    }

    public void setScsBoletaRespaldatoria(String scsBoletaRespaldatoria) {
        this.scsBoletaRespaldatoria = scsBoletaRespaldatoria;
    }

    public String getScsCodigoBoletaRespaldatoria() {
        return scsCodigoBoletaRespaldatoria;
    }

    public void setScsCodigoBoletaRespaldatoria(String scsCodigoBoletaRespaldatoria) {
        this.scsCodigoBoletaRespaldatoria = scsCodigoBoletaRespaldatoria;
    }
  
    
}
