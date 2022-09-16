package uap.usic.siga.dto;

/**
 * Rectorado - USIC
 * Fecha: 2019-07-23
 * @author Freddy Morales
 */
public class CjaTiposClasificacionesResponse {
    
    private Long idCjaTipoClasificacion;
    private Long idDireccionFuncional;
    private Long idUnidadFuncional;
    private Double total;
    private Double montoMaximo;
    private Double saldo;
    private Integer nroTipoClasificacion;
    private String tipoClasificacion;

    public CjaTiposClasificacionesResponse(Long idCjaTipoClasificacion, Long idDireccionFuncional, Long idUnidadFuncional, Double total, Double montoMaximo, Double saldo, Integer nroTipoClasificacion, String tipoClasificacion) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
        this.idDireccionFuncional = idDireccionFuncional;
        this.idUnidadFuncional = idUnidadFuncional;
        this.total = total;
        this.montoMaximo = montoMaximo;
        this.saldo = saldo;
        this.nroTipoClasificacion = nroTipoClasificacion;
        this.tipoClasificacion = tipoClasificacion;
    }

    
    public CjaTiposClasificacionesResponse(Long idCjaTipoClasificacion,Long idUnidadFuncional, Double total, Double montoMaximo, Double saldo, Integer nroTipoClasificacion, String tipoClasificacion) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
        this.idUnidadFuncional = idUnidadFuncional;
        this.total = total;
        this.montoMaximo = montoMaximo;
        this.saldo = saldo;
        this.nroTipoClasificacion = nroTipoClasificacion;
        this.tipoClasificacion = tipoClasificacion;
    }

    
    
    public CjaTiposClasificacionesResponse(Long idCjaTipoClasificacion, Double total, Double montoMaximo, Double saldo, Integer nroTipoClasificacion, String tipoClasificacion) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
        this.total = total;
        this.montoMaximo = montoMaximo;
        this.saldo = saldo;
        this.nroTipoClasificacion = nroTipoClasificacion;
        this.tipoClasificacion = tipoClasificacion;
    }
    
    public CjaTiposClasificacionesResponse(Long idCjaTipoClasificacion, Integer nroTipoClasificacion, String tipoClasificacion, Double total) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
        this.nroTipoClasificacion = nroTipoClasificacion;
        this.tipoClasificacion = tipoClasificacion;
        this.total = total;
    }


    public Long getIdDireccionFuncional() {
        return idDireccionFuncional;
    }

    public void setIdDireccionFuncional(Long idDireccionFuncional) {
        this.idDireccionFuncional = idDireccionFuncional;
    }

    public Long getIdUnidadFuncional() {
        return idUnidadFuncional;
    }

    public void setIdUnidadFuncional(Long idUnidadFuncional) {
        this.idUnidadFuncional = idUnidadFuncional;
    }

    
    
    public Long getIdCjaTipoClasificacion() {
        return idCjaTipoClasificacion;
    }

    public void setIdCjaTipoClasificacion(Long idCjaTipoClasificacion) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
    }

    public Integer getNroTipoClasificacion() {
        return nroTipoClasificacion;
    }

    public void setNroTipoClasificacion(Integer nroTipoClasificacion) {
        this.nroTipoClasificacion = nroTipoClasificacion;
    }

    public String getTipoClasificacion() {
        return tipoClasificacion;
    }

    public void setTipoClasificacion(String tipoClasificacion) {
        this.tipoClasificacion = tipoClasificacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(Double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

     
}
