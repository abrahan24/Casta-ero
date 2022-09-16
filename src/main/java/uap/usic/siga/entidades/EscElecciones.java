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
@Table(name = "esc_elecciones")
public class EscElecciones extends SigaUsicGestiones {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleccion")
    private Long idEleccion;

    @Column(name = "eleccion")
    private String eleccion;

    @Column(name = "codigo")
    private Integer codigo;

	public Long getIdEleccion() {
		return idEleccion;
	}

	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}

	public String getEleccion() {
		return eleccion;
	}

	public void setEleccion(String eleccion) {
		this.eleccion = eleccion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	

     
}
