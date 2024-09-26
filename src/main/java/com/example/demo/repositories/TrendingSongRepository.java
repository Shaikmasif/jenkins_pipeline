package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Trending;

public interface TrendingSongRepository extends JpaRepository<Trending, Integer> {

}
