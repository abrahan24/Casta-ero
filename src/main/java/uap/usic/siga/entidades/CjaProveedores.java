package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "cja_proveedores")
public class CjaProveedores extends SigaUsicRevisiones{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_proveedor")
    private Long idCjaProveedor;

    @Column(name = "proveedor")
    private String proveedor;
    
    @Size(max = 20)
    private String sigla;

	public Long getIdCjaProveedor() {
		return idCjaProveedor;
	}

	public void setIdCjaProveedor(Long idCjaProveedor) {
		this.idCjaProveedor = idCjaProveedor;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}
