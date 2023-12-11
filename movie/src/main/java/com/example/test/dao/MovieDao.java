package com.example.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entity.MovieEntity;

@Repository
public interface MovieDao extends JpaRepository<MovieEntity, Integer> {

}
