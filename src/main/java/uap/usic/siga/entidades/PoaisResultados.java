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
@Table(name = "poais_resultados")
public class PoaisResultados extends SigaUsicRevisiones {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poaisResultados")
	private List<PoaisActividades> poaisActividades;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_objetivo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisObjetivos  poaisObjetivos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultado")
    private Long idResultado;
	
	@Column(name = "resultado")
    private String resultado;
	
	@Column(name = "indicador")
    private String indicador;
	
	@Column(name = "ponderacion")
    private Double ponderacion;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "puntaje")
	private Double puntaje;

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public PoaisObjetivos getPoaisObjetivos() {
		return poaisObjetivos;
	}

	public void setPoaisObjetivos(PoaisObjetivos poaisObjetivos) {
		this.poaisObjetivos = poaisObjetivos;
	}

	public Long getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(Long idResultado) {
		this.idResultado = idResultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Double getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Double ponderacion) {
		this.ponderacion = ponderacion;
	}

	public List<PoaisActividades> getPoaisActividades() {
		return poaisActividades;
	}

	public void setPoaisActividades(List<PoaisActividades> poaisActividades) {
		this.poaisActividades = poaisActividades;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
