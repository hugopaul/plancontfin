package io.github.hugopaul.plan.io.github.hugopaul.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.hugopaul.plan.io.github.hugopaul.model.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAtual;

    @NaturalId
    @Column(nullable = false)
    @NotEmpty(message = "{campo.notaFiscalNumero.obrigatorio}")
    private String notaFiscalNumero;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;


    @Column
    @CNPJ(message = "{campo.cnpj.invalido}")
    private String cnpj;


    @Column
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(nullable = false)
    private Double valor;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataVencimento;

    @Column
    private Integer estadoPg;

    public StatusPagamento getEstadoPg(){
        return StatusPagamento.toEnum(estadoPg);
    }
    public void setEstadoPg(StatusPagamento estadoPg){
        this.estadoPg = estadoPg.getCod();
    }

    @PrePersist
    public void prePersist(){
        setDataAtual(LocalDate.now());
    }
}
