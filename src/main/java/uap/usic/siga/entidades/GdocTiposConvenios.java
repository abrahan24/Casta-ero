package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_tipos_convenios")
public class GdocTiposConvenios  extends SigaUsicRevisiones{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_tipo_convenio")
    private Long idGdocTipoConvenio;

    @NotNull
    @Column(name = "tipo_convenio")
    private String  tipoConvenio;

    @NotNull
    @Column(name = "sigla_convenio")
    private String  siglaConvenio;

    public Long getIdGdocTipoConvenio() {
		return idGdocTipoConvenio;
	}

	public void setIdGdocTipoConvenio(Long idGdocTipoConvenio) {
		this.idGdocTipoConvenio = idGdocTipoConvenio;
	}

	public String getTipoConvenio() {
		return tipoConvenio;
	}

	public void setTipoConvenio(String tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}

	public String getSiglaConvenio() {
		return siglaConvenio;
	}

	public void setSiglaConvenio(String siglaConvenio) {
		this.siglaConvenio = siglaConvenio;
	}   

     
}
