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
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_convenios")
public class GdocConvenios extends SigaUsicGestiones{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_autoridad", referencedColumnName = "id_gdoc_autoridad")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocAutoridades gdocAutoridades; 
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_representante", referencedColumnName = "id_gdoc_representante")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocRepresentantes gdocRepresentantes; 

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_tipo_convenio", referencedColumnName = "id_gdoc_tipo_convenio")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocTiposConvenios gdocTiposConvenios; 

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_institucion", referencedColumnName = "id_institucion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private  Instituciones instituciones; 
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_consejo", referencedColumnName = "id_gdoc_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocConsejos gdocConsejos;  

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_archivo_adjunto", referencedColumnName = "id_gdoc_archivo_adjunto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocArchivosAdjuntos gdocArchivosAdjuntos;  

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_convenio")
    private Long idGdocConvenio;
    
    @NotNull
     @Column(name = "nro_convenio")
    private String  nroConvenio;   
    
    @Column(name = "nro_folio")
    private Integer nroFolio;
     
    @NotNull 
    @Column(name = "objeto_convenio")
    private String  objetoConvenio;   

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

    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo;

	public GdocAutoridades getGdocAutoridades() {
		return gdocAutoridades;
	}

	public void setGdocAutoridades(GdocAutoridades gdocAutoridades) {
		this.gdocAutoridades = gdocAutoridades;
	}

	public GdocRepresentantes getGdocRepresentantes() {
		return gdocRepresentantes;
	}

	public void setGdocRepresentantes(GdocRepresentantes gdocRepresentantes) {
		this.gdocRepresentantes = gdocRepresentantes;
	}

	public GdocTiposConvenios getGdocTiposConvenios() {
		return gdocTiposConvenios;
	}

	public void setGdocTiposConvenios(GdocTiposConvenios gdocTiposConvenios) {
		this.gdocTiposConvenios = gdocTiposConvenios;
	}

	public Instituciones getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(Instituciones instituciones) {
		this.instituciones = instituciones;
	}

	public GdocConsejos getGdocConsejos() {
		return gdocConsejos;
	}

	public void setGdocConsejos(GdocConsejos gdocConsejos) {
		this.gdocConsejos = gdocConsejos;
	}

	public GdocArchivosAdjuntos getGdocArchivosAdjuntos() {
		return gdocArchivosAdjuntos;
	}

	public void setGdocArchivosAdjuntos(GdocArchivosAdjuntos gdocArchivosAdjuntos) {
		this.gdocArchivosAdjuntos = gdocArchivosAdjuntos;
	}

	public Long getIdGdocConvenio() {
		return idGdocConvenio;
	}

	public void setIdGdocConvenio(Long idGdocConvenio) {
		this.idGdocConvenio = idGdocConvenio;
	}

	public String getNroConvenio() {
		return nroConvenio;
	}

	public void setNroConvenio(String nroConvenio) {
		this.nroConvenio = nroConvenio;
	}

	public Integer getNroFolio() {
		return nroFolio;
	}

	public void setNroFolio(Integer nroFolio) {
		this.nroFolio = nroFolio;
	}

	public String getObjetoConvenio() {
		return objetoConvenio;
	}

	public void setObjetoConvenio(String objetoConvenio) {
		this.objetoConvenio = objetoConvenio;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
    
    
	

}
