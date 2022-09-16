package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_consejos")
public class GdocConsejos extends SigaUsicGestiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_consejo")
    private Long idGdocConsejo;

	 @NotNull
	 @Column(name = "consejo")
	  private String consejo;

	 @NotNull
	 @Column(name = "sigla")
	  private String sigla = "Ninguno";
	 
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getIdGdocConsejo() {
		return idGdocConsejo;
	}

	public void setIdGdocConsejo(Long idGdocConsejo) {
		this.idGdocConsejo = idGdocConsejo;
	}

	public String getConsejo() {
		return consejo;
	}

	public void setConsejo(String consejo) {
		this.consejo = consejo;
	}

	 
}
