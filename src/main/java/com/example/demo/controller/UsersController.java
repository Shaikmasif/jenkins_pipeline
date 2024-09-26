package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Song;
import com.example.demo.entities.Trending;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.TrendingSong;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	@Autowired
	TrendingSong trendingSongService;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false) {
			service.addUsers(user);
			System.out.println("User added.");
		}
		else {
			System.out.println("user already exists");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,@RequestParam("password") String password,HttpSession session,Model model) {
		if(service.emailExists(email)) {
			if(service.validateUser(email,password)==true) {
				String role=service.getRole(email);
				
				session.setAttribute("email", email);
				
				if(role.equals("admin")) {
					return "adminHome";
				}else {
						List<Song> songsList = songService.fetchAllSongs();
						model.addAttribute("songs", songsList);
						List<Trending> trendingSongsList = trendingSongService.fetchAllSongs();
						model.addAttribute("trendingSongs", trendingSongsList);
						Users user = service.getUser(email);
						boolean userStatus = user.isPremium();
						model.addAttribute("isPremium",userStatus);
						System.out.println(songsList);
						return "customerHome";
					
				}
			}
		}
		else {
			return "login";
		}
		return "login";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "login";
	}
	@PostMapping("/forgot")
	public String forgot(@RequestParam("email") String email,@RequestParam("password") String password){
		if(service.updatePassword(email, password)) {
			return "login";
		}else {
			return "registration";
		}
	}
	
}
