package uap.usic.siga.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
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
 * @author Yessenia
 */
@Entity
@Table(name = "sac_devoluciones_comprobantes")
public class SacDevolucionesComprobantes extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_devolucion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecDevolucion;

    // @Temporal(javax.persistence.TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "hora_devolucion")
    private LocalTime horaDevolucion;

    @Column(name = "gestion_devolucion")
    private Integer gestionDevolucion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_devolucion_comprobante")
    private Long idSacDevolucionComprobante;

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Date getFecDevolucion() {
        return fecDevolucion;
    }

    public void setFecDevolucion(Date fecDevolucion) {
        this.fecDevolucion = fecDevolucion;
    }

    public LocalTime getHoraDevolucion() {
        return horaDevolucion;
    }

    public void setHoraDevolucion(LocalTime horaDevolucion) {
        this.horaDevolucion = horaDevolucion;
    }

    public Integer getGestionDevolucion() {
        return gestionDevolucion;
    }

    public void setGestionDevolucion(Integer gestionDevolucion) {
        this.gestionDevolucion = gestionDevolucion;
    }

    public Long getIdSacDevolucionComprobante() {
        return idSacDevolucionComprobante;
    }

    public void setIdSacDevolucionComprobante(Long idSacDevolucionComprobante) {
        this.idSacDevolucionComprobante = idSacDevolucionComprobante;
    }

}
