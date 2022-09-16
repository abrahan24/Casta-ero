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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "poais")
public class Poais extends SigaUsicGestiones {
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_personal_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalAdministrativos pnlPersonalAdministrativos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "poais")
	private List<PoaisObjetivos> poaisObjetivos;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_poai")
    private Long idPoai;
	
	@Column(name = "descripcion")
    private String descripcion;

	public PnlPersonalAdministrativos getPnlPersonalAdministrativos() {
		return pnlPersonalAdministrativos;
	}

	public void setPnlPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativos) {
		this.pnlPersonalAdministrativos = pnlPersonalAdministrativos;
	}

	public Long getIdPoai() {
		return idPoai;
	}

	public void setIdPoai(Long idPoai) {
		this.idPoai = idPoai;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<PoaisObjetivos> getPoaisObjetivos() {
		return poaisObjetivos;
	}

	public void setPoaisObjetivos(List<PoaisObjetivos> poaisObjetivos) {
		this.poaisObjetivos = poaisObjetivos;
	}
	
 
	
}
