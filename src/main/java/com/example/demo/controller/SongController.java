package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.entities.Trending;
import com.example.demo.services.SongService;
import com.example.demo.services.TrendingSong;

import jakarta.transaction.Transactional;


@Controller
public class SongController {
	@Autowired
	SongService service;
	@Autowired
	TrendingSong trendingSongService;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus=service.songExists(song.getName());
		if(songStatus==false) {
		service.addSong(song);
		System.out.println("Song added successfully.");
		}else {
			System.out.println("song already exists.");
		}
		return "adminHome";
	}
	@GetMapping("viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList = service.fetchAllSongs();
	
		model.addAttribute("songs", songsList);
		List<Trending> trendingSongsList = trendingSongService.fetchAllSongs();
		model.addAttribute("trendingSongs",trendingSongsList);
		return "displaySongs";
	}
	@GetMapping("playSongs")
	public String playSongs(Model model) {
		
		boolean premiumUser = true;
		if(premiumUser==true) {
		List<Song> songsList = service.fetchAllSongs();
		model.addAttribute("songs", songsList);
		List<Trending> trendingSongsList = trendingSongService.fetchAllSongs();
		model.addAttribute("trendingSongs",trendingSongsList);
		return "displaySongs";
		}
		else {
			return "makePayment";
		}
	}
	@PostMapping("/trendingSong")
	@Transactional
	public String addTrendingSong(@ModelAttribute Trending song,Model model) {
		trendingSongService.addSong(song);
		List<Song> songsList = service.fetchAllSongs();
		model.addAttribute("songs", songsList);
		List<Trending> trendingSongsList = trendingSongService.fetchAllSongs();
		model.addAttribute("trendingSongs",trendingSongsList);
		return "adminHome";
	}
	
}
