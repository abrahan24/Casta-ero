package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "mnu_funciones")
public class MnuFunciones extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_funcion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposFunciones mnuTiposFunciones;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sis_administrador", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisAdministrador sisAdministrador;


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_nivel_acceso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisNivelesAccesos sisNivelesAccesos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_funcion")
    private Long idMnuFuncion;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_expiracion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecExpiracion;

    @NotNull
    @Column(name = "valor")
    private Double valor;
 
    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public MnuTiposFunciones getMnuTiposFunciones() {
        return mnuTiposFunciones;
    }

    public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

    public SisAdministrador getSisAdministrador() {
        return sisAdministrador;
    }

    public void setSisAdministrador(SisAdministrador sisAdministrador) {
        this.sisAdministrador = sisAdministrador;
    }

    public SisNivelesAccesos getSisNivelesAccesos() {
        return sisNivelesAccesos;
    }

    public void setSisNivelesAccesos(SisNivelesAccesos sisNivelesAccesos) {
        this.sisNivelesAccesos = sisNivelesAccesos;
    }

    public Long getIdMnuFuncion() {
        return idMnuFuncion;
    }

    public void setIdMnuFuncion(Long idMnuFuncion) {
        this.idMnuFuncion = idMnuFuncion;
    }

    public Date getFecExpiracion() {
        return fecExpiracion;
    }

    public void setFecExpiracion(Date fecExpiracion) {
        this.fecExpiracion = fecExpiracion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
    
}
