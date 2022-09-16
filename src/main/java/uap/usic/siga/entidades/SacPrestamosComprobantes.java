package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
@Table(name = "sac_prestamos_comprobantes")
public class SacPrestamosComprobantes extends SigaUsicRevisiones {

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

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_prestamo_comprobante")
    private Long idSacPrestamoComprobante;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_prestamo")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecPrestamo;

    // @Temporal(javax.persistence.TemporalType.TIME)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "hora_prestamo")
    private LocalTime horaPrestamo;

    @Column(name = "gestion_prestamo")
    private Integer gestionPrestamo;

    @Column(name = "prestamo")
    private Boolean prestamo;

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

    public Long getIdSacPrestamoComprobante() {
        return idSacPrestamoComprobante;
    }

    public void setIdSacPrestamoComprobante(Long idSacPrestamoComprobante) {
        this.idSacPrestamoComprobante = idSacPrestamoComprobante;
    }

    public Date getFecPrestamo() {
        return fecPrestamo;
    }

    public void setFecPrestamo(Date fecPrestamo) {
        this.fecPrestamo = fecPrestamo;
    }

    public Integer getGestionPrestamo() {
        return gestionPrestamo;
    }

    public void setGestionPrestamo(Integer gestionPrestamo) {
        this.gestionPrestamo = gestionPrestamo;
    }

    public Boolean getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Boolean prestamo) {
        this.prestamo = prestamo;
    }

    public LocalTime getHoraPrestamo() {
        return horaPrestamo;
    }

    public void setHoraPrestamo(LocalTime horaPrestamo) {
        this.horaPrestamo = horaPrestamo;
    }

    

   

   
   
 
  

}
