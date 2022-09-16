package uap.usic.siga.entidades;

import javax.persistence.Basic;
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
*
* @author 
*/

@Entity
@Table(name = "poais_supervisores")
public class PoaisSupervisores extends SigaUsicGestiones{
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pnl_cargos", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PnlCargos pnlCargos;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_poai", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Poais poais;
	*/
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_supervisor")
    private Long idSupervisor;

    @Column(name = "descripcion")
    private String descripcion;

	public Personas getPersonas() {
		return personas;
	}

	public void setPersonas(Personas personas) {
		this.personas = personas;
	}

	public PnlCargos getPnlCargos() {
		return pnlCargos;
	}

	public void setPnlCargos(PnlCargos pnlCargos) {
		this.pnlCargos = pnlCargos;
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

        
}
