package uap.usic.siga.entidades;

import java.util.Date;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_resoluciones_digitales")
public class GdocResolucionesDigitales extends SigaUsicGestiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_consejo", referencedColumnName = "id_gdoc_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocConsejos gdocConsejos;  

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_gestion_consejo", referencedColumnName = "id_gdoc_gestion_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocGestionConsejos gdocGestionConsejos;   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_gdoc_resolucion_digita")
   private Long idGdocResolucionDigital;

    @NotNull
    @Column(name = "nro_resolucion")
   private String  nroResolucion;   

    @NotNull
    @Column(name = "titulo_resolucion")
   private String  tituloResolucion;     

    @NotNull
    @Column(name = "vistos_resolucion")
   private String  vistosResolucion;   

    @NotNull
    @Column(name = "considerandos_resolucion")
   private String  considerandosResolucion;   

    @NotNull
    @Column(name = "porlotanto_resolucion")
   private String  porlotantoResolucion;   
    
    @NotNull
    @Column(name = "resuelve_resolucion")
   private String  resuelveResolucion;   

    @NotNull
    @Column(name = "esdado_resolucion")
   private String  esdadoResolucion;   
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_resolucion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date  fecResolucion;

	public GdocConsejos getGdocConsejos() {
		return gdocConsejos;
	}

	public void setGdocConsejos(GdocConsejos gdocConsejos) {
		this.gdocConsejos = gdocConsejos;
	}

	public GdocGestionConsejos getGdocGestionConsejos() {
		return gdocGestionConsejos;
	}

	public void setGdocGestionConsejos(GdocGestionConsejos gdocGestionConsejos) {
		this.gdocGestionConsejos = gdocGestionConsejos;
	}

	public Long getIdGdocResolucionDigital() {
		return idGdocResolucionDigital;
	}

	public void setIdGdocResolucionDigital(Long idGdocResolucionDigital) {
		this.idGdocResolucionDigital = idGdocResolucionDigital;
	}

	public String getNroResolucion() {
		return nroResolucion;
	}

	public void setNroResolucion(String nroResolucion) {
		this.nroResolucion = nroResolucion;
	}

	public String getTituloResolucion() {
		return tituloResolucion;
	}

	public void setTituloResolucion(String tituloResolucion) {
		this.tituloResolucion = tituloResolucion;
	}

	public String getVistosResolucion() {
		return vistosResolucion;
	}

	public void setVistosResolucion(String vistosResolucion) {
		this.vistosResolucion = vistosResolucion;
	}

	public String getConsiderandosResolucion() {
		return considerandosResolucion;
	}

	public void setConsiderandosResolucion(String considerandosResolucion) {
		this.considerandosResolucion = considerandosResolucion;
	}

	public String getPorlotantoResolucion() {
		return porlotantoResolucion;
	}

	public void setPorlotantoResolucion(String porlotantoResolucion) {
		this.porlotantoResolucion = porlotantoResolucion;
	}

	public String getResuelveResolucion() {
		return resuelveResolucion;
	}

	public void setResuelveResolucion(String resuelveResolucion) {
		this.resuelveResolucion = resuelveResolucion;
	}

	public String getEsdadoResolucion() {
		return esdadoResolucion;
	}

	public void setEsdadoResolucion(String esdadoResolucion) {
		this.esdadoResolucion = esdadoResolucion;
	}

	public Date getFecResolucion() {
		return fecResolucion;
	}

	public void setFecResolucion(Date fecResolucion) {
		this.fecResolucion = fecResolucion;
	}

        
}
