package uap.usic.siga.dto;

import java.util.ArrayList;
import java.util.List;
import uap.usic.siga.entidades.Personas;

/**
 *
 * @author fmbma
 */
public class PersonasResponse {

    private Long idPersona;
    private String nombres;
    private String paterno;
    private String materno;
    private String ci;
    private String telefono;
    private String pais;

    private Long idPrsCiExpedido;
    private Long idPais;
    private Long idPrsTipoSexo;
    private Long idSacRazonSocial;
    private Long idGradoAcademico;

    private List<Personas> lPersonas;
    private Personas personas;

    public PersonasResponse(Long idSacRazonSocial, Long idPersona, String nombres, String paterno, String materno, String ci, Long idPrsCiExpedido, Long idPais, Long idPrsTipoSexo, Long idGradoAcademico) {
        this.idSacRazonSocial = idSacRazonSocial;
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.idPrsCiExpedido = idPrsCiExpedido;
        this.idPais = idPais;
        this.idPrsTipoSexo = idPrsTipoSexo;
        this.idGradoAcademico = idGradoAcademico;
    }

    public PersonasResponse(Long idPersona, String nombres, String paterno, String materno, String ci, Long idPrsCiExpedido, Long idPais, Long idPrsTipoSexo, Long idGradoAcademico) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.ci = ci;
        this.idPrsCiExpedido = idPrsCiExpedido;
        this.idPais = idPais;
        this.idPrsTipoSexo = idPrsTipoSexo;
        this.idGradoAcademico = idGradoAcademico;
    }

    public PersonasResponse(List<Personas> lPersonas, Personas personas) {
        this.lPersonas = lPersonas;
        this.personas = personas;
    }

    ArrayList<String> persona = new ArrayList<String>();

    public PersonasResponse(Personas personas) {
        this.idPersona = personas.getIdPersona();
        this.nombres = personas.getNombres();
        this.paterno = personas.getPaterno();
        this.materno = personas.getMaterno();
        this.ci = personas.getCi();
        this.telefono = personas.getTelefono();
        this.pais = personas.getPrsPaises().getPais();
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getIdPrsCiExpedido() {
        return idPrsCiExpedido;
    }

    public void setIdPrsCiExpedido(Long idPrsCiExpedido) {
        this.idPrsCiExpedido = idPrsCiExpedido;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Long getIdPrsTipoSexo() {
        return idPrsTipoSexo;
    }

    public void setIdPrsTipoSexo(Long idPrsTipoSexo) {
        this.idPrsTipoSexo = idPrsTipoSexo;
    }

    public Long getIdSacRazonSocial() {
        return idSacRazonSocial;
    }

    public void setIdSacRazonSocial(Long idSacRazonSocial) {
        this.idSacRazonSocial = idSacRazonSocial;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Long getIdGradoAcademico() {
        return idGradoAcademico;
    }

    public void setIdGradoAcademico(Long idGradoAcademico) {
        this.idGradoAcademico = idGradoAcademico;
    }

    
}
