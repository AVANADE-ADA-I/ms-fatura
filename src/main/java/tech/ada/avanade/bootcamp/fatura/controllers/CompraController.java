package tech.ada.avanade.bootcamp.fatura.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcamp.fatura.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.fatura.services.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {
    private CompraService compraService;
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarCompra(@RequestBody @Valid CompraRequestDTO dto) {
        return this.compraService.executeCadastrar(dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String cancelarCompra(@RequestBody @Valid CompraRequestDTO dto) {
        return this.compraService.executeCancelar(dto);
    }
}
