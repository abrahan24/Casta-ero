
package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "pnl_tipos_externos")
public class PnlTiposExternos extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_tipo_externo")
    private Long idPnlTipoExterno;
    
    @NotEmpty
    @Column(name = "tipo_externo")
    private String tipoExterno;

    public Long getIdPnlTipoExterno() {
        return idPnlTipoExterno;
    }

    public void setIdPnlTipoExterno(Long idPnlTipoExterno) {
        this.idPnlTipoExterno = idPnlTipoExterno;
    }

    public String getTipoExterno() {
        return tipoExterno;
    }

    public void setTipoExterno(String tipoExterno) {
        this.tipoExterno = tipoExterno;
    }
    
}
