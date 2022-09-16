package uap.usic.siga.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "gdoc_gestion_consejos")
public class GdocGestionConsejos  extends SigaUsicGestiones {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_consejo", referencedColumnName = "id_gdoc_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocConsejos gdocConsejos;  

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_gdoc_gestion_consejo")
   private Long idGdocGestionConsejo;

    @NotNull
    @Column(name = "gestion_consejo")
   private String  gestionConsejo;   

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecInicio;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecFinal;

	public Long getIdGdocGestionConsejo() {
		return idGdocGestionConsejo;
	}

	public void setIdGdocGestionConsejo(Long idGdocGestionConsejo) {
		this.idGdocGestionConsejo = idGdocGestionConsejo;
	}


	public GdocConsejos getGdocConsejos() {
		return gdocConsejos;
	}

	public void setGdocConsejos(GdocConsejos gdocConsejos) {
		this.gdocConsejos = gdocConsejos;
	}

	public String getGestionConsejo() {
		return gestionConsejo;
	}

	public void setGestionConsejo(String gestionConsejo) {
		this.gestionConsejo = gestionConsejo;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFinal() {
		return fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}

    

    
}
