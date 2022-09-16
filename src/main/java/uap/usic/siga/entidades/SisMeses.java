package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sis_meses")
public class SisMeses extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mes")
    private Long idMes;

    @NotEmpty
    @Column(name = "mes")
    private String mes;

    public Long getIdMes() {
        return idMes;
    }

    public void setIdMes(Long idMes) {
        this.idMes = idMes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

}
