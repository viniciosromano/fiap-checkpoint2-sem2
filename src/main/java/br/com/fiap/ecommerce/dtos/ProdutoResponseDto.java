package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProdutoResponseDto {
    private Long id;
	private String nome;
	private BigDecimal valor;


}
