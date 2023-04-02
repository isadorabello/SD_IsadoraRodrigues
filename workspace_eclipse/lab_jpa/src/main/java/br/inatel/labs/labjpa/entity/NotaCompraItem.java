package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaCompraItem {
	
	private Long id;
	private NotaCompra notaCompra;
	private Produto produto;
	private BigDecimal valorCompraProduto;
	private Integer quantidade = 1;
	
	public BigDecimal getCalculoTotalItem() {
		return valorCompraProduto.multiply(BigDecimal.valueOf(quantidade));
	}
}
