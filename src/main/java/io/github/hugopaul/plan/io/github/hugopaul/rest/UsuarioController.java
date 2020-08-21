package io.github.hugopaul.plan.io.github.hugopaul.rest;

import io.github.hugopaul.plan.io.github.hugopaul.exception.UsuarioCadastradoException;
import io.github.hugopaul.plan.io.github.hugopaul.model.entity.Usuario;
import io.github.hugopaul.plan.io.github.hugopaul.model.repostory.UsuarioRepository;
import io.github.hugopaul.plan.io.github.hugopaul.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar (@RequestBody @Valid Usuario usuario){
        try{
            usuarioService.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage() );


        }
    }
}
