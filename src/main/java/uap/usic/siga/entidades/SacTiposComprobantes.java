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
 * @author Yessenia
 */
@Entity
@Table(name = "sac_tipos_comprobantes")
public class SacTiposComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_comprobante")
    private Long idSacTipoComprobante;

    @NotEmpty
    @Column(name = "sac_tipo_comprobante")
    private String sacTipoComprobante;

    public Long getIdSacTipoComprobante() {
        return idSacTipoComprobante;
    }

    public void setIdSacTipoComprobante(Long idSacTipoComprobante) {
        this.idSacTipoComprobante = idSacTipoComprobante;
    }

    public String getSacTipoComprobante() {
        return sacTipoComprobante;
    }

    public void setSacTipoComprobante(String sacTipoComprobante) {
        this.sacTipoComprobante = sacTipoComprobante;
    }
    
}
