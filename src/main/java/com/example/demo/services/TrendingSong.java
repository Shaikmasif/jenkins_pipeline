package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Trending;

public interface TrendingSong {
	public void addTrendingSong(Trending song);
	public List<Trending> findSongs();
	public List<Trending> fetchAllSongs();
	public void addSong(Trending song);

}
