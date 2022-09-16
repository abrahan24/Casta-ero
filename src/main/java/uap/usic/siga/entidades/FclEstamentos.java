package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Rectorado - USIC
* Fecha: 2021-07-09
* @author Freddy Morales
*/
@Entity
@Table(name = "fcl_estamentos")
public class FclEstamentos extends SigaUsicRevisiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estamento")
    private Long idEstamento;

    @Column(name = "estamento")
    private String estamento;

    @Column(name = "codigo")
    private String codigo;

	public Long getIdEstamento() {
		return idEstamento;
	}

	public void setIdEstamento(Long idEstamento) {
		this.idEstamento = idEstamento;
	}

	public String getEstamento() {
		return estamento;
	}

	public void setEstamento(String estamento) {
		this.estamento = estamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
    
}
