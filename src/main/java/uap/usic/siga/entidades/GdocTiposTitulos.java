package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_tipos_titulos")
public class GdocTiposTitulos extends SigaUsicRevisiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gdoc_tipo_titulo")
	private Long idGdocTipoTitulo;

	@NotNull
	@Column(name = "gdoc_tipo_titulo")
	private String gdocTipoTitulo;

	@NotNull
	@Column(name = "gdoc_sigla")
	private String gdocSigla;

	

	public Long getIdGdocTipoTitulo() {
		return idGdocTipoTitulo;
	}

	public void setIdGdocTipoTitulo(Long idGdocTipoTitulo) {
		this.idGdocTipoTitulo = idGdocTipoTitulo;
	}

	public String getGdocTipoTitulo() {
		return gdocTipoTitulo;
	}

	public void setGdocTipoTitulo(String gdocTipoTitulo) {
		this.gdocTipoTitulo = gdocTipoTitulo;
	}

	public String getGdocSigla() {
		return gdocSigla;
	}

	public void setGdocSigla(String gdocSigla) {
		this.gdocSigla = gdocSigla;
	}

	

}
