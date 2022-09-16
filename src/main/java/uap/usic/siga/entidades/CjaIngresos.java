package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import javax.persistence.Basic;
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
 * @author fmbma
 */
@Entity
@Table(name = "cja_ingresos")
public class CjaIngresos extends SigaUsicGestiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_ingreso", referencedColumnName = "id_cja_tipo_ingreso")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposIngresos cjaTiposIngresos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cja_ingreso")
    private Long idCjaIngreso;

    @NotNull
    @Column(name = "monto")
    private Double monto;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "nro_documento")
    private Integer nroDocumento = 0;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_ingreso")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecIngreso;

    @Column(name = "porcentaje_gasto")
    private Double porcentajeGasto;

    @Column(name = "monto_maximo")
    private Double montoMaximo;

    @Column(name = "porcentaje_saldo")
    private Double porcentajeSaldo;

    
    @Column(name = "caja")
    private Integer caja = 0;


	public CjaTiposIngresos getCjaTiposIngresos() {
		return cjaTiposIngresos;
	}


	public void setCjaTiposIngresos(CjaTiposIngresos cjaTiposIngresos) {
		this.cjaTiposIngresos = cjaTiposIngresos;
	}


	public Personas getPersonas() {
		return personas;
	}


	public void setPersonas(Personas personas) {
		this.personas = personas;
	}


	public Long getIdCjaIngreso() {
		return idCjaIngreso;
	}


	public void setIdCjaIngreso(Long idCjaIngreso) {
		this.idCjaIngreso = idCjaIngreso;
	}


	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public Double getSaldo() {
		return saldo;
	}


	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public Integer getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}


	public Date getFecIngreso() {
		return fecIngreso;
	}


	public void setFecIngreso(Date fecIngreso) {
		this.fecIngreso = fecIngreso;
	}


	public Double getPorcentajeGasto() {
		return porcentajeGasto;
	}


	public void setPorcentajeGasto(Double porcentajeGasto) {
		this.porcentajeGasto = porcentajeGasto;
	}


	public Double getMontoMaximo() {
		return montoMaximo;
	}


	public void setMontoMaximo(Double montoMaximo) {
		this.montoMaximo = montoMaximo;
	}


	public Double getPorcentajeSaldo() {
		return porcentajeSaldo;
	}


	public void setPorcentajeSaldo(Double porcentajeSaldo) {
		this.porcentajeSaldo = porcentajeSaldo;
	}


	public Integer getCaja() {
		return caja;
	}


	public void setCaja(Integer caja) {
		this.caja = caja;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
    
}
