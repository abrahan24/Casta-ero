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
@Table(name = "cja_pedidos_gastos")
public class CjaPedidosGastos extends SigaUsicGestiones {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_pedido_gasto")
    private Long idCjaPedidoGasto;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Column(name = "fec_pedido")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecPedido;

	public Long getIdCjaPedidoGasto() {
		return idCjaPedidoGasto;
	}

	public void setIdCjaPedidoGasto(Long idCjaPedidoGasto) {
		this.idCjaPedidoGasto = idCjaPedidoGasto;
	}

	public Date getFecPedido() {
		return fecPedido;
	}

	public void setFecPedido(Date fecPedido) {
		this.fecPedido = fecPedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
 
}
