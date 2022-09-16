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
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sis_archivos_adjuntos")
public class SisArchivosAdjuntos extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sis_archivo_adjunto")
    private Long idSisArchivoAdjunto;

    @NotEmpty
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion = "Anulado por Yessenia ";

    @NotEmpty
    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    //@NotEmpty
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido")
    private byte[] contenido = new byte[100];

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Long getIdSisArchivoAdjunto() {
        return idSisArchivoAdjunto;
    }

    public void setIdSisArchivoAdjunto(Long idSisArchivoAdjunto) {
        this.idSisArchivoAdjunto = idSisArchivoAdjunto;
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

    public SacComprobantes getSacComprobantes() {
        return sacComprobantes;
    }

    public void setSacComprobantes(SacComprobantes sacComprobantes) {
        this.sacComprobantes = sacComprobantes;
    }

    
    
}
