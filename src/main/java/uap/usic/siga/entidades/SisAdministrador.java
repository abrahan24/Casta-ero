package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "sis_administrador")
public class SisAdministrador extends SigaUsicGestiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sis_administrador")
    private Long idSisAdministrador;

    @NotEmpty
    @Column(name = "nombre_sis")
    private String nombreSis;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "obs")
    private String obs;

    @NotNull
    @Column(name = "valor")
    private Double valor;
    
    public Long getIdSisAdministrador() {
        return idSisAdministrador;
    }

    public void setIdSisAdministrador(Long idSisAdministrador) {
        this.idSisAdministrador = idSisAdministrador;
    }

    public String getNombreSis() {
        return nombreSis;
    }

    public void setNombreSis(String nombreSis) {
        this.nombreSis = nombreSis;
    }

   

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    

}
