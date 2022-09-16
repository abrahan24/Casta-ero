package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gdoc_tipos_titulos_grados")
public class GdocTiposTitulosGrados extends SigaUsicRevisiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_titulo_grado")
	private Long idTipoTituloGrado;

	@Column(name = "tipo_titulo_grado")
	private String tipoTituloGrado;

	

	public Long getIdTipoTituloGrado() {
		return idTipoTituloGrado;
	}

	public void setIdTipoTituloGrado(Long idTipoTituloGrado) {
		this.idTipoTituloGrado = idTipoTituloGrado;
	}

	public String getTipoTituloGrado() {
		return tipoTituloGrado;
	}

	public void setTipoTituloGrado(String tipoTituloGrado) {
		this.tipoTituloGrado = tipoTituloGrado;
	}

	
	

}
