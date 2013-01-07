package com.javagame.db.model;

public class PictureModel {
	
	private Long id;
	private byte[] picture;

	public PictureModel(Long id, byte[] blob) {
		this.id = id;
		this.picture = blob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	

}
