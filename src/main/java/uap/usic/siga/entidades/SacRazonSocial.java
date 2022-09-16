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
 * @author Yessenia
 */
@Entity
@Table(name = "sac_razon_social")
public class SacRazonSocial extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_razon_social")
    private Long idSacRazonSocial;

    public SacComprobantes getSacComprobantes() {
        return sacComprobantes;
    }

    public void setSacComprobantes(SacComprobantes sacComprobantes) {
        this.sacComprobantes = sacComprobantes;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Long getIdSacRazonSocial() {
        return idSacRazonSocial;
    }

    public void setIdSacRazonSocial(Long idSacRazonSocial) {
        this.idSacRazonSocial = idSacRazonSocial;
    }

   

}
