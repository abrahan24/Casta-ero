package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "menues")
public class Menues extends SigaUsicRevisiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_enlace", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuEnlaces mnuEnlaces;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_funcion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposFunciones mnuTiposFunciones;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long idMenu;

   

    public MnuEnlaces getMnuEnlaces() {
        return mnuEnlaces;
    }

    public void setMnuEnlaces(MnuEnlaces mnuEnlaces) {
        this.mnuEnlaces = mnuEnlaces;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public MnuTiposFunciones getMnuTiposFunciones() {
        return mnuTiposFunciones;
    }

    public void setMnuTiposFunciones(MnuTiposFunciones mnuTiposFunciones) {
        this.mnuTiposFunciones = mnuTiposFunciones;
    }

  

}
