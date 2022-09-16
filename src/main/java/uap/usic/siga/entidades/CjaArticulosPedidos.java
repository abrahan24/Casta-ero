/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Rectorado - USIC
 * Fecha: 2019-09-27
 * @author Freddy Morales
 */
@Entity
@Table(name = "cja_articulos_pedidos")
public class CjaArticulosPedidos extends SigaUsicRevisiones {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_articulo_pedido")
    private Long idCjaArtituloPedido;

    @Column(name = "articulo")
    private String articulo;

    @Column(name = "cantidad")
    private Integer cantidad;

	public Long getIdCjaArtituloPedido() {
		return idCjaArtituloPedido;
	}

	public void setIdCjaArtituloPedido(Long idCjaArtituloPedido) {
		this.idCjaArtituloPedido = idCjaArtituloPedido;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
    
}
