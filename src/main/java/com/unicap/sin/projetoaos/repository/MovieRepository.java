package com.unicap.sin.projetoaos.repository;

import com.unicap.sin.projetoaos.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
