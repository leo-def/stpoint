package com.pp.stpoint.dto;

/**
 *
 * @author Leonardo de Freitas Oliveira
 */
public class GenericDTO {
    protected String title;
    protected String message;

    public GenericDTO(){}
    public GenericDTO(String title){this.title = title;}
    public GenericDTO(String title, String message){this(title); this.message = message;}
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
