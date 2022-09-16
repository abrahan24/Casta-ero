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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Rectorado - USIC Fecha: 2019-04-25
 *
 * @author Freddy Morales
 */
@Entity
@Table(name = "cja_gastos_ejecutados")
public class CjaGastosEjecutados extends SigaUsicGestiones {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_ingreso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaIngresos cjaIngresos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_proveedor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaProveedores cjaProveedores;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_personal_administrativo", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlPersonalAdministrativos pnlPersonalAdministrativos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_gasto_ejecutado")
    private Long idCjaGastoEjecutado;

    @Column(name = "nro_documento")
    private Integer nroDocumento;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fec_gasto")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecGasto;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nro_factura")
    private String nroFactura;

    
    @Column(name = "monto")
    private Double monto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull
    @Column(name = "totalG")
    private Double totalG;

    @NotNull
    @Column(name = "certificacion_almacen")
    private Integer certificacionAlmacen;
    
    @Column(name = "retencion")
    private Double retencion;
    
    @Column(name = "retencion_check")
    private Integer retencionCheck;

	public CjaTiposGastos getCjaTiposGastos() {
		return cjaTiposGastos;
	}

	public void setCjaTiposGastos(CjaTiposGastos cjaTiposGastos) {
		this.cjaTiposGastos = cjaTiposGastos;
	}

	public CjaIngresos getCjaIngresos() {
		return cjaIngresos;
	}

	public void setCjaIngresos(CjaIngresos cjaIngresos) {
		this.cjaIngresos = cjaIngresos;
	}

	public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public CjaProveedores getCjaProveedores() {
		return cjaProveedores;
	}

	public void setCjaProveedores(CjaProveedores cjaProveedores) {
		this.cjaProveedores = cjaProveedores;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public PnlPersonalAdministrativos getPnlPersonalAdministrativos() {
		return pnlPersonalAdministrativos;
	}

	public void setPnlPersonalAdministrativos(PnlPersonalAdministrativos pnlPersonalAdministrativos) {
		this.pnlPersonalAdministrativos = pnlPersonalAdministrativos;
	}

	public Long getIdCjaGastoEjecutado() {
		return idCjaGastoEjecutado;
	}

	public void setIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
		this.idCjaGastoEjecutado = idCjaGastoEjecutado;
	}

	public Integer getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Date getFecGasto() {
		return fecGasto;
	}

	public void setFecGasto(Date fecGasto) {
		this.fecGasto = fecGasto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotalG() {
		return totalG;
	}

	public void setTotalG(Double totalG) {
		this.totalG = totalG;
	}

	public Integer getCertificacionAlmacen() {
		return certificacionAlmacen;
	}

	public void setCertificacionAlmacen(Integer certificacionAlmacen) {
		this.certificacionAlmacen = certificacionAlmacen;
	}

	public Double getRetencion() {
		return retencion;
	}

	public void setRetencion(Double retencion) {
		this.retencion = retencion;
	}

	public Integer getRetencionCheck() {
		return retencionCheck;
	}

	public void setRetencionCheck(Integer retencionCheck) {
		this.retencionCheck = retencionCheck;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
    
    
}
