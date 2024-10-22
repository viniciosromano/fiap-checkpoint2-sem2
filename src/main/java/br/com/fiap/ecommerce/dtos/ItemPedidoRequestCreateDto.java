package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.com.fiap.ecommerce.model.ItemPedido;

@Setter
@Getter
public class ItemPedidoRequestCreateDto {
	private Long idPedido;
    private Long idProduto;
    private BigDecimal quantidade;
    private BigDecimal valorTotal;

}
