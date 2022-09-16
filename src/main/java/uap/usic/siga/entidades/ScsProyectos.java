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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_proyectos")
public class ScsProyectos extends SigaUsicGestiones {
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_funcional", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsUnidadesFuncionales insUnidadesFuncionales;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_proyecto")
    private Long idScsProyecto;

    @NotEmpty
    @Column(name = "scs_proyecto")
    private String scsProyecto;

    @NotEmpty
    @Column(name = "scs_codigo_proyecto")
    private String scsCodigoProyecto;
    
    @NotNull
    @Column(name = "scs_nro_proyecto")
    private Integer scsNroProyecto;

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public InsUnidadesFuncionales getInsUnidadesFuncionales() {
        return insUnidadesFuncionales;
    }

    public void setInsUnidadesFuncionales(InsUnidadesFuncionales insUnidadesFuncionales) {
        this.insUnidadesFuncionales = insUnidadesFuncionales;
    }

    public Long getIdScsProyecto() {
        return idScsProyecto;
    }

    public void setIdScsProyecto(Long idScsProyecto) {
        this.idScsProyecto = idScsProyecto;
    }

    public String getScsProyecto() {
        return scsProyecto;
    }

    public void setScsProyecto(String scsProyecto) {
        this.scsProyecto = scsProyecto;
    }

    public String getScsCodigoProyecto() {
        return scsCodigoProyecto;
    }

    public void setScsCodigoProyecto(String scsCodigoProyecto) {
        this.scsCodigoProyecto = scsCodigoProyecto;
    }

    public Integer getScsNroProyecto() {
        return scsNroProyecto;
    }

    public void setScsNroProyecto(Integer scsNroProyecto) {
        this.scsNroProyecto = scsNroProyecto;
    }
    
    
}
