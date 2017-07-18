package com.pp.stpoint.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Languages {
    EN("EN"),
    EN_US("EN-US"),
    PT("PT"),
    PT_BR("PT-BR");
    
    public String language;
    
    private Languages(String language){
        this.language = language;
    }
    
    public static Languages get(String language){
        for(Languages clanguage : Languages.values()){
            if(clanguage.language.equals(language)){return clanguage;}  
        }
        return null;
    }
    
    public static List<String> toStringList(Languages[] langs){
        return Arrays.asList(Languages.toString(langs));
    }
    
    public String toStirng(){
        return language;
    }
    
    public static String toString(Languages languages){
        return languages.toString();
    }
    
    public static String[] toString(Languages[] languages){
        String[] returnValue = new String[languages.length];
        for(int i = 0 ; i< returnValue.length; i++){
            returnValue[i] = languages[i].language;
        }
        return returnValue;
    }
    
    //pega a sub linguagens da principal
    public static Languages[] languagesFor(String main){
        List<Languages> options = new ArrayList<>();
        for(Languages current_language : Languages.values()){
            String main_language = current_language.language.split("-")[0];
            if(main.equalsIgnoreCase(main_language)){
                options.add(current_language);
            }
        }
        return options.toArray(new Languages[]{});
    }
    
    //pega linguagens do mesmo tipo
    public static Languages[] relatedLanguages(String language){
        return languagesFor(language.split("-")[0]);
    }
    
    //pega apenas as linguagens principais 
    public static Languages[] mainLanguages(){
        List<Languages> options = new ArrayList<>();
        for(Languages current_language : Languages.values()){
            if(current_language.language.split("-").length == 1){
                options.add(current_language);
            }
        }
        return options.toArray(new Languages[]{});
    }
    
    @Override
    public String toString(){
    	return language;
    }
}
