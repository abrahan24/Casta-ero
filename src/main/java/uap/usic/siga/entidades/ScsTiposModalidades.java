package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_tipos_modalidades")
public class ScsTiposModalidades extends SigaUsicRevisiones{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_tipo_modalidad")
    private Long idScsTipoModalidad;
    
    @NotEmpty
    @Column(name = "scs_tipo_modalidad")
    private String scsTipoModalidad;

    public Long getIdScsTipoModalidad() {
        return idScsTipoModalidad;
    }

    public void setIdScsTipoModalidad(Long idScsTipoModalidad) {
        this.idScsTipoModalidad = idScsTipoModalidad;
    }

    public String getScsTipoModalidad() {
        return scsTipoModalidad;
    }

    public void setScsTipoModalidad(String scsTipoModalidad) {
        this.scsTipoModalidad = scsTipoModalidad;
    }
  
    
}
