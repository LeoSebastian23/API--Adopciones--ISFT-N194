package com.example.api_adopciones.Services;

import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Repositories.AdoptanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptanteService {

    private final AdoptanteRepository adoptanteRepository;

    @Autowired
    public AdoptanteService(AdoptanteRepository adoptanteRepository) {
        this.adoptanteRepository = adoptanteRepository;
    }

    // Obtener todos los adoptantes
    public List<Adoptante> getAllAdoptantes() {
        return adoptanteRepository.findAll();
    }

    // Obtener adoptante por ID
    public Optional<Adoptante> getAdoptanteById(Long id) {
        return adoptanteRepository.findById(id);
    }

    // Crear un nuevo adoptante
    public Adoptante createAdoptante(Adoptante adoptante) {
        return adoptanteRepository.save(adoptante);
    }

    // Actualizar un adoptante
    public Optional<Adoptante> updateAdoptante(Long id, Adoptante adoptanteDetails) {
        Optional<Adoptante> adoptanteOptional = adoptanteRepository.findById(id);
        adoptanteOptional.ifPresent(adoptante -> {
            adoptante.setNombre(adoptanteDetails.getNombre());
            adoptante.setApellido(adoptanteDetails.getApellido());
            adoptante.setDni(adoptanteDetails.getDni());
            adoptante.setCelular(adoptanteDetails.getCelular());
            adoptante.setEmail(adoptanteDetails.getEmail());
            adoptanteRepository.save(adoptante);
        });
        return adoptanteOptional;
    }

    // Eliminar un adoptante
    public boolean deleteAdoptante(Long id) {
        if (adoptanteRepository.existsById(id)) {
            adoptanteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

