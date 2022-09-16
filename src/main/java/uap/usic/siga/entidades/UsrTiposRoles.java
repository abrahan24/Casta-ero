package uap.usic.siga.entidades;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "usr_tipos_roles")
public class UsrTiposRoles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_rol")   
    private Integer idUsrTipoRol;
  
    @Column(name = "tipo_rol")
    private String tipoRol;

    public Integer getIdUsrTipoRol() {
        return idUsrTipoRol;
    }

    public void setIdUsrTipoRol(Integer idUsrTipoRol) {
        this.idUsrTipoRol = idUsrTipoRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
  
    
}
