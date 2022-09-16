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
@Table(name = "prs_grados_academicos")
public class PrsGradosAcademicos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grado_academico")
    private Long idGradoAcademico;

    @NotEmpty
    @Column(name = "grado_academico")
    private String gradoAcademico;

    @NotEmpty
    @Column(name = "descripcion")
    private String descripcion;

    public Long getIdGradoAcademico() {
        return idGradoAcademico;
    }

    public void setIdGradoAcademico(Long idGradoAcademico) {
        this.idGradoAcademico = idGradoAcademico;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
      
}
