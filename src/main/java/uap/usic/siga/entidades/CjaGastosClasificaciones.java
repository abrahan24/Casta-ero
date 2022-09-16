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
 * @author pppppppppppp
 */
@Entity
@Table(name = "cja_gastos_clasificaciones")
public class CjaGastosClasificaciones extends SigaUsicRevisiones{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_clasificacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposClasificaciones cjaTiposClasificaciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_gasto_clasificacion")
    private Long idCjaGastoClasificacion;

    @Column(name = "gasto_clasificacion")
    private String gastoClasificacion;
    
    @Column(name = "nro_gasto_clasificacion")
    private Integer nroGastoClasificacion;

	public CjaTiposClasificaciones getCjaTiposClasificaciones() {
		return cjaTiposClasificaciones;
	}

	public void setCjaTiposClasificaciones(CjaTiposClasificaciones cjaTiposClasificaciones) {
		this.cjaTiposClasificaciones = cjaTiposClasificaciones;
	}

	public Long getIdCjaGastoClasificacion() {
		return idCjaGastoClasificacion;
	}

	public void setIdCjaGastoClasificacion(Long idCjaGastoClasificacion) {
		this.idCjaGastoClasificacion = idCjaGastoClasificacion;
	}

	public String getGastoClasificacion() {
		return gastoClasificacion;
	}

	public void setGastoClasificacion(String gastoClasificacion) {
		this.gastoClasificacion = gastoClasificacion;
	}

	public Integer getNroGastoClasificacion() {
		return nroGastoClasificacion;
	}

	public void setNroGastoClasificacion(Integer nroGastoClasificacion) {
		this.nroGastoClasificacion = nroGastoClasificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
