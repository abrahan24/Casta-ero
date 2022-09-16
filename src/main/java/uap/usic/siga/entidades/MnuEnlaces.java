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
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "mnu_enlaces")
public class MnuEnlaces extends SigaUsicRevisiones{
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mnu_tipo_enlace", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MnuTiposEnlaces mnuTiposEnlaces;
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_enlace")   
    private Long idMnuEnlace;
    
    @Size(max = 100)
    @NotEmpty(message = "")
    @Column(name = "mnu_enlace")
    private String mnuEnlace;

    @Size(max = 100)
    @NotEmpty(message = "")
    @Column(name = "mnu_ruta")
    private String mnuRuta;
    
    @Size(max = 100)
    @NotEmpty(message = "")
    @Column(name = "mnu_imagen")
    private String mnuImagen;

    @Column(name = "mnu_nivel")
    private Integer mnuNivel;

    @Column(name = "mnu_enlace_orden")
    private Integer mnuEnlaceOrden;

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    
    
    public MnuTiposEnlaces getMnuTiposEnlaces() {
        return mnuTiposEnlaces;
    }

    public void setMnuTiposEnlaces(MnuTiposEnlaces mnuTiposEnlaces) {
        this.mnuTiposEnlaces = mnuTiposEnlaces;
    }

    public Long getIdMnuEnlace() {
        return idMnuEnlace;
    }

    public void setIdMnuEnlace(Long idMnuEnlace) {
        this.idMnuEnlace = idMnuEnlace;
    }

    public String getMnuEnlace() {
        return mnuEnlace;
    }

    public void setMnuEnlace(String mnuEnlace) {
        this.mnuEnlace = mnuEnlace;
    }

    public String getMnuRuta() {
        return mnuRuta;
    }

    public void setMnuRuta(String mnuRuta) {
        this.mnuRuta = mnuRuta;
    }

    public Integer getMnuNivel() {
        return mnuNivel;
    }

    public void setMnuNivel(Integer mnuNivel) {
        this.mnuNivel = mnuNivel;
    }

    public Integer getMnuEnlaceOrden() {
        return mnuEnlaceOrden;
    }

    public void setMnuEnlaceOrden(Integer mnuEnlaceOrden) {
        this.mnuEnlaceOrden = mnuEnlaceOrden;
    }

    
}
