package uap.usic.siga.entidades;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
*
* @author Freddy Morales
*/
@Entity
@Table(name = "cng_mesa_directiva")
public class CngMesaDirectiva extends SigaUsicRevisiones {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cng_congresista", referencedColumnName = "id_cng_congresista")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngCongresistas cngCongresistas;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_cargo", referencedColumnName = "id_tipo_cargo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CngTiposCargos cngTiposCargos;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mesa_directiva")
    private Long idMesaDirectiva;

	@Column(name = "codigo")
    private String codigo;
	
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

		public CngCongresistas getCngCongresistas() {
			return cngCongresistas;
		}

		public void setCngCongresistas(CngCongresistas cngCongresistas) {
			this.cngCongresistas = cngCongresistas;
		}

		public CngTiposCargos getCngTiposCargos() {
			return cngTiposCargos;
		}

		public void setCngTiposCargos(CngTiposCargos cngTiposCargos) {
			this.cngTiposCargos = cngTiposCargos;
		}

		public Long getIdMesaDirectiva() {
			return idMesaDirectiva;
		}

		public void setIdMesaDirectiva(Long idMesaDirectiva) {
			this.idMesaDirectiva = idMesaDirectiva;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
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
