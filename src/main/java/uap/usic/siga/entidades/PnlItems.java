package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fmbma
 */
@Entity
@Table(name = "pnl_items")
public class PnlItems extends SigaUsicRevisiones{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pnl_item")
    private Long idPnlItem;

    @Column(name = "item")
    private String item;

    @Column(name = "codigo_item")
    private String codigoItem;

    public Long getIdPnlItem() {
        return idPnlItem;
    }

    public void setIdPnlItem(Long idPnlItem) {
        this.idPnlItem = idPnlItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    
}
