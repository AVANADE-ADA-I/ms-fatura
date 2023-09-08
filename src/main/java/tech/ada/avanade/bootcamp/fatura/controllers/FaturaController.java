package tech.ada.avanade.bootcamp.fatura.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcamp.fatura.dto.response.FaturaDTO;
import tech.ada.avanade.bootcamp.fatura.services.FaturaService;

@RestController
@RequestMapping("")
public class FaturaController {
    private FaturaService faturaService;
    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping("/{numeroCartao}/{mes}/{ano}")
    @ResponseStatus(HttpStatus.OK)
    public FaturaDTO buscarFatura(@PathVariable String numeroCartao, @PathVariable Integer mes, @PathVariable Integer ano) {
        return this.faturaService.executeBuscar(numeroCartao, mes, ano);
    }
}
