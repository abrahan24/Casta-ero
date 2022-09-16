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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_prestamos_comprobantes_detalles")
public class SacPrestamosComprobantesDetalles extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacComprobantes sacComprobantes;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_prestamo_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacPrestamosComprobantes sacPrestamosComprobantes;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_estado_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacEstadosComprobantes sacEstadosComprobantes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_prestamo_comprobante_detalle")
    private Long idSacPrestamoComprobanteDetalle;

    @Column(name = "prestamo")
    private Boolean prestamo;

    public SacComprobantes getSacComprobantes() {
        return sacComprobantes;
    }

    public void setSacComprobantes(SacComprobantes sacComprobantes) {
        this.sacComprobantes = sacComprobantes;
    }

    public SacPrestamosComprobantes getSacPrestamosComprobantes() {
        return sacPrestamosComprobantes;
    }

    public void setSacPrestamosComprobantes(SacPrestamosComprobantes sacPrestamosComprobantes) {
        this.sacPrestamosComprobantes = sacPrestamosComprobantes;
    }

    public Long getIdSacPrestamoComprobanteDetalle() {
        return idSacPrestamoComprobanteDetalle;
    }

    public void setIdSacPrestamoComprobanteDetalle(Long idSacPrestamoComprobanteDetalle) {
        this.idSacPrestamoComprobanteDetalle = idSacPrestamoComprobanteDetalle;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public SacEstadosComprobantes getSacEstadosComprobantes() {
        return sacEstadosComprobantes;
    }

    public void setSacEstadosComprobantes(SacEstadosComprobantes sacEstadosComprobantes) {
        this.sacEstadosComprobantes = sacEstadosComprobantes;
    }

    public Boolean getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Boolean prestamo) {
        this.prestamo = prestamo;
    }
    
    

}
