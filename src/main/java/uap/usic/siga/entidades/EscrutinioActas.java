package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Rectorado - USIC
* Fecha: 2021-07-09
* @author Freddy Morales
*/
@Entity
@Table(name = "escrutinio_actas")
public class EscrutinioActas extends SigaUsicGestiones{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mesa_habilitada", referencedColumnName = "id_mesa_habilitada")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EscMesasHabilitadas escMesasHabilitadas;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escrutinio_acta")
    private Long idEscrutinioActa;

    @Column(name = "nro_acta")
    private Double nroActa;
    
    @Column(name = "votos_validos")
    private Double votosValidos;

    @Column(name = "votos_nulos")
    private Double votosNulos;
    
    @Column(name = "votos_blancos")
    private Double votosBlancos;
    
    @Column(name = "votos_emitidos")
    private Double votosEmitidos;

  public EscMesasHabilitadas getEscMesasHabilitadas() {
		return escMesasHabilitadas;
	}

	public void setEscMesasHabilitadas(EscMesasHabilitadas escMesasHabilitadas) {
		this.escMesasHabilitadas = escMesasHabilitadas;
	}

	public Long getIdEscrutinioActa() {
		return idEscrutinioActa;
	}

	public void setIdEscrutinioActa(Long idEscrutinioActa) {
		this.idEscrutinioActa = idEscrutinioActa;
	}

	public Double getNroActa() {
		return nroActa;
	}

	public void setNroActa(Double nroActa) {
		this.nroActa = nroActa;
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

	public Double getVotosEmitidos() {
		return votosEmitidos;
	}

	public void setVotosEmitidos(Double votosEmitidos) {
		this.votosEmitidos = votosEmitidos;
	}

	
	
	
	
}
