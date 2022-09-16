package uap.usic.siga.dto;

/**
 * Rectorado - USIC
 * Fecha: 2019-07-29
 * @author Freddy Morales
 */
public class CjaUnidadesFuncionalesResponse {
    
    private Long idUnidadFuncional;
    private Double totalGasto; 
    private Double montoMaximo;
    private Double saldo;
    private String unidadFuncional;
    private String sigla; 

    public CjaUnidadesFuncionalesResponse(Long idUnidadFuncional, Double totalGasto, Double montoMaximo, Double saldo, String unidadFuncional, String sigla) {
        this.idUnidadFuncional = idUnidadFuncional;
        this.totalGasto = totalGasto;
        this.montoMaximo = montoMaximo;
        this.saldo = saldo;
        this.unidadFuncional = unidadFuncional;
        this.sigla = sigla;
    }

    public Long getIdUnidadFuncional() {
        return idUnidadFuncional;
    }

    public void setIdUnidadFuncional(Long idUnidadFuncional) {
        this.idUnidadFuncional = idUnidadFuncional;
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

    public String getUnidadFuncional() {
        return unidadFuncional;
    }

    public void setUnidadFuncional(String unidadFuncional) {
        this.unidadFuncional = unidadFuncional;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

       
}
