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
 * @author Usuario
 */
@Entity
@Table(name = "mnu_tipos_funciones")
public class MnuTiposFunciones extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mnu_tipo_funcion")
    private Long idMnuTipoFuncion;

    @NotEmpty
    @Column(name = "tipo_funcion")
    private String tipoFuncion;

     @Size(max = 20)
    @Column(name = "sigla")
    private String sigla;
     
    public Long getIdMnuTipoFuncion() {
        return idMnuTipoFuncion;
    }

    public void setIdMnuTipoFuncion(Long idMnuTipoFuncion) {
        this.idMnuTipoFuncion = idMnuTipoFuncion;
    }

    public String getTipoFuncion() {
        return tipoFuncion;
    }

    public void setTipoFuncion(String tipoFuncion) {
        this.tipoFuncion = tipoFuncion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
   
}
