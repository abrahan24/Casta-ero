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
 * Rectorado - USIC 
 * Fecha: 2019-12-26
 * @author Freddy Morales
 */
@Entity
@Table(name = "scs_contrataciones")
public class ScsContrataciones extends SigaUsicGestiones {
    
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_archivo_adjunto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsArchivosAdjuntos scsArchivosAdjuntos;
   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_formulario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsFormularios scsFormularios;
   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_proyecto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsProyectos scsProyectos;
   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_modalidad", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsModalidades scsModalidades;
   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cja_tipo_gasto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CjaTiposGastos cjaTiposGastos;
      
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Personas personas;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_tipo_contrato", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsTiposContratos scsTiposContratos;
   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_scs_boleta_respaldatoria", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ScsBoletasRespaldatorias scsBoletasRespaldatorias;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scs_contratacion")
    private Long idScsContratacion;
    
    @NotEmpty
    @Column(name = "scs_codigo_contratacion")
    private String scsCodigoContratacion;
   
    @NotEmpty
    @Column(name = "scs_cargo_servicio")
    private String scsCargoServicio;
   
    @NotNull
    @Column(name = "monto_mensual")
    private Double montoMensual;
    
    @NotNull
    @Column(name = "monto_total")
    private Double montoTotal;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecInicio;
    
    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_final")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecFinal;
    
    @NotNull
    @Column(name = "fojas")
    private Integer fojas;

    @NotNull
    @Column(name = "dias")
    private Integer dias;
    
    @Column(name = "modal")
    private Integer modal;
    
    @NotNull
    @Column(name = "nro_contratacion")
    private Integer nroContratacion;

    @Transient
    private MultipartFile file; 
    
    @Transient
    private String nombreArchivo; 

    public ScsArchivosAdjuntos getScsArchivosAdjuntos() {
        return scsArchivosAdjuntos;
    }

    public void setScsArchivosAdjuntos(ScsArchivosAdjuntos scsArchivosAdjuntos) {
        this.scsArchivosAdjuntos = scsArchivosAdjuntos;
    }

    public ScsFormularios getScsFormularios() {
        return scsFormularios;
    }

    public void setScsFormularios(ScsFormularios scsFormularios) {
        this.scsFormularios = scsFormularios;
    }

    public ScsProyectos getScsProyectos() {
        return scsProyectos;
    }

    public void setScsProyectos(ScsProyectos scsProyectos) {
        this.scsProyectos = scsProyectos;
    }

    public ScsModalidades getScsModalidades() {
        return scsModalidades;
    }

    public void setScsModalidades(ScsModalidades scsModalidades) {
        this.scsModalidades = scsModalidades;
    }

    public CjaTiposGastos getCjaTiposGastos() {
        return cjaTiposGastos;
    }

    public void setCjaTiposGastos(CjaTiposGastos cjaTiposGastos) {
        this.cjaTiposGastos = cjaTiposGastos;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public ScsTiposContratos getScsTiposContratos() {
        return scsTiposContratos;
    }

    public void setScsTiposContratos(ScsTiposContratos scsTiposContratos) {
        this.scsTiposContratos = scsTiposContratos;
    }

    public ScsBoletasRespaldatorias getScsBoletasRespaldatorias() {
        return scsBoletasRespaldatorias;
    }

    public void setScsBoletasRespaldatorias(ScsBoletasRespaldatorias scsBoletasRespaldatorias) {
        this.scsBoletasRespaldatorias = scsBoletasRespaldatorias;
    }

    public Long getIdScsContratacion() {
        return idScsContratacion;
    }

    public void setIdScsContratacion(Long idScsContratacion) {
        this.idScsContratacion = idScsContratacion;
    }

    public String getScsCodigoContratacion() {
        return scsCodigoContratacion;
    }

    public void setScsCodigoContratacion(String scsCodigoContratacion) {
        this.scsCodigoContratacion = scsCodigoContratacion;
    }

    public String getScsCargoServicio() {
        return scsCargoServicio;
    }

    public void setScsCargoServicio(String scsCargoServicio) {
        this.scsCargoServicio = scsCargoServicio;
    }

    public Double getMontoMensual() {
        return montoMensual;
    }

    public void setMontoMensual(Double montoMensual) {
        this.montoMensual = montoMensual;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFinal() {
        return fecFinal;
    }

    public void setFecFinal(Date fecFinal) {
        this.fecFinal = fecFinal;
    }

    public Integer getFojas() {
        return fojas;
    }

    public void setFojas(Integer fojas) {
        this.fojas = fojas;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getModal() {
        return modal;
    }

    public void setModal(Integer modal) {
        this.modal = modal;
    }

    public Integer getNroContratacion() {
        return nroContratacion;
    }

    public void setNroContratacion(Integer nroContratacion) {
        this.nroContratacion = nroContratacion;
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
