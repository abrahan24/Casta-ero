package uap.usic.siga.dto;

/**
 * Rectorado - USIC
 * Fecha: 2019-07-24 
 * @author Freddy Morales
 */
public class CjaGastosClasificacionesResponse {
   
    private Long idCjaGastoClasificacion;
    private Long idCjaTipoClasificacion;
    private Integer nroGastoClasificacion;
    private String gastoClasificacion;
    private Double total;

    public CjaGastosClasificacionesResponse(Long idCjaGastoClasificacion, Long idCjaTipoClasificacion, Integer nroGastoClasificacion, String gastoClasificacion, Double total) {
        this.idCjaGastoClasificacion = idCjaGastoClasificacion;
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
        this.nroGastoClasificacion = nroGastoClasificacion;
        this.gastoClasificacion = gastoClasificacion;
        this.total = total;
    }

    public Long getIdCjaGastoClasificacion() {
        return idCjaGastoClasificacion;
    }

    public void setIdCjaGastoClasificacion(Long idCjaGastoClasificacion) {
        this.idCjaGastoClasificacion = idCjaGastoClasificacion;
    }

    public Long getIdCjaTipoClasificacion() {
        return idCjaTipoClasificacion;
    }

    public void setIdCjaTipoClasificacion(Long idCjaTipoClasificacion) {
        this.idCjaTipoClasificacion = idCjaTipoClasificacion;
    }

    public Integer getNroGastoClasificacion() {
        return nroGastoClasificacion;
    }

    public void setNroGastoClasificacion(Integer nroGastoClasificacion) {
        this.nroGastoClasificacion = nroGastoClasificacion;
    }

    public String getGastoClasificacion() {
        return gastoClasificacion;
    }

    public void setGastoClasificacion(String gastoClasificacion) {
        this.gastoClasificacion = gastoClasificacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

        
}
