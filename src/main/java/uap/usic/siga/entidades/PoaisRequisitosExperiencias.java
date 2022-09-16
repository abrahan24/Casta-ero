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
@Table(name = "poais_requisitos_experiencias")
public class PoaisRequisitosExperiencias extends SigaUsicRevisiones{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos  poaisRequisitos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito_experiencia")
    private Long idRequisitoExperiencia;
	
	@Column(name = "requisito_experiencia")
	private String requisitoExperiencia;

	public PoaisRequisitos getPoaisRequisitos() {
		return poaisRequisitos;
	}

	public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		this.poaisRequisitos = poaisRequisitos;
	}

	public Long getIdRequisitoExperiencia() {
		return idRequisitoExperiencia;
	}

	public void setIdRequisitoExperiencia(Long idRequisitoExperiencia) {
		this.idRequisitoExperiencia = idRequisitoExperiencia;
	}

	public String getRequisitoExperiencia() {
		return requisitoExperiencia;
	}

	public void setRequisitoExperiencia(String requisitoExperiencia) {
		this.requisitoExperiencia = requisitoExperiencia;
	}
	
	
}
