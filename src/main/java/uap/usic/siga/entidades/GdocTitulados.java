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
@Table(name = "gdoc_titulados")
public class GdocTitulados extends SigaUsicGestiones{

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas; 

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_titulo", referencedColumnName = "id_gdoc_titulo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocTitulos gdocTitulos; 
	
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
   @Column(name = "id_gdoc_titulado")
   private Long idGdocTitulado;
	
	@Column(name = "valor_deposito")
    private Double valorDeposito;
    
	    @NotNull
	    @DateTimeFormat(pattern = "yyy-MM-dd")
	    @Column(name = "fec_expedido")
	    @Temporal(javax.persistence.TemporalType.DATE)
	    private Date fecExpedido;

	public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public GdocTitulos getGdocTitulos() {
		return gdocTitulos;
	}

	public void setGdocTitulos(GdocTitulos gdocTitulos) {
		this.gdocTitulos = gdocTitulos;
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

	public Long getIdGdocTitulado() {
		return idGdocTitulado;
	}

	public void setIdGdocTitulado(Long idGdocTitulado) {
		this.idGdocTitulado = idGdocTitulado;
	}

	public Double getValorDeposito() {
		return valorDeposito;
	}

	public void setValorDeposito(Double valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

	public Date getFecExpedido() {
		return fecExpedido;
	}

	public void setFecExpedido(Date fecExpedido) {
		this.fecExpedido = fecExpedido;
	}
	@Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo;

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
