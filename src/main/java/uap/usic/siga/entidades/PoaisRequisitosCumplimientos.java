package uap.usic.siga.entidades;

import javax.persistence.Basic;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais_requisitos_cumplimientos")
public class PoaisRequisitosCumplimientos extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos poaisRequisitos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_cumplimiento", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisTiposCumplimientos poaisTiposCumplimientos; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito_cumplimiento")
    private Long idRequisitoCumplimiento;
	
	@Column(name = "requisito_cumpkimiento")
    private String requisitoCumplimiento;

	public PoaisRequisitos getPoaisRequisitos() {
		return poaisRequisitos;
	}

	public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		this.poaisRequisitos = poaisRequisitos;
	}

	public PoaisTiposCumplimientos getPoaisTiposCumplimientos() {
		return poaisTiposCumplimientos;
	}

	public void setPoaisTiposCumplimientos(PoaisTiposCumplimientos poaisTiposCumplimientos) {
		this.poaisTiposCumplimientos = poaisTiposCumplimientos;
	}

	public Long getIdRequisitoCumplimiento() {
		return idRequisitoCumplimiento;
	}

	public void setIdRequisitoCumplimiento(Long idRequisitoCumplimiento) {
		this.idRequisitoCumplimiento = idRequisitoCumplimiento;
	}

	public String getRequisitoCumplimiento() {
		return requisitoCumplimiento;
	}

	public void setRequisitoCumplimiento(String requisitoCumplimiento) {
		this.requisitoCumplimiento = requisitoCumplimiento;
	}

	
}
