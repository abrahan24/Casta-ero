package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC Fecha: 2019-03-26
 *
 * @author Freddy Morales
 */
@Entity
@Table(name = "ins_unidades_funcionales")
public class InsUnidadesFuncionales extends SigaUsicRevisiones {
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "insUnidadesFuncionales")
	private List<PnlPersonalAdministrativos> administrativos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_direccion_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsDireccionesFuncionales insDireccionesFuncionales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_funcional")
    private Long idUnidadFuncional;

    @Size(max = 255)
    @NotEmpty(message = "Por favor ingrese Unidad Funcional")
    @Column(name = "unidad_funcional", length = 255, nullable = false)
    private String unidadFuncional;

    @Size(max = 255)
    @NotEmpty(message = "Por favor ingrese Unidad Funcional")
    @Column(name = "sigla", length = 255, nullable = false)
    private String sigla;

    public InsDireccionesFuncionales getInsDireccionesFuncionales() {
        return insDireccionesFuncionales;
    }

    public void setInsDireccionesFuncionales(InsDireccionesFuncionales insDireccionesFuncionales) {
        this.insDireccionesFuncionales = insDireccionesFuncionales;
    }

    public Long getIdUnidadFuncional() {
        return idUnidadFuncional;
    }

    public void setIdUnidadFuncional(Long idUnidadFuncional) {
        this.idUnidadFuncional = idUnidadFuncional;
    }

    public String getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(String unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

	public List<PnlPersonalAdministrativos> getAdministrativos() {
		return administrativos;
	}

	public void setAdministrativos(List<PnlPersonalAdministrativos> administrativos) {
		this.administrativos = administrativos;
	}
    
    
}
