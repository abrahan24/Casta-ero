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
@Table(name = "cja_cnt_unidades")
public class CjaCntUnidades extends SigaUsicGestiones {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_cnt_unidades")
    private Long idCjaCntUnidades;

    @Column(name = "totalGasto")
    private Double totalGasto;

	public InsUnidadesFuncionales getInsUnidadesFuncionales() {
		return insUnidadesFuncionales;
	}

	public void setInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
		this.insUnidadesFuncionales = insUnidadesFuncionales;
	}

	public Long getIdCjaCntUnidades() {
		return idCjaCntUnidades;
	}

	public void setIdCjaCntUnidades(Long idCjaCntUnidades) {
		this.idCjaCntUnidades = idCjaCntUnidades;
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
