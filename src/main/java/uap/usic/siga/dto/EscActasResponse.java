package uap.usic.siga.dto;

/**
 * Rectorado - USIC
 * @author Freddy Morales
 */
public class EscActasResponse {

	private Long idEscrutinioActa;
	private String facultad;
	private Long idFacultad;
	private Long idEleccion;
	private String Eleccion;
	private Integer gestion;
	private Long idEstamento;
	private String estamento;
	private Long idFrente;
	private String frente; 
	private Double votosEmitidos;
	private Double votosValidos; 
	private Double votosNulos;
	private Double votosBlancos;
	private Double votosFrente; 
	private Long idMesaHabilitada; 
	private String mesa; 
	
	public EscActasResponse(String facultad, Long idFacultad, Long idFrente, String frente, Double votosEmitidos,
			Double votosValidos, Double votosNulos, Double votosBlancos, Double votosFrente, Long idMesaHabilitada,
			String mesa) {
		this.facultad = facultad;
		this.idFacultad = idFacultad;
		this.idFrente = idFrente;
		this.frente = frente;
		this.votosEmitidos = votosEmitidos;
		this.votosValidos = votosValidos;
		this.votosNulos = votosNulos;
		this.votosBlancos = votosBlancos;
		this.votosFrente = votosFrente;
		this.idMesaHabilitada = idMesaHabilitada;
		this.mesa = mesa;
	}


	public EscActasResponse(String facultad, Long idFacultad, Integer gestion, Long idFrente, String frente,
			Double votosEmitidos, Double votosValidos, Double votosNulos, Double votosBlancos, Double votosFrente) {
		this.facultad = facultad;
		this.idFacultad = idFacultad;
		this.gestion = gestion;
		this.idFrente = idFrente;
		this.frente = frente;
		this.votosEmitidos = votosEmitidos;
		this.votosValidos = votosValidos;
		this.votosNulos = votosNulos;
		this.votosBlancos = votosBlancos;
		this.votosFrente = votosFrente;
	}

	
	public EscActasResponse(String facultad, Long idFacultad, Long idEleccion, String eleccion, Integer gestion,Long idEstamento, String estamento) {
		this.facultad = facultad;
		this.idFacultad = idFacultad;
		this.idEleccion = idEleccion;
		Eleccion = eleccion;
		this.gestion = gestion;
		this.idEstamento = idEstamento;
		this.estamento = estamento;
	}


	public Long getIdEscrutinioActa() {
		return idEscrutinioActa;
	}


	public void setIdEscrutinioActa(Long idEscrutinioActa) {
		this.idEscrutinioActa = idEscrutinioActa;
	}


	public String getFacultad() {
		return facultad;
	}


	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}


	public Long getIdFacultad() {
		return idFacultad;
	}


	public void setIdFacultad(Long idFacultad) {
		this.idFacultad = idFacultad;
	}


	public Long getIdEleccion() {
		return idEleccion;
	}


	public void setIdEleccion(Long idEleccion) {
		this.idEleccion = idEleccion;
	}


	public String getEleccion() {
		return Eleccion;
	}


	public void setEleccion(String eleccion) {
		Eleccion = eleccion;
	}


	public Integer getGestion() {
		return gestion;
	}


	public void setGestion(Integer gestion) {
		this.gestion = gestion;
	}


	public Long getIdEstamento() {
		return idEstamento;
	}


	public void setIdEstamento(Long idEstamento) {
		this.idEstamento = idEstamento;
	}


	public String getEstamento() {
		return estamento;
	}


	public void setEstamento(String estamento) {
		this.estamento = estamento;
	}


	public Long getIdFrente() {
		return idFrente;
	}


	public void setIdFrente(Long idFrente) {
		this.idFrente = idFrente;
	}


	public String getFrente() {
		return frente;
	}


	public void setFrente(String frente) {
		this.frente = frente;
	}


	public Double getVotosEmitidos() {
		return votosEmitidos;
	}


	public void setVotosEmitidos(Double votosEmitidos) {
		this.votosEmitidos = votosEmitidos;
	}


	public Double getVotosValidos() {
		return votosValidos;
	}


	public void setVotosValidos(Double votosValidos) {
		this.votosValidos = votosValidos;
	}


	public Double getVotosNulos() {
		return votosNulos;
	}


	public void setVotosNulos(Double votosNulos) {
		this.votosNulos = votosNulos;
	}


	public Double getVotosBlancos() {
		return votosBlancos;
	}


	public void setVotosBlancos(Double votosBlancos) {
		this.votosBlancos = votosBlancos;
	}


	public Double getVotosFrente() {
		return votosFrente;
	}


	public void setVotosFrente(Double votosFrente) {
		this.votosFrente = votosFrente;
	}


	public Long getIdMesaHabilitada() {
		return idMesaHabilitada;
	}


	public void setIdMesaHabilitada(Long idMesaHabilitada) {
		this.idMesaHabilitada = idMesaHabilitada;
	}


	public String getMesa() {
		return mesa;
	}


	public void setMesa(String mesa) {
		this.mesa = mesa;
	}



	
	
}
