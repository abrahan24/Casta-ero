package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC 
 * Fecha: 2019-05-28
 * @author Freddy Morales
 */
@Entity
@Table(name = "prs_tipos_sexos")
public class PrsTiposSexos extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_tipo_sexo")
    private Long idPrsTipoSexo;
    
    @Size(max = 50)
    @NotEmpty
    @Column(name = "prs_tipo_sexo")
    private String prsTipoSexo;

    public Long getIdPrsTipoSexo() {
        return idPrsTipoSexo;
    }

    public void setIdPrsTipoSexo(Long idPrsTipoSexo) {
        this.idPrsTipoSexo = idPrsTipoSexo;
    }

    public String getPrsTipoSexo() {
        return prsTipoSexo;
    }

    public void setPrsTipoSexo(String prsTipoSexo) {
        this.prsTipoSexo = prsTipoSexo;
    }

    
}
