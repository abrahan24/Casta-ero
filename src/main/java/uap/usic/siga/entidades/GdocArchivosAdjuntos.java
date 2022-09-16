package uap.usic.siga.entidades;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_archivos_adjuntos")
public class GdocArchivosAdjuntos extends SigaUsicRevisiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_gdoc_archivo_adjunto")
	    private Long idGeodcArchivoAdjunto;

	    @NotNull
	    @Column(name = "nombre_archivo")
	    private String nombreArchivo;

	    @NotNull
	    @Column(name = "descripcion")
	    private String descripcion = "Anulado por Yessenia ";

	    @NotNull
	    @Column(name = "tipo_archivo")
	    private String tipoArchivo;

	    //@NotEmpty
	    @Lob
	    @Basic(fetch = FetchType.LAZY)
	    @Column(name = "contenido")
	    private byte[] contenido = new byte[100];

	    @NotNull
	    @Column(name = "ruta_archivo")
	    private String rutaArchivo;

		public Usuarios getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(Usuarios usuarios) {
			this.usuarios = usuarios;
		}

		public Long getIdGeodcArchivoAdjunto() {
			return idGeodcArchivoAdjunto;
		}

		public void setIdGeodcArchivoAdjunto(Long idGeodcArchivoAdjunto) {
			this.idGeodcArchivoAdjunto = idGeodcArchivoAdjunto;
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
