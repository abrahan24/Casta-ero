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
@Table(name = "institucion")
public class Institucion extends SigaUsicGestiones{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long idInstitucion;

    @Size(max = 100)
    @NotEmpty(message = "Por favor ingrese institucion")
    private String institucion;

    @NotEmpty(message = "Por favor ingrese la sigla")
    private String sigla;

}
