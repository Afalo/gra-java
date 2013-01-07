package com.javagame.db.model;

public class AnswerModel {

	private long id;
	private String description;
	private Long pictureId;
	private String langVersion;
	
	private boolean goodAnswer = false;

	public AnswerModel(long id, String description, Long pictureId, String langVersion) {
		this.id = id;
		this.description = description;
		this.pictureId = pictureId;
		this.langVersion = langVersion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public String getLangVersion() {
		return langVersion;
	}

	public void setLangVersion(String langVersion) {
		this.langVersion = langVersion;
	}

	public boolean isGoodAnswer() {
		return goodAnswer;
	}

	public void setGoodAnswer(boolean goodAnswer) {
		this.goodAnswer = goodAnswer;
	}

}
