package com.unicap.sin.projetoaos.repository;

import com.unicap.sin.projetoaos.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
