package uap.usic.siga.dto;

/**
 * Rectorado - USIC
 * Fecha: 2019-08-05
 * @author Freddy Morales
 */
public class CjaDireccionesFuncionalesResponse {
  
    private Long idDireccionFuncional;
    private Long idUnidadFuncional;
    private Double totalGasto; 
    private Double montoMaximo;
    private Double saldo;
    private String direccionFuncional;
    private String sigla; 

    public CjaDireccionesFuncionalesResponse(Long idDireccionFuncional, Long idUnidadFuncional, Double totalGasto, Double montoMaximo, Double saldo, String direccionFuncional, String sigla) {
        this.idDireccionFuncional = idDireccionFuncional;
        this.idUnidadFuncional = idUnidadFuncional;
        this.totalGasto = totalGasto;
        this.montoMaximo = montoMaximo;
        this.saldo = saldo;
        this.direccionFuncional = direccionFuncional;
        this.sigla = sigla;
    }

    public Long getIdUnidadFuncional() {
        return idUnidadFuncional;
    }

    public void setIdUnidadFuncional(Long idUnidadFuncional) {
        this.idUnidadFuncional = idUnidadFuncional;
    }

   
   
    public Long getIdDireccionFuncional() {
        return idDireccionFuncional;
    }

    public void setIdDireccionFuncional(Long idDireccionFuncional) {
        this.idDireccionFuncional = idDireccionFuncional;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
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

    public String getDireccionFuncional() {
        return direccionFuncional;
    }

    public void setDireccionFuncional(String direccionFuncional) {
        this.direccionFuncional = direccionFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    
}
