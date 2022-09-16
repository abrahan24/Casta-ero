package uap.usic.siga.dto;

import java.util.Date;
import uap.usic.siga.entidades.CjaGastosEjecutados;

/**
 * Rectorado - USIC Fecha 2019-05-08
 *
 * @author Yessenia Velasco
 */
public class CjaGastosEjecutadosResponse {

    private Long idCjaGastoEjecutado;
    private String nroFactura;
    private String tipoGasto;
    private Integer nroDocumento;
    private Date fecGasto;
    private String descripcion;
    private Double monto;
    private Date fecIngreso;
    private Long idCjaProveedores;
    private String proveedor;

    public CjaGastosEjecutadosResponse(CjaGastosEjecutados cjaGastosEjecutados) {
        this.idCjaGastoEjecutado = cjaGastosEjecutados.getIdCjaGastoEjecutado();
        this.nroFactura = cjaGastosEjecutados.getNroFactura();
        this.tipoGasto = cjaGastosEjecutados.getCjaTiposGastos().getTipoGasto();
        this.fecGasto =  cjaGastosEjecutados.getFecGasto();
        this.descripcion = cjaGastosEjecutados.getDescripcion();
        this.monto = cjaGastosEjecutados.getMonto();
        this.fecIngreso = cjaGastosEjecutados.getCjaIngresos().getFecIngreso();
        this.idCjaProveedores = cjaGastosEjecutados.getCjaProveedores().getIdCjaProveedor();
        this.proveedor = cjaGastosEjecutados.getCjaProveedores().getProveedor();

    }

    public Long getIdCjaGastoEjecutado() {
        return idCjaGastoEjecutado;
    }

    public void setIdCjaGastoEjecutado(Long idCjaGastoEjecutado) {
        this.idCjaGastoEjecutado = idCjaGastoEjecutado;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

   
    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecIngreso() {
        return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
        this.fecIngreso = fecIngreso;
    }

    public Long getIdCjaProveedores() {
        return idCjaProveedores;
    }

    public void setIdCjaProveedores(Long idCjaProveedores) {
        this.idCjaProveedores = idCjaProveedores;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    

}
