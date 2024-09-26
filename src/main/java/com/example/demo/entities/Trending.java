package com.example.demo.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trending {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String image;
	String title;
	String artist;
	String type;
	String link;
	
	public Trending() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trending(String image, String title, String artist, String link) {
		super();
		this.image = image;
		this.title = title;
		this.artist = artist;
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "Trending [image=" + image + ", title=" + title + ", artist=" + artist + ", link=" + link + "]";
	}
	

}
