package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Yessenia
 */
@Entity
@Table(name = "sac_tipos_pagos")
public class SacTiposPagos extends SigaUsicRevisiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sac_tipo_pago")
    private Long idSacTipoPago;

    @NotEmpty
    @Column(name = "sac_tipo_pago")
    private String sacTipoPago;

    public Long getIdSacTipoPago() {
        return idSacTipoPago;
    }

    public void setIdSacTipoPago(Long idSacTipoPago) {
        this.idSacTipoPago = idSacTipoPago;
    }

    public String getSacTipoPago() {
        return sacTipoPago;
    }

    public void setSacTipoPago(String sacTipoPago) {
        this.sacTipoPago = sacTipoPago;
    }

}
