package uap.usic.siga.dto;

public class PoaisIdentificacionesResponse {

	private Long idPoai;
	private Long idPnlCargos; 
	private Long idPnlPersonalAdministrativo;
	private Long idSupervisor;
	private String descripcion; 
	private String relacionInterinstitucional;
	private String relacionIntrainstitucional;
	private Integer gestion;
	private Long idPersona;
	private String nombres;
	private String paterno;
	private String materno;
	public PoaisIdentificacionesResponse(Long idSupervisor, String descripcion, Integer gestion, Long idPersona,
			String nombres, String paterno, String materno) {
		super();
		this.idSupervisor = idSupervisor;
		this.descripcion = descripcion;
		this.gestion = gestion;
		this.idPersona = idPersona;
		this.nombres = nombres;
		this.paterno = paterno;
		this.materno = materno;
	}
	public Long getIdPoai() {
		return idPoai;
	}
	public void setIdPoai(Long idPoai) {
		this.idPoai = idPoai;
	}
	public Long getIdPnlCargos() {
		return idPnlCargos;
	}
	public void setIdPnlCargos(Long idPnlCargos) {
		this.idPnlCargos = idPnlCargos;
	}
	public Long getIdPnlPersonalAdministrativo() {
		return idPnlPersonalAdministrativo;
	}
	public void setIdPnlPersonalAdministrativo(Long idPnlPersonalAdministrativo) {
		this.idPnlPersonalAdministrativo = idPnlPersonalAdministrativo;
	}
	public Long getIdSupervisor() {
		return idSupervisor;
	}
	public void setIdSupervisor(Long idSupervisor) {
		this.idSupervisor = idSupervisor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRelacionInterinstitucional() {
		return relacionInterinstitucional;
	}
	public void setRelacionInterinstitucional(String relacionInterinstitucional) {
		this.relacionInterinstitucional = relacionInterinstitucional;
	}
	public String getRelacionIntrainstitucional() {
		return relacionIntrainstitucional;
	}
	public void setRelacionIntrainstitucional(String relacionIntrainstitucional) {
		this.relacionIntrainstitucional = relacionIntrainstitucional;
	}
	public Integer getGestion() {
		return gestion;
	}
	public void setGestion(Integer gestion) {
		this.gestion = gestion;
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
	
	
		
}
