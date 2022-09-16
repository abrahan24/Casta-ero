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
@Table(name = "cja_tipos_ingresos")
public class CjaTiposIngresos extends SigaUsicRevisiones {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_tipo_ingreso")
    private Long idCjaTipoIngreso;

    @Size(max = 100)
    @Column(name = "tipo_ingreso")
    private String tipoIngreso;

    public Long getIdCjaTipoIngreso() {
        return idCjaTipoIngreso;
    }

    public void setIdCjaTipoIngreso(Long idCjaTipoIngreso) {
        this.idCjaTipoIngreso = idCjaTipoIngreso;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

}
