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

@Entity
@Table(name = "gdoc_usr_tipos_funciones")
public class GdocUsrTiposFunciones extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_funcion", referencedColumnName = "id_mnu_tipo_funcion")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposFunciones mnuTiposFunciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gdoc_consejo", referencedColumnName = "id_gdoc_consejo")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GdocConsejos gdocConsejos;  

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gdoc_tipo_funciona")
    private Long idGdocUsrTipoFuncion;

	public MnuTiposFunciones getMnuTiposFunciones() {
		return mnuTiposFunciones;
	}

	public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
		this.mnuTiposFunciones = mnuTiposFunciones;
	}

	public GdocConsejos getGdocConsejos() {
		return gdocConsejos;
	}

	public void setGdocConsejos(GdocConsejos gdocConsejos) {
		this.gdocConsejos = gdocConsejos;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdGdocUsrTipoFuncion() {
		return idGdocUsrTipoFuncion;
	}

	public void setIdGdocUsrTipoFuncion(Long idGdocUsrTipoFuncion) {
		this.idGdocUsrTipoFuncion = idGdocUsrTipoFuncion;
	}

	

		
}
