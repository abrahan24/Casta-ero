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
@Table(name = "gdoc_resoluciones")
public class GdocResoluciones extends SigaUsicGestiones{

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
   @Column(name = "id_gdoc_resolucion")
   private Long idGdocResolucion;

    @NotNull
    @Column(name = "nro_resolucion")
   private String  nroResolucion;   
   
   @Column(name = "nro_folio")
   private Integer nroFolio;
    
   @NotNull 
   @Column(name = "objeto_resolucion")
   private String  objetoResolucion;   

      @NotNull
   @DateTimeFormat(pattern = "yyy-MM-dd")
   @Column(name = "fec_resolucion")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date fecResolucion;

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

	public Long getIdGdocResolucion() {
		return idGdocResolucion;
	}

	public void setIdGdocResolucion(Long idGdocResolucion) {
		this.idGdocResolucion = idGdocResolucion;
	}

	public String getNroResolucion() {
		return nroResolucion;
	}

	public void setNroResolucion(String nroResolucion) {
		this.nroResolucion = nroResolucion;
	}

	public Integer getNroFolio() {
		return nroFolio;
	}

	public void setNroFolio(Integer nroFolio) {
		this.nroFolio = nroFolio;
	}

	public String getObjetoResolucion() {
		return objetoResolucion;
	}

	public void setObjetoResolucion(String objetoResolucion) {
		this.objetoResolucion = objetoResolucion;
	}

	public Date getFecResolucion() {
		return fecResolucion;
	}

	public void setFecResolucion(Date fecResolucion) {
		this.fecResolucion = fecResolucion;
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
