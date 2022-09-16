package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Yessenia Velasco
 */
@Entity
@Table(name = "sac_carpetas")
public class SacCarpetas extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_tipo_carpeta", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposCarpetas sacTiposCarpetas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_estante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacEstantes sacEstantes;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mes", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SisMeses sisMeses;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_carpeta")
    private Long idSacCarpeta;

    @NotEmpty
    @Column(name = "sac_codigo_carpeta")
    private String sacCodigoCarpeta;

    @Column(name = "sac_numero_carpeta")
    private Integer sacNumeroCarpeta;

    public Long getIdSacCarpeta() {
        return idSacCarpeta;
    }

    public void setIdSacCarpeta(Long idSacCarpeta) {
        this.idSacCarpeta = idSacCarpeta;
    }

    public String getSacCodigoCarpeta() {
        return sacCodigoCarpeta;
    }

    public void setSacCodigoCarpeta(String sacCodigoCarpeta) {
        this.sacCodigoCarpeta = sacCodigoCarpeta;
    }

    public Integer getSacNumeroCarpeta() {
        return sacNumeroCarpeta;
    }

    public void setSacNumeroCarpeta(Integer sacNumeroCarpeta) {
        this.sacNumeroCarpeta = sacNumeroCarpeta;
    }

    public SacTiposCarpetas getSacTiposCarpetas() {
        return sacTiposCarpetas;
    }

    public void setSacTiposCarpetas(SacTiposCarpetas sacTiposCarpetas) {
        this.sacTiposCarpetas = sacTiposCarpetas;
    }

    public SacEstantes getSacEstantes() {
        return sacEstantes;
    }

    public void setSacEstantes(SacEstantes sacEstantes) {
        this.sacEstantes = sacEstantes;
    }

    public SisMeses getSisMeses() {
        return sisMeses;
    }

    public void setSisMeses(SisMeses sisMeses) {
        this.sisMeses = sisMeses;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

}
