package io.github.hugopaul.plan.io.github.hugopaul.rest;

import io.github.hugopaul.plan.io.github.hugopaul.model.entity.Planilha;
import io.github.hugopaul.plan.io.github.hugopaul.model.repostory.PlanilhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/planilhas")
@CrossOrigin("http://localhost:4200")
public class PlanilhaController {

    private final PlanilhaRepository repository;
    @Autowired
    public PlanilhaController(PlanilhaRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Planilha> obterTodos(
            @RequestParam(value = "notaFiscalNumero",
                    required = false)String notaFiscalNumero){
            if(notaFiscalNumero == null ){
                return repository.findAll();
            }else {
                return repository.findByNotaFiscalNumero("%" + notaFiscalNumero + "%");
            }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planilha salvar (@RequestBody @Valid Planilha planilha){

        return  repository.save(planilha);
    }


    @GetMapping("{id}")
    public Planilha acharPorId(@PathVariable Integer id){
        return repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planilha não encontrada"));
    }
    /*@GetMapping
    public List<Planilha> pesquisa(@RequestParam(value = "notaFiscalNumero", required = false)String notaFiscalNumero){
        return repository.findByNotaFiscalNumero("%" + notaFiscalNumero + "%");
    }*/
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(
                planilha -> {
                    repository.delete(planilha);
                    return Void.TYPE;
                }
        ).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planilha não encontrada"));
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(  @PathVariable Integer id, @RequestBody @Valid Planilha planilhaAtualizado){
        repository
                .findById(id)
                .map(planilha -> {
                    planilha.setNotaFiscalNumero(planilhaAtualizado.getNotaFiscalNumero());
                    planilha.setNome(planilhaAtualizado.getNome());
                    planilha.setCnpj(planilhaAtualizado.getCnpj());
                    planilha.setCpf(planilhaAtualizado.getCpf());
                    planilha.setValor(planilhaAtualizado.getValor());
                    planilha.setDataVencimento(planilhaAtualizado.getDataVencimento());
                    planilha.setEstadoPg(planilhaAtualizado.getEstadoPg());
                    return repository.save(planilhaAtualizado);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planilha não encontrada"));
    }
}
