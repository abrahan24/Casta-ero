package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Rectorado - USIC
 * Fecha: 2020-02-03
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_prs_contratados")
public class ScsPrsContratados extends  SigaUsicGestiones {
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_archivo_adjunto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsArchivosAdjuntos scsArchivosAdjuntos;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_prs_contratado")
    private Long idScsPrsContratado;
    
    @NotEmpty
    @Column(name = "scs_nit")
    private String scsNit;
    
    @NotEmpty
    @Column(name = "scs_nua_cua")
    private String scsNuaCua;
    
    @NotEmpty
    @Column(name = "scs_usuario_rupe")
    private String scsUsuarioRupe;
    
    @NotEmpty
    @Column(name = "scs_clave_rupe")
    private String scsClaveRupe;
    
    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo; 

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public ScsArchivosAdjuntos getScsArchivosAdjuntos() {
        return scsArchivosAdjuntos;
    }

    public void setScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        this.scsArchivosAdjuntos = scsArchivosAdjuntos;
    }

    public Long getIdScsPrsContratado() {
        return idScsPrsContratado;
    }

    public void setIdScsPrsContratado(Long idScsPrsContratado) {
        this.idScsPrsContratado = idScsPrsContratado;
    }

    public String getScsNit() {
        return scsNit;
    }

    public void setScsNit(String scsNit) {
        this.scsNit = scsNit;
    }

    public String getScsNuaCua() {
        return scsNuaCua;
    }

    public void setScsNuaCua(String scsNuaCua) {
        this.scsNuaCua = scsNuaCua;
    }

    public String getScsUsuarioRupe() {
        return scsUsuarioRupe;
    }

    public void setScsUsuarioRupe(String scsUsuarioRupe) {
        this.scsUsuarioRupe = scsUsuarioRupe;
    }

    public String getScsClaveRupe() {
        return scsClaveRupe;
    }

    public void setScsClaveRupe(String scsClaveRupe) {
        this.scsClaveRupe = scsClaveRupe;
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
