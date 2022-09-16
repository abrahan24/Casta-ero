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
@Table(name = "sac_estados_comprobantes")
public class SacEstadosComprobantes extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_estado_comprobante")
    private Long idSacEstadoComprobante;

    @NotEmpty
    @Column(name = "sac_nombre_estado")
    private String sacNombreEstado;

    public Long getIdSacEstadoComprobante() {
        return idSacEstadoComprobante;
    }

    public void setIdSacEstadoComprobante(Long idSacEstadoComprobante) {
        this.idSacEstadoComprobante = idSacEstadoComprobante;
    }

    public String getSacNombreEstado() {
        return sacNombreEstado;
    }

    public void setSacNombreEstado(String sacNombreEstado) {
        this.sacNombreEstado = sacNombreEstado;
    }

}
