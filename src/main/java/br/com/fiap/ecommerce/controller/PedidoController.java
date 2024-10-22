package br.com.fiap.ecommerce.controller;

import br.com.fiap.ecommerce.dtos.PedidoRequestCreateDto;
import br.com.fiap.ecommerce.dtos.PedidoRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.PedidoResponseDto;
import br.com.fiap.ecommerce.mapper.PedidoMapper;
import br.com.fiap.ecommerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> list() {
        List<PedidoResponseDto> dtos = pedidoService.list()
            .stream()
            .map(pedidoMapper::toDto)
            .toList();
        
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> create(@RequestBody PedidoRequestCreateDto dto) {        
        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(
                        pedidoMapper.toDto(
                                pedidoService.save(pedidoMapper.toModel(dto))
                        )
        			);
    }

    @PutMapping("{id}")
    public ResponseEntity<PedidoResponseDto> update(
                        @PathVariable Long id, 
                        @RequestBody PedidoRequestUpdateDto dto) {
        if (! pedidoService.existsById(id)){
            throw new RuntimeException("Id inexistente");
        }                
        return ResponseEntity.ok()
        		.body(
                        pedidoMapper.toDto(
        				    pedidoService.save(pedidoMapper.toModel(id,dto))
                        )
        		);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (! pedidoService.existsById(id)){
        	throw new RuntimeException("Id inexistente");
        }

        pedidoService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoResponseDto> findById(@PathVariable Long id) {    	
    	return ResponseEntity.ok()
    			.body(
    				pedidoService
    					.findById(id)
    					.map(pedidoMapper::toDto)
    					.orElseThrow(() -> new RuntimeException("Id inexistente"))
    			);
    	  		     
    }
}
