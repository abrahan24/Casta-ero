package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rectorado - USIC
 * Fecha: 2019-06-01
 * @author Freddy Morales
 */
@Entity
@Table(name = "prs_ci_expedidos")
public class PrsCiExpedidos extends  SigaUsicRevisiones  {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prs_ci_expedido")
    private Long idPrsCiExpedido;

    @Size(max = 50)
    @NotEmpty(message = "")
    @Column(name = "prs_ci_expedido")
    private String prsCiExpedido;
    
    @Size(max = 50)
    @NotEmpty(message = "")
    @Column(name = "prs_ci_expedido_sigla")
    private String prsCiExpedidoSigla;

    public Long getIdPrsCiExpedido() {
        return idPrsCiExpedido;
    }

    public void setIdPrsCiExpedido(Long idPrsCiExpedido) {
        this.idPrsCiExpedido = idPrsCiExpedido;
    }

    public String getPrsCiExpedido() {
        return prsCiExpedido;
    }

    public void setPrsCiExpedido(String prsCiExpedido) {
        this.prsCiExpedido = prsCiExpedido;
    }

    public String getPrsCiExpedidoSigla() {
        return prsCiExpedidoSigla;
    }

    public void setPrsCiExpedidoSigla(String prsCiExpedidoSigla) {
        this.prsCiExpedidoSigla = prsCiExpedidoSigla;
    }

    
}
