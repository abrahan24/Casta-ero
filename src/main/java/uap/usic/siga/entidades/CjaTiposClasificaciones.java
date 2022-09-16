package uap.usic.siga.entidades;

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
@Table(name = "cja_tipos_clasificaciones")
public class CjaTiposClasificaciones extends SigaUsicRevisiones {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_clasificacion")
    private Long idCjaTipoClasificacion;

    @Column(name = "tipo_clasificacion")
    private String tipoClasificacion;
    
    @Column(name = "nro_tipo_clasificacion")
    private Integer nroTipoClasificacion;

	public Long getIdCjaTipoClasificacion() {
		return idCjaTipoClasificacion;
	}

	public void setIdCjaTipoClasificacion(Long idCjaTipoClasificacion) {
		this.idCjaTipoClasificacion = idCjaTipoClasificacion;
	}

	public String getTipoClasificacion() {
		return tipoClasificacion;
	}

	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	public Integer getNroTipoClasificacion() {
		return nroTipoClasificacion;
	}

	public void setNroTipoClasificacion(Integer nroTipoClasificacion) {
		this.nroTipoClasificacion = nroTipoClasificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    
}
