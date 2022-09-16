package uap.usic.siga.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais_objetivos")
public class PoaisObjetivos  extends SigaUsicRevisiones{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Poais  poais;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisObjetivos")
	private List<PoaisResultados> poaisResultados;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_objetivo")
    private Long idObjetivo;
	
	
	public Poais getPoais() {
		return poais;
	}

	public void setPoais(Poais poais) {
		this.poais = poais;
	}

	public Long getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(Long idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public List<PoaisResultados> getPoaisResultados() {
		return poaisResultados;
	}

	public void setPoaisResultados(List<PoaisResultados> poaisResultados) {
		this.poaisResultados = poaisResultados;
	}
	
	
}
