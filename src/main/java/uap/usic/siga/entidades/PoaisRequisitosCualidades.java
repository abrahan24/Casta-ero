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
@Table(name = "poais_requisitos_cualidades")
public class PoaisRequisitosCualidades extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos  poaisRequisitos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito_cualidad")
    private Long idRequisitoCualidad;
	
	@Column(name = "requisito_cualidad")
	private String requisitoCualidad;

	public PoaisRequisitos getPoaisRequisitos() {
		return poaisRequisitos;
	}

	public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		this.poaisRequisitos = poaisRequisitos;
	}

	public Long getIdRequisitoCualidad() {
		return idRequisitoCualidad;
	}

	public void setIdRequisitoCualidad(Long idRequisitoCualidad) {
		this.idRequisitoCualidad = idRequisitoCualidad;
	}

	public String getRequisitoCualidad() {
		return requisitoCualidad;
	}

	public void setRequisitoCualidad(String requisitoCualidad) {
		this.requisitoCualidad = requisitoCualidad;
	}
	
	
}

