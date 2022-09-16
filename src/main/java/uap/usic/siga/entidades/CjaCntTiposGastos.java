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
 * Rectorado - USIC
 * Fecha: 2019-07-29
 * @author Freddy Morales
 */
@Entity
@Table(name = "cja_cnt_tipos_gastos")
public class CjaCntTiposGastos extends SigaUsicGestiones{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_cnt_tipo_gasto")
    private Long idCjaCntTipoGasto;

    @Column(name = "totalGasto")
    private Double totalGasto;

	public CjaTiposGastos getCjaTiposGastos() {
		return cjaTiposGastos;
	}

	public void setCjaTiposGastos(CjaTiposGastos cjaTiposGastos) {
		this.cjaTiposGastos = cjaTiposGastos;
	}

	public Long getIdCjaCntTipoGasto() {
		return idCjaCntTipoGasto;
	}

	public void setIdCjaCntTipoGasto(Long idCjaCntTipoGasto) {
		this.idCjaCntTipoGasto = idCjaCntTipoGasto;
	}

	public Double getTotalGasto() {
		return totalGasto;
	}

	public void setTotalGasto(Double totalGasto) {
		this.totalGasto = totalGasto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}
