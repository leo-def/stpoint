package com.pp.stpoint.repository;

import com.pp.stpoint.entity.Expression;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public interface ExpressionRepository extends JpaRepository<Expression, Long>{
    List<Expression> findBySituationId(Long id);
    
    //Procura a expressão de determinada situação passada
    //e que tenha Values cuja linguagem esteja presente nas coleções passadas
    @Query(
    		"   SELECT e "
    		+ " FROM "
    		+ "	Expression as e "
    		+ " 	join e.values as d "//Display d
    		+ " WHERE "
    		+ " 	e.situation.id = :id AND "
    		//+ " 	d.EXPRESSION_ID = e.id AND "
    		+ " 	( "
    		+ "		d.language in :languages OR "//d.language 
    		+ " 		d.language in :desiredLanguages "
    		+ "		) "
    		+ " GROUP BY e.id")
    List<Expression> findBySituationAndLanguage(
    		@Param("id") Long id,
    		@Param("languages") List<String> languages,
    		@Param("desiredLanguages") List<String> desiredLanguages);
    
}
