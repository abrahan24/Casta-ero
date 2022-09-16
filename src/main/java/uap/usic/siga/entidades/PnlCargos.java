package uap.usic.siga.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "pnl_cargos")
public class PnlCargos  extends SigaUsicRevisiones {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_cargos")
    private Long idPnlCargos;
    
    @Size(max = 255)
    @NotEmpty
    private String cargo;
    
    @Column(length = 10485760)
    private String objetivoCargo;

    public Long getIdPnlCargos() {
        return idPnlCargos;
    }

    public void setIdPnlCargos(Long idPnlCargos) {
        this.idPnlCargos = idPnlCargos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

	public String getObjetivoCargo() {
		return objetivoCargo;
	}

	public void setObjetivoCargo(String objetivoCargo) {
		this.objetivoCargo = objetivoCargo;
	}

    
   
}
