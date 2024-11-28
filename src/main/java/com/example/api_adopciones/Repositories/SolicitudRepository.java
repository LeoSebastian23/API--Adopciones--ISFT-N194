package com.example.api_adopciones.Repositories;

import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

}


