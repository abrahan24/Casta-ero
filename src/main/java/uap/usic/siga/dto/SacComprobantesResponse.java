package uap.usic.siga.dto;

/**
 * Rectorado - USIC 
 * Fecha: 2019-10-02
 * @author Freddy Morales
 */
public class SacComprobantesResponse {
    
    private Long idSacComprobante;
    private Long idSacEstante; 
    private Long idSacCarpeta;
    private Long idMes; 
    private Integer gestion; 
    private String mes;
    private String sacCodigoCarpeta;
    private String sacNombreEstante;
    private Integer sacNumeroCarpeta; 
     private Long idSacRazonSocial;
    private Long idPersona;
    private String ci; 
    private String paterno;
    private String materno;
    private String nombres; 
    private Integer sacNroComprobante;
    private Long idSacCompNroComprobante;
    private Integer sacNroCheque;
    private Long idSacCompNroCheque;
    
    public SacComprobantesResponse(Long idSacEstante, Integer gestion, String sacNombreEstante) {
        this.idSacEstante = idSacEstante;
        this.gestion = gestion;
        this.sacNombreEstante = sacNombreEstante;
    }

    public SacComprobantesResponse(Long idMes, String mes, Integer gestion) {
        this.idMes = idMes;
        this.mes = mes;
        this.gestion = gestion;
    }

    public SacComprobantesResponse(Long idSacCarpeta, String sacCodigoCarpeta, String sacNombreEstante, Integer sacNumeroCarpeta) {
        this.idSacCarpeta = idSacCarpeta;
        this.sacCodigoCarpeta = sacCodigoCarpeta;
        this.sacNombreEstante = sacNombreEstante;
        this.sacNumeroCarpeta = sacNumeroCarpeta;
    }
    
     public SacComprobantesResponse(Long idSacComprobante, Long idSacRazonSocial, Long idPersona, String ci, String paterno, String materno, String nombres, Integer gestion, String mes) {
        this.idSacComprobante = idSacComprobante;
        this.idSacRazonSocial = idSacRazonSocial;
        this.idPersona = idPersona;
        this.ci = ci;
        this.paterno = paterno;
        this.materno = materno;
        this.nombres = nombres;
        this.gestion = gestion;
        this.mes = mes; 
    }

    public SacComprobantesResponse(Long idSacComprobante, Integer gestion, String mes, Integer sacNroComprobante, Long idSacCompNroComprobante) {
        this.idSacComprobante = idSacComprobante;
        this.gestion = gestion;
        this.mes = mes;
        this.sacNroComprobante = sacNroComprobante;
        this.idSacCompNroComprobante = idSacCompNroComprobante;
    }

    public SacComprobantesResponse(Integer gestion, Long idSacComprobante, String mes, Integer sacNroCheque, Long idSacCompNroCheque) {
        this.idSacComprobante = idSacComprobante;
        this.gestion = gestion;
        this.mes = mes;
        this.sacNroCheque = sacNroCheque;
        this.idSacCompNroCheque = idSacCompNroCheque;
    }
      

    public Long getIdSacComprobante() {
        return idSacComprobante;
    }

    public void setIdSacComprobante(Long idSacComprobante) {
        this.idSacComprobante = idSacComprobante;
    }

    public Long getIdSacEstante() {
        return idSacEstante;
    }

    public void setIdSacEstante(Long idSacEstante) {
        this.idSacEstante = idSacEstante;
    }

    public Long getIdSacCarpeta() {
        return idSacCarpeta;
    }

    public void setIdSacCarpeta(Long idSacCarpeta) {
        this.idSacCarpeta = idSacCarpeta;
    }

    public Long getIdMes() {
        return idMes;
    }

    public void setIdMes(Long idMes) {
        this.idMes = idMes;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getSacCodigoCarpeta() {
        return sacCodigoCarpeta;
    }

    public void setSacCodigoCarpeta(String sacCodigoCarpeta) {
        this.sacCodigoCarpeta = sacCodigoCarpeta;
    }

    public String getSacNombreEstante() {
        return sacNombreEstante;
    }

    public void setSacNombreEstante(String sacNombreEstante) {
        this.sacNombreEstante = sacNombreEstante;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getSacNumeroCarpeta() {
        return sacNumeroCarpeta;
    }

    public void setSacNumeroCarpeta(Integer sacNumeroCarpeta) {
        this.sacNumeroCarpeta = sacNumeroCarpeta;
    }

    public Long getIdSacRazonSocial() {
        return idSacRazonSocial;
    }

    public void setIdSacRazonSocial(Long idSacRazonSocial) {
        this.idSacRazonSocial = idSacRazonSocial;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getSacNroComprobante() {
        return sacNroComprobante;
    }

    public void setSacNroComprobante(Integer sacNroComprobante) {
        this.sacNroComprobante = sacNroComprobante;
    }

    public Long getIdSacCompNroComprobante() {
        return idSacCompNroComprobante;
    }

    public void setIdSacCompNroComprobante(Long idSacCompNroComprobante) {
        this.idSacCompNroComprobante = idSacCompNroComprobante;
    }

    public Integer getSacNroCheque() {
        return sacNroCheque;
    }

    public void setSacNroCheque(Integer sacNroCheque) {
        this.sacNroCheque = sacNroCheque;
    }

    public Long getIdSacCompNroCheque() {
        return idSacCompNroCheque;
    }

    public void setIdSacCompNroCheque(Long idSacCompNroCheque) {
        this.idSacCompNroCheque = idSacCompNroCheque;
    }
    
    
    
}
