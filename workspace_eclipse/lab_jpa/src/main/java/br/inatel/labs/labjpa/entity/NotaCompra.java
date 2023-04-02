package br.inatel.labs.labjpa.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class NotaCompra {
	
	private Long id;
	private List<NotaCompraItem> listaNotaCompraItem;
	private Fornecedor fornecedor;
	private LocalDate dataEmissao;
	
	// PROGRAMAÇÃO FUNCIONAL
	public BigDecimal getCalculoTotalNota() {
		BigDecimal total = this.listaNotaCompraItem.stream()
				//.map(NotaCompraItem::getCalculoTotalItem)
			      .map( i -> i.getCalculoTotalItem())
			      .reduce( BigDecimal.ZERO, BigDecimal::add );
		return total;
	}
	
}
