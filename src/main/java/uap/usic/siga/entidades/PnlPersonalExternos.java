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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "pnl_personal_externos")
public class PnlPersonalExternos extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_tipo_externo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalExternos pnlPersonalExternos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_personal_externo")
    private Long idPnlPersonalExterno;

    public PnlPersonalExternos getPnlPersonalExternos() {
        return pnlPersonalExternos;
    }

    public void setPnlPersonalExternos(PnlPersonalExternos pnlPersonalExternos) {
        this.pnlPersonalExternos = pnlPersonalExternos;
    }

    public PnlCargos getPnlCargos() {
        return pnlCargos;
    }

    public void setPnlCargos(PnlCargos pnlCargos) {
        this.pnlCargos = pnlCargos;
    }

    public InsUnidadesFuncionales getInsUnidadesFuncionales() {
        return insUnidadesFuncionales;
    }

    public void setInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
        this.insUnidadesFuncionales = insUnidadesFuncionales;
    }

    public Long getIdPnlPersonalExterno() {
        return idPnlPersonalExterno;
    }

    public void setIdPnlPersonalExterno(Long idPnlPersonalExterno) {
        this.idPnlPersonalExterno = idPnlPersonalExterno;
    }
    

}
