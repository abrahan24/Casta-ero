package uap.usic.siga.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;
 import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_comprobantes")
public class SacComprobantes extends SigaUsicGestiones {

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_tipo_pago", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposPagos sacTiposPagos;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_tipo_comprobante", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacTiposComprobantes sacTiposComprobantes;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sac_carpeta", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SacCarpetas sacCarpetas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_comprobante")
    private Long idSacComprobante;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_elaboracion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecElaboracion;

    @NotNull
    @Column(name = "hoja_ruta")
    private Integer hojaRuta;

    @NotEmpty
    @Column(name = "glosa")
    private String glosa;

    @NotNull
    @Column(name = "sac_monto")
    private Double sacMonto;

    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo; 
    
    
    public SacTiposPagos getSacTiposPagos() {
        return sacTiposPagos;
    }

    public void setSacTiposPagos(SacTiposPagos sacTiposPagos) {
        this.sacTiposPagos = sacTiposPagos;
    }

    public SacTiposComprobantes getSacTiposComprobantes() {
        return sacTiposComprobantes;
    }

    public void setSacTiposComprobantes(SacTiposComprobantes sacTiposComprobantes) {
        this.sacTiposComprobantes = sacTiposComprobantes;
    }

    public SacCarpetas getSacCarpetas() {
        return sacCarpetas;
    }

    public void setSacCarpetas(SacCarpetas sacCarpetas) {
        this.sacCarpetas = sacCarpetas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

   

    public Long getIdSacComprobante() {
        return idSacComprobante;
    }

    public void setIdSacComprobante(Long idSacComprobante) {
        this.idSacComprobante = idSacComprobante;
    }


    public Date getFecElaboracion() {
        return fecElaboracion;
    }

    public void setFecElaboracion(Date fecElaboracion) {
        this.fecElaboracion = fecElaboracion;
    }

    public Integer getHojaRuta() {
        return hojaRuta;
    }

    public void setHojaRuta(Integer hojaRuta) {
        this.hojaRuta = hojaRuta;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public Double getSacMonto() {
        return sacMonto;
    }

    public void setSacMonto(Double sacMonto) {
        this.sacMonto = sacMonto;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

     
}
