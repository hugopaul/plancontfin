package io.github.hugopaul.plan.io.github.hugopaul.model.repostory;

import io.github.hugopaul.plan.io.github.hugopaul.model.entity.Planilha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanilhaRepository extends JpaRepository<Planilha, Integer> {

    @Query(" select s from Planilha s where upper ( s.notaFiscalNumero ) like upper( :notaFiscalNumero )")
    List<Planilha> findByNotaFiscalNumero (@Param("notaFiscalNumero") String notaFiscalNumero);
}
