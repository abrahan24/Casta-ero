package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC 
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_archivos_adjuntos")
public class ScsArchivosAdjuntos extends SigaUsicRevisiones {
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_archivo_adjunto")
    private Long idScsArchivoAdjunto;

    @NotEmpty
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "descripcion")
    private String descripcion =  "Anulado por Yessenia ";;

    @NotEmpty
    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido")
    private byte[] contenido = new byte[100];

    @NotEmpty
    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Long getIdScsArchivoAdjunto() {
        return idScsArchivoAdjunto;
    }

    public void setIdScsArchivoAdjunto(Long idScsArchivoAdjunto) {
        this.idScsArchivoAdjunto = idScsArchivoAdjunto;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    
}
