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
@Table(name = "esc_detalles")
public class EscDetalles extends SigaUsicGestiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_escrutinio_acta", referencedColumnName = "id_escrutinio_acta")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscrutinioActas escrutinioActas;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente", referencedColumnName = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;
			
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esc_detalle")
    private Long idEscDetalle;

    @Column(name = "voto_frente")
    private Double votoFrente;

	public EscrutinioActas getEscrutinioActas() {
		return escrutinioActas;
	}

	public void setEscrutinioActas(EscrutinioActas escrutinioActas) {
		this.escrutinioActas = escrutinioActas;
	}

	public EscFrentes getEscFrentes() {
		return escFrentes;
	}

	public void setEscFrentes(EscFrentes escFrentes) {
		this.escFrentes = escFrentes;
	}

	public Long getIdEscDetalle() {
		return idEscDetalle;
	}

	public void setIdEscDetalle(Long idEscDetalle) {
		this.idEscDetalle = idEscDetalle;
	}

	public Double getVotoFrente() {
		return votoFrente;
	}

	public void setVotoFrente(Double votoFrente) {
		this.votoFrente = votoFrente;
	}

	
    
}
