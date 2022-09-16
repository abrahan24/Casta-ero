package uap.usic.siga.entidades;

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

/**
* Rectorado - USIC
* Fecha: 2021-07-09
* @author Freddy Morales
*/
@Entity
@Table(name = "esc_resultados_graficos")
public class EscResultadosGraficos extends SigaUsicGestiones{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Facultades facultades;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclCarreras fclCarreras;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estamento", referencedColumnName = "id_estamento")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclEstamentos fclEstamentos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_eleccion", referencedColumnName = "id_eleccion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscElecciones escElecciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente", referencedColumnName = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado_grafico")
    private Long idResultadoGrafico;

    @Column(name = "votos_validos")
    private Double votosValidos;

    @Column(name = "votos_nulos")
    private Double votosNulos;
    
    @Column(name = "votos_blancos")
    private Double votosBlancos;
    
    @Column(name = "votos_frente")
    private Double votosFrente;

	public Facultades getFacultades() {
		return facultades;
	}

	public void setFacultades(Facultades facultades) {
		this.facultades = facultades;
	}

	public FclCarreras getFclCarreras() {
		return fclCarreras;
	}

	public void setFclCarreras(FclCarreras fclCarreras) {
		this.fclCarreras = fclCarreras;
	}

	public FclEstamentos getFclEstamentos() {
		return fclEstamentos;
	}

	public void setFclEstamentos(FclEstamentos fclEstamentos) {
		this.fclEstamentos = fclEstamentos;
	}

	public EscElecciones getEscElecciones() {
		return escElecciones;
	}

	public void setEscElecciones(EscElecciones escElecciones) {
		this.escElecciones = escElecciones;
	}

	public EscFrentes getEscFrentes() {
		return escFrentes;
	}

	public void setEscFrentes(EscFrentes escFrentes) {
		this.escFrentes = escFrentes;
	}

	public Long getIdResultadoGrafico() {
		return idResultadoGrafico;
	}

	public void setIdResultadoGrafico(Long idResultadoGrafico) {
		this.idResultadoGrafico = idResultadoGrafico;
	}

	public Double getVotosValidos() {
		return votosValidos;
	}

	public void setVotosValidos(Double votosValidos) {
		this.votosValidos = votosValidos;
	}

	public Double getVotosNulos() {
		return votosNulos;
	}

	public void setVotosNulos(Double votosNulos) {
		this.votosNulos = votosNulos;
	}

	public Double getVotosBlancos() {
		return votosBlancos;
	}

	public void setVotosBlancos(Double votosBlancos) {
		this.votosBlancos = votosBlancos;
	}

	public Double getVotosFrente() {
		return votosFrente;
	}

	public void setVotosFrente(Double votosFrente) {
		this.votosFrente = votosFrente;
	}
    
    
    
}
