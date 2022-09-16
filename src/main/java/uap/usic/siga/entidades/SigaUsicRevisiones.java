package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

 
/**
 *
 * @author fmbma
 */
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class SigaUsicRevisiones implements Serializable {
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_registro")
    @CreatedDate
    private Date registro  = new Timestamp(System.currentTimeMillis());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "_modificacion")
    @LastModifiedDate
    private Date modificacion    = new Timestamp(System.currentTimeMillis());
    
    @Size(max = 2)
    @Column(name = "id_estado")
    private String idEstado = "A";

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

   
    
}
