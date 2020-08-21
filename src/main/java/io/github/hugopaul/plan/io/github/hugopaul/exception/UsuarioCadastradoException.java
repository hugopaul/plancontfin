package io.github.hugopaul.plan.io.github.hugopaul.exception;

public class UsuarioCadastradoException  extends RuntimeException{
    public UsuarioCadastradoException(String login){
        super("Usuario ja cadastrado para login: " + login);

    }
}
