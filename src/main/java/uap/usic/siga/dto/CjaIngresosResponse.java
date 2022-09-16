package uap.usic.siga.dto;

import java.util.Date;
import uap.usic.siga.entidades.CjaIngresos;
import uap.usic.siga.entidades.SigaUsicGestiones;

/**
 * Rectorado - USIC
 * Fecha: 2019-05-07
 * @author Freddy Morales
 */
public class CjaIngresosResponse extends SigaUsicGestiones {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCjaIngreso;
    private Double monto;
    private Double saldo;
    private Date fecIngreso;
    private String tipoIngreso;
    private String paterno; 
    private String materno; 
    private String nombres;
    private String ci; 
    
    public CjaIngresosResponse(CjaIngresos cjaIngresos){
        this.idCjaIngreso = cjaIngresos.getIdCjaIngreso();
        this.monto = cjaIngresos.getMonto();
        this.saldo = cjaIngresos.getSaldo();
        this.fecIngreso = cjaIngresos.getFecIngreso();
        this.tipoIngreso = cjaIngresos.getCjaTiposIngresos().getTipoIngreso();
        this.paterno = cjaIngresos.getPersonas().getPaterno(); 
        this.materno = cjaIngresos.getPersonas().getMaterno();
        this.nombres = cjaIngresos.getPersonas().getNombres();
        this.ci = cjaIngresos.getPersonas().getCi();
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

    public Date getFecIngreso() {
        return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
        this.fecIngreso = fecIngreso;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
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

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
    
    
}
