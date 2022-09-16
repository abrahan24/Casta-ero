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
@Table(name = "esc_frentes")
public class EscFrentes extends SigaUsicGestiones {

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
	 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_frente")
    private Long idFrente;

    @Column(name = "frente")
    private String frente;
    
    @Column(name = "sigla")
    private String sigla;

    @Column(name = "nro_integrantes")
    private Integer nroIntegrantes;

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

	public Long getIdFrente() {
		return idFrente;
	}

	public void setIdFrente(Long idFrente) {
		this.idFrente = idFrente;
	}

	public String getFrente() {
		return frente;
	}

	public void setFrente(String frente) {
		this.frente = frente;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getNroIntegrantes() {
		return nroIntegrantes;
	}

	public void setNroIntegrantes(Integer nroIntegrantes) {
		this.nroIntegrantes = nroIntegrantes;
	}

	
}
