package uap.usic.siga.entidades;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "poais_meses")
public class PoaisMeses  extends SigaUsicRevisiones{

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "poaisMeses")
	private List<PoaisDetallesActividades> poaisDetallesActividades;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mes")
    private Long idMes;
	
	@Column(name = "mes")
    private String mes;

	public Long getIdMes() {
		return idMes;
	}

	public void setIdMes(Long idMes) {
		this.idMes = idMes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<PoaisDetallesActividades> getPoaisDetallesActividades() {
		return poaisDetallesActividades;
	}

	public void setPoaisDetallesActividades(List<PoaisDetallesActividades> poaisDetallesActividades) {
		this.poaisDetallesActividades = poaisDetallesActividades;
	}
	
	
}
