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
 * @author fmbma
 */
@Entity
@Table(name = "cja_tipos_gastos")
public class CjaTiposGastos extends SigaUsicRevisiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_gasto_clasificacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaGastosClasificaciones cjaGastosClasificaciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_gasto")
    private Long idCjaTipoGasto;

    @Column(name = "nro_tipo_gasto")
    private Integer nroTipoGasto;

    @Column(name = "tipo_gasto")
    private String tipoGasto;

    public CjaGastosClasificaciones getCjaGastosClasificaciones() {
        return cjaGastosClasificaciones;
    }

    public void setCjaGastosClasificaciones(CjaGastosClasificaciones cjaGastosClasificaciones) {
        this.cjaGastosClasificaciones = cjaGastosClasificaciones;
    }

    

    public Long getIdCjaTipoGasto() {
        return idCjaTipoGasto;
    }

    public void setIdCjaTipoGasto(Long idCjaTipoGasto) {
        this.idCjaTipoGasto = idCjaTipoGasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Integer getNroTipoGasto() {
        return nroTipoGasto;
    }

    public void setNroTipoGasto(Integer nroTipoGasto) {
        this.nroTipoGasto = nroTipoGasto;
    }
}
