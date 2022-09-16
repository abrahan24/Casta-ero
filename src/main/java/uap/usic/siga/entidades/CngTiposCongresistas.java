package uap.usic.siga.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_tipos_congresistas")
public class CngTiposCongresistas extends SigaUsicGestiones{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_congresista")
    private Long idTipoCongresista;

    @NotNull
    @Column(name = "tipo_conresista")
    private String tipoCongresista;
    
    public Long getIdTipoCongresista() {
		return idTipoCongresista;
	}

	public void setIdTipoCongresista(Long idTipoCongresista) {
		this.idTipoCongresista = idTipoCongresista;
	}

	public String getTipoCongresista() {
		return tipoCongresista;
	}

	public void setTipoCongresista(String tipoCongresista) {
		this.tipoCongresista = tipoCongresista;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "codigo")
    private String codigo;


}
