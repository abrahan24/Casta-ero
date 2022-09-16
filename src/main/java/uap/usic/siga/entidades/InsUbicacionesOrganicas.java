package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "ins_ubicaciones_organicas")
public class InsUbicacionesOrganicas extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InsSedes sedes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_organica")
    private Long idUbicacionOrganica;

    @Column(name = "ubicacion_organica")
    private String ubicacionOrganica;

    public InsSedes getSedes() {
        return sedes;
    }

    public void setSedes(InsSedes sedes) {
        this.sedes = sedes;
    }

    public Long getIdUbicacionOrganica() {
        return idUbicacionOrganica;
    }

    public void setIdUbicacionOrganica(Long idUbicacionOrganica) {
        this.idUbicacionOrganica = idUbicacionOrganica;
    }

    public String getUbicacionOrganica() {
        return ubicacionOrganica;
    }

    public void setUbicacionOrganica(String ubicacionOrganica) {
        this.ubicacionOrganica = ubicacionOrganica;
    }

}
