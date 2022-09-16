package uap.usic.siga.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Rectorado - USIC
 * Fecha: 2019-09-27
 * @author Freddy Morales
 */

@Entity
@Table(name = "cja_pedidos_certificados")
public class CjaPedidosCertificados extends SigaUsicGestiones {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_pedido_certificado")
    private Long idCjaPedidoCertificado;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_certificado")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecCertificado;

	public Long getIdCjaPedidoCertificado() {
		return idCjaPedidoCertificado;
	}

	public void setIdCjaPedidoCertificado(Long idCjaPedidoCertificado) {
		this.idCjaPedidoCertificado = idCjaPedidoCertificado;
	}

	public Date getFecCertificado() {
		return fecCertificado;
	}

	public void setFecCertificado(Date fecCertificado) {
		this.fecCertificado = fecCertificado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
