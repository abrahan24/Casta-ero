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
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "gdoc_titulos")
public class GdocTitulos  extends SigaUsicRevisiones{
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado_academico", referencedColumnName = "id_grado_academico")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PrsGradosAcademicos prsGradosAcademicos; 

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_tipo_titulo", referencedColumnName = "id_gdoc_tipo_titulo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocTiposTitulos gdocTiposTitulos; 
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_titulo_grado", referencedColumnName = "id_tipo_titulo_grado")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocTiposTitulosGrados gdocTiposTitulosGrados; 
	
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
   @Column(name = "id_gdoc_titulo")
   private Long idGdocTitulo;
		 
	@Column(name = "nro_titulo")
    private Integer nroTitulo;

    
	@Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo;

	public PrsGradosAcademicos getPrsGradosAcademicos() {
		return prsGradosAcademicos;
	}

	public void setPrsGradosAcademicos(PrsGradosAcademicos prsGradosAcademicos) {
		this.prsGradosAcademicos = prsGradosAcademicos;
	}

	public GdocTiposTitulos getGdocTiposTitulos() {
		return gdocTiposTitulos;
	}

	public void setGdocTiposTitulos(GdocTiposTitulos gdocTiposTitulos) {
		this.gdocTiposTitulos = gdocTiposTitulos;
	}



	public GdocTiposTitulosGrados getGdocTiposTitulosGrados() {
		return gdocTiposTitulosGrados;
	}

	public void setGdocTiposTitulosGrados(GdocTiposTitulosGrados gdocTiposTitulosGrados) {
		this.gdocTiposTitulosGrados = gdocTiposTitulosGrados;
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

	public Long getIdGdocTitulo() {
		return idGdocTitulo;
	}

	public void setIdGdocTitulo(Long idGdocTitulo) {
		this.idGdocTitulo = idGdocTitulo;
	}

	public Integer getNroTitulo() {
		return nroTitulo;
	}

	public void setNroTitulo(Integer nroTitulo) {
		this.nroTitulo = nroTitulo;
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
