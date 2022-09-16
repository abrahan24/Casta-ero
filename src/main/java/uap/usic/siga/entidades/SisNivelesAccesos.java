package uap.usic.siga.entidades;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "sis_niveles_accesos")
public class SisNivelesAccesos extends SigaUsicRevisiones{
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_acceso")   
    private Integer idNivelAcceso;
    
    
    @Column(name = "nivel_acceso")
    private String nivelAcceso;
 
    


    public Integer getIdNivelAcceso() {
        return idNivelAcceso;
    }

    public void setIdNivelAcceso(Integer idNivelAcceso) {
        this.idNivelAcceso = idNivelAcceso;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

   
}
