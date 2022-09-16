package uap.usic.siga.entidades;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_cogresistas")
public class CngCongresistas extends SigaUsicGestiones{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_congresista", referencedColumnName = "id_tipo_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposCongresistas cngTiposCongresistas;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_congreso_uap", referencedColumnName = "id_congreso_uap")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CongresoUap congresoUap;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FclCarreras fclCarreras;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_frente", referencedColumnName = "id_frente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscFrentes escFrentes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Personas personas;
	 
	@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "id_estamento", referencedColumnName = "id_estamento")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private FclEstamentos fclEstamentos;
	 
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cng_congresista")
    private Long idCngCongresista;

    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "foto")
    private String foto;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecInicio;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecFinal;

	public CngTiposCongresistas getCngTiposCongresistas() {
		return cngTiposCongresistas;
	}

	public void setCngTiposCongresistas(CngTiposCongresistas cngTiposCongresistas) {
		this.cngTiposCongresistas = cngTiposCongresistas;
	}

	public CongresoUap getCongresoUap() {
		return congresoUap;
	}

	public void setCongresoUap(CongresoUap congresoUap) {
		this.congresoUap = congresoUap;
	}

	public FclCarreras getFclCarreras() {
		return fclCarreras;
	}

	public void setFclCarreras(FclCarreras fclCarreras) {
		this.fclCarreras = fclCarreras;
	}

	public EscFrentes getEscFrentes() {
		return escFrentes;
	}

	public void setEscFrentes(EscFrentes escFrentes) {
		this.escFrentes = escFrentes;
	}

	public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public FclEstamentos getFclEstamentos() {
		return fclEstamentos;
	}

	public void setFclEstamentos(FclEstamentos fclEstamentos) {
		this.fclEstamentos = fclEstamentos;
	}

	public Long getIdCngCongresista() {
		return idCngCongresista;
	}

	public void setIdCngCongresista(Long idCngCongresista) {
		this.idCngCongresista = idCngCongresista;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFinal() {
		return fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}

	
    
    
}
