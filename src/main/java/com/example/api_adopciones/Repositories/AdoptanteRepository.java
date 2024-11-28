package com.example.api_adopciones.Repositories;

import com.example.api_adopciones.Models.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptanteRepository extends JpaRepository<Adoptante, Long > {
}