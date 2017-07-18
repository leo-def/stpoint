package com.pp.stpoint.dto;

public class SituationAsItemDTO {
	private Long id;
	private String title;
	
	public SituationAsItemDTO(){}
	public SituationAsItemDTO(Object[] params){
		setId((Long)params[0]);
		setTitle((String)params[1]);
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
}
