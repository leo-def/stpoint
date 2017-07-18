package com.pp.stpoint.dto;

import com.pp.stpoint.entity.Expression;
import com.pp.stpoint.entity.Value;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public class ExpressionTranslateDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> values = new ArrayList<>();
    private List<String> valueLanguages = new ArrayList<>();
    private List<String> translates = new ArrayList<>();
    private List<String> translateLanguages = new ArrayList<>();

    public ExpressionTranslateDTO(){}
    public ExpressionTranslateDTO(Expression expression, List<String> valueLanguages, List<String> translateLanguages)
    {
        this.valueLanguages = valueLanguages;
        this.translateLanguages = translateLanguages;
        setExpression(expression);
    }
    public ExpressionTranslateDTO(Expression expression){setExpression(expression);}
    public ExpressionTranslateDTO(List<String> valueLanguages, List<String> translateLanguages){
        this.valueLanguages = valueLanguages;
        this.translateLanguages = translateLanguages;
    }
    
    public static ExpressionTranslateDTO create(Expression expression, List<String> valueLanguages, List<String> translateLanguages){
        return new ExpressionTranslateDTO(expression, valueLanguages, translateLanguages);
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the value
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * @param value the value to set
     */
    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * @return the translate
     */
    public List<String>  getTranslates() {
        return translates;
    }

    /**
     * @param translate the translate to set
     */
    public void setTranslates(List<String> translates) {
        this.translates = translates;
    }

    /**
     * @return the valueLanguage
     */
    public List<String> getValueLanguages() {
        return valueLanguages;
    }

    /**
     * @param valueLanguage the valueLanguage to set
     */
    public void setValueLanguages(List<String> valueLanguages) {
        this.valueLanguages = valueLanguages;
    }

    /**
     * @return the translateLanguage
     */
    public List<String> getTranslateLanguages() {
        return translateLanguages;
    }

    /**
     * @param translateLanguage the translateLanguage to set
     */
    public void setTranslateLanguages(List<String> translateLanguages) {
        this.translateLanguages = translateLanguages;
    }
    public void setExpression(Expression expression){
    	setId(expression.getId());
    	setTitle(expression.getTitle());
    	setDescription(expression.getDescription());
        for(Value value : expression.getValues()){
            if(this.valueLanguages.contains(value.getLanguage())){
                this.values.add(value.getValue());
            }
            if(this.translateLanguages.contains(value.getLanguage())){
                this.translates.add(value.getValue());
            }
        }
    }
}
