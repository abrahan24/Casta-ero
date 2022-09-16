package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC
 * Fecha: 2019-07-30
 * @author Freddy Morales
 */
@Entity
@Table(name = "ins_direcciones_funcionales")
public class InsDireccionesFuncionales extends  SigaUsicRevisiones {
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes insSedes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion_funcional")
    private Long idDireccionFuncional;

    @Size(max = 255)
    @NotEmpty(message = "Por favor ingrese Direccion Funcional")
    @Column(name = "direccion_funcional", length = 60, nullable = false)
    private String direccionFuncional;

    @Size(max = 10)
    @NotEmpty(message = "Por favor ingrese sigla")
    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    public InsSedes getInsSedes() {
        return insSedes;
    }

    public void setInsSedes(InsSedes insSedes) {
        this.insSedes = insSedes;
    }

    public Long getIdDireccionFuncional() {
        return idDireccionFuncional;
    }

    public void setIdDireccionFuncional(Long idDireccionFuncional) {
        this.idDireccionFuncional = idDireccionFuncional;
    }

    public String getDireccionFuncional() {
        return direccionFuncional;
    }

    public void setDireccionFuncional(String direccionFuncional) {
        this.direccionFuncional = direccionFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
}
