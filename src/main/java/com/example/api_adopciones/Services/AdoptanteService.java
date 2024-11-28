package com.example.api_adopciones.Services;

import com.example.api_adopciones.DTOs.AdoptanteDTO;
import com.example.api_adopciones.Exceptions.AdoptanteCreationException;
import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Repositories.AdoptanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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

    public AdoptanteDTO getAdoptanteById(Long id) {
        Adoptante adoptante = adoptanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoptante no encontrado con ID " + id));

        AdoptanteDTO adoptanteDTO = new AdoptanteDTO();
        adoptanteDTO.setNombre(adoptante.getNombre());
        adoptanteDTO.setApellido(adoptante.getApellido());
        adoptanteDTO.setCelular(adoptante.getCelular());
        adoptanteDTO.setEmail(adoptante.getEmail());

        return adoptanteDTO;
    }

    public Adoptante createAdoptante(AdoptanteDTO adoptanteDTO) {
        try {
            // Validaciones previas
            if (adoptanteDTO.getDni() == null || adoptanteDTO.getEmail() == null) {
                throw new AdoptanteCreationException("Faltan datos necesarios.");
            }

            // Crear la entidad Adoptante
            Adoptante adoptante = new Adoptante();
            adoptante.setNombre(adoptanteDTO.getNombre());
            adoptante.setApellido(adoptanteDTO.getApellido());
            adoptante.setDni(adoptanteDTO.getDni());
            adoptante.setCelular(adoptanteDTO.getCelular());
            adoptante.setEmail(adoptanteDTO.getEmail());

            // Guardar el adoptante
            return adoptanteRepository.save(adoptante);
        } catch (DataIntegrityViolationException e) {
            // Ejemplo de error de integridad, como un DNI duplicado
            throw new AdoptanteCreationException("El DNI ya está registrado.");
        } catch (TransactionSystemException e) {
            // Error de validación o transacción
            throw new AdoptanteCreationException("Hubo un problema con los datos proporcionados.");
        } catch (Exception e) {
            // Captura de cualquier otra excepción
            throw new AdoptanteCreationException("Error desconocido al crear el adoptante: " + e.getMessage(), e);
        }
    }





    // Actualizar un adoptante
    public Adoptante updateAdoptante(Long id, AdoptanteDTO adoptanteDTO) {
        Adoptante adoptante = adoptanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoptante no encontrado con ID " + id));

        adoptante.setNombre(adoptanteDTO.getNombre());
        adoptante.setApellido(adoptanteDTO.getApellido());
        adoptante.setDni(adoptanteDTO.getDni());
        adoptante.setCelular(adoptanteDTO.getCelular());  // Mantén el tipo Long
        adoptante.setEmail(adoptanteDTO.getEmail());

        return adoptanteRepository.save(adoptante);
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

