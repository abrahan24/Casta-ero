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
@Table(name = "poais_actividades")
public class PoaisActividades  extends SigaUsicRevisiones{
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "poaisActividades")
	private List<PoaisDetallesActividades> poaisDetallesActividades;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resultado", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoaisResultados  poaisResultados;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actividad")
    private Long idActividad;
	
	@Column(name = "adtividad")
    private String actividad;
	
	@Column(name = "medio_verificacion")
    private String medioVerificacion;
	
	@Column(name = "ponderacion")
	private Double ponderacion;
	
	@Column(name = "puntaje")
	private Double puntaje;
	
	@Column(name = "porcentaje")
	private Double porcentaje;

	public List<PoaisDetallesActividades> getPoaisDetallesActividades() {
		return poaisDetallesActividades;
	}

	public void setPoaisDetallesActividades(List<PoaisDetallesActividades> poaisDetallesActividades) {
		this.poaisDetallesActividades = poaisDetallesActividades;
	}

	public PoaisResultados getPoaisResultados() {
		return poaisResultados;
	}

	public void setPoaisResultados(PoaisResultados poaisResultados) {
		this.poaisResultados = poaisResultados;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getMedioVerificacion() {
		return medioVerificacion;
	}

	public void setMedioVerificacion(String medioVerificacion) {
		this.medioVerificacion = medioVerificacion;
	}

	public Double getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Double ponderacion) {
		this.ponderacion = ponderacion;
	}

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	

}
