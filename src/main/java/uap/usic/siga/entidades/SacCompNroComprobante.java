package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Yessenia Velasco
 */
@Entity
@Table(name = "sac_comp_nro_comprobante")
public class SacCompNroComprobante extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comp_nro_comprobante")
    private Long idSacCompNroComprobante;

    @NotNull
    @Column(name = "sac_nro_comprobante")
    private Integer sacNroComprobante;

    public SacComprobantes getSacComprobantes() {
        return sacComprobantes;
    }

    public void setSacComprobantes(SacComprobantes sacComprobantes) {
        this.sacComprobantes = sacComprobantes;
    }

    public Long getIdSacCompNroComprobante() {
        return idSacCompNroComprobante;
    }

    public void setIdSacCompNroComprobante(Long idSacCompNroComprobante) {
        this.idSacCompNroComprobante = idSacCompNroComprobante;
    }

    public Integer getSacNroComprobante() {
        return sacNroComprobante;
    }

    public void setSacNroComprobante(Integer sacNroComprobante) {
        this.sacNroComprobante = sacNroComprobante;
    }

}
