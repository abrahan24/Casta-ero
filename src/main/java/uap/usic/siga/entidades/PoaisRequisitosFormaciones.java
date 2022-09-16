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
@Table(name = "poais_requisitos_formaciones")
public class PoaisRequisitosFormaciones extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_requisito", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisRequisitos  poaisRequisitos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito_formacion")
    private Long idRequisitoFormacion;
	
	@Column(name = "requisito_formacion")
	private String requisitoFormacion;

	public PoaisRequisitos getPoaisRequisitos() {
		return poaisRequisitos;
	}

	public void setPoaisRequisitos(PoaisRequisitos poaisRequisitos) {
		this.poaisRequisitos = poaisRequisitos;
	}

	public Long getIdRequisitoFormacion() {
		return idRequisitoFormacion;
	}

	public void setIdRequisitoFormacion(Long idRequisitoFormacion) {
		this.idRequisitoFormacion = idRequisitoFormacion;
	}

	public String getRequisitoFormacion() {
		return requisitoFormacion;
	}

	public void setRequisitoFormacion(String requisitoFormacion) {
		this.requisitoFormacion = requisitoFormacion;
	}
	
	
}
