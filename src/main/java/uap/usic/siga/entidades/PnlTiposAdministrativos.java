package uap.usic.siga.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "pnl_tipos_administrativos")
public class PnlTiposAdministrativos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_tipo_administrativo")
    private Long idPnlTipoAdtministrativo;

    @Column(name = "tipo_administrativo")
    private String tipoAdministrativo;

   

    public Long getIdPnlTipoAdtministrativo() {
        return idPnlTipoAdtministrativo;
    }

    public void setIdPnlTipoAdtministrativo(Long idPnlTipoAdtministrativo) {
        this.idPnlTipoAdtministrativo = idPnlTipoAdtministrativo;
    }

    public String getTipoAdministrativo() {
        return tipoAdministrativo;
    }

    public void setTipoAdministrativo(String tipoAdministrativo) {
        this.tipoAdministrativo = tipoAdministrativo;
    }

}
