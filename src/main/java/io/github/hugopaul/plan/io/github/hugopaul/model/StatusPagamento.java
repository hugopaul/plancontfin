package io.github.hugopaul.plan.io.github.hugopaul.model;

public enum StatusPagamento {

    PENDENTE(1, "Pendente"),
    LIQUIDADO(2, "Liquidado");

    private int cod;
    private String descricao;

    StatusPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public static StatusPagamento toEnum (Integer cod) {
        if(cod == null) {
            return null;
        }
        for(StatusPagamento x: StatusPagamento.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);

    }
}
