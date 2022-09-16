package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "mnu_tipos_enlaces")
public class MnuTiposEnlaces extends SigaUsicRevisiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_enlace")   
    private Long idMnuTipoEnlace;
    
    @Size(max = 100)
    @NotEmpty(message = "")
    @Column(name = "mnu_tipo_enlace")
    private String mnuTipoEnlace;

    @Size(max = 100)
    @NotEmpty(message = "")
    @Column(name = "mnu_imagen")
    private String mnuImagen;

    @Column(name = "mnu_tipo_enlace_orden")
    private Integer mnuTipoEnlaceOrden;

    public String getMnuImagen() {
        return mnuImagen;
    }

    public void setMnuImagen(String mnuImagen) {
        this.mnuImagen = mnuImagen;
    }

    
    public Long getIdMnuTipoEnlace() {
        return idMnuTipoEnlace;
    }

    public void setIdMnuTipoEnlace(Long idMnuTipoEnlace) {
        this.idMnuTipoEnlace = idMnuTipoEnlace;
    }

    public String getMnuTipoEnlace() {
        return mnuTipoEnlace;
    }

    public void setMnuTipoEnlace(String mnuTipoEnlace) {
        this.mnuTipoEnlace = mnuTipoEnlace;
    }

    public Integer getMnuTipoEnlaceOrden() {
        return mnuTipoEnlaceOrden;
    }

    public void setMnuTipoEnlaceOrden(Integer mnuTipoEnlaceOrden) {
        this.mnuTipoEnlaceOrden = mnuTipoEnlaceOrden;
    }

    
    
}
