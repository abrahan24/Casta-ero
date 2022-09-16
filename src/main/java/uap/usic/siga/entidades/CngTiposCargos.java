package uap.usic.siga.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_tipos_cargos")
public class CngTiposCargos extends SigaUsicRevisiones{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_cargo")
    private Long idTipoCargo;

    @Column(name = "cargo")
    private String cargo;
    
    @Column(name = "codigo")
    private String codigo;

	public Long getIdTipoCargo() {
		return idTipoCargo;
	}

	public void setIdTipoCargo(Long idTipoCargo) {
		this.idTipoCargo = idTipoCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    
}
