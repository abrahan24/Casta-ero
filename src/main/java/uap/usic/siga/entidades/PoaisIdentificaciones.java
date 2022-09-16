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

/**
 *
 * @author
 */
@Entity
@Table(name = "poais_identificaciones")
public class PoaisIdentificaciones extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Poais  poais;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_supervisor", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private PoaisSupervisores poaisSupervisores;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_identificacion")
	private Long idIdentificacion;
	
	@Column(name = "relacion_interinstitucional")
    private String relacionInterinstitucional;
	
	@Column(name = "relacion_intrainstitucional")
    private String relacionIntrainstitucional;

	public Poais getPoais() {
		return poais;
	}

	public void setPoais(Poais poais) {
		this.poais = poais;
	}

	public PnlCargos getPnlCargos() {
		return pnlCargos;
	}

	public void setPnlCargos(PnlCargos pnlCargos) {
		this.pnlCargos = pnlCargos;
	}

	public PoaisSupervisores getPoaisSupervisores() {
		return poaisSupervisores;
	}

	public void setPoaisSupervisores(PoaisSupervisores poaisSupervisores) {
		this.poaisSupervisores = poaisSupervisores;
	}

	public Long getIdIdentificacion() {
		return idIdentificacion;
	}

	public void setIdIdentificacion(Long idIdentificacion) {
		this.idIdentificacion = idIdentificacion;
	}

	public String getRelacionInterinstitucional() {
		return relacionInterinstitucional;
	}

	public void setRelacionInterinstitucional(String relacionInterinstitucional) {
		this.relacionInterinstitucional = relacionInterinstitucional;
	}

	public String getRelacionIntrainstitucional() {
		return relacionIntrainstitucional;
	}

	public void setRelacionIntrainstitucional(String relacionIntrainstitucional) {
		this.relacionIntrainstitucional = relacionIntrainstitucional;
	}

				
}
