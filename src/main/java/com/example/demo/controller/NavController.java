package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Trending;
import com.example.demo.services.TrendingSong;


@Controller
public class NavController {
	@Autowired
	TrendingSong song;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	@GetMapping("/addSong")
	public String newSong() {
		return "newSong";
	}
	@GetMapping("/trending")
	public String trendingSong() {
		return "trending";
	}
	@GetMapping("/")
	public String findTrendingSongs(Model songs) {
		List<Trending> songsList = song.findSongs();
		songs.addAttribute("songs", songsList);
		
		return "index";
	}
	@GetMapping("/forgot")
	public String forgot() {
		return "forgot";
	}
	@GetMapping("tunehub")
	public String tunehub() {
		return "index";
	}
	
}
