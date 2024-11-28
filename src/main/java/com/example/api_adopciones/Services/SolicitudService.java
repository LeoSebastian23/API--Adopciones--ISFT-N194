package com.example.api_adopciones.Services;

import com.example.api_adopciones.DTOs.SolicitudDTO;
import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Models.Solicitud;
import com.example.api_adopciones.Repositories.AdoptanteRepository;
import com.example.api_adopciones.Repositories.MascotaRepository;
import com.example.api_adopciones.Repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final MascotaRepository mascotaRepository;
    private final AdoptanteRepository adoptanteRepository;

    public SolicitudService(SolicitudRepository solicitudRepository,
                            MascotaRepository mascotaRepository,
                            AdoptanteRepository adoptanteRepository) {
        this.solicitudRepository = solicitudRepository;
        this.mascotaRepository = mascotaRepository;
        this.adoptanteRepository = adoptanteRepository;
    }


    // Obtener todas las solicitudes
    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Obtener solicitud por ID
    public Optional<Solicitud> getSolicitudById(Long id) {
        return solicitudRepository.findById(id);
    }

    public Solicitud createSolicitud(SolicitudDTO solicitudDTO) {
        Mascota mascota = mascotaRepository.findById(solicitudDTO.getIdMascota())
                .orElseThrow(() -> new IllegalArgumentException("La mascota no existe"));

        Adoptante adoptante = adoptanteRepository.findById(solicitudDTO.getDniAdoptante())
                .orElseThrow(() -> new IllegalArgumentException("El adoptante no existe"));

        Solicitud solicitud = new Solicitud();
        solicitud.setMascota(mascota);
        solicitud.setAdoptante(adoptante);
        solicitud.setFechaSolicitud(solicitudDTO.getFechaSolicitud());

        return solicitudRepository.save(solicitud);
    }

    // Actualizar una solicitud
    public Solicitud updateSolicitud(Long id, SolicitudDTO solicitudDTO) {
        return solicitudRepository.findById(id).map(solicitud -> {
            Mascota mascota = mascotaRepository.findById(solicitudDTO.getIdMascota())
                    .orElseThrow(() -> new IllegalArgumentException("La mascota no existe"));

            Adoptante adoptante = adoptanteRepository.findById(solicitudDTO.getDniAdoptante())
                    .orElseThrow(() -> new IllegalArgumentException("El adoptante no existe"));

            solicitud.setMascota(mascota);
            solicitud.setAdoptante(adoptante);
            solicitud.setFechaSolicitud(solicitudDTO.getFechaSolicitud());
            return solicitudRepository.save(solicitud);
        }).orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
    }


    public boolean deleteSolicitud(Long id) {
        try {
            if (solicitudRepository.existsById(id)) {
                solicitudRepository.deleteById(id);
                return true;
            }
            return false; // No existe la solicitud
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la solicitud con ID: " + id, e);
        }
    }

    /*
    public String procesarSolicitudAdopcion(Solicitud solicitud) {
        if (solicitud == null || solicitud.getMascota() == null) {
            throw new IllegalArgumentException("La solicitud o la mascota asociada no puede ser nula");
        }

        // Obtenemos el ID de la mascota de la solicitud
        Long mascotaId = solicitud.getMascota().getId();

        // Buscar la mascota en la base de datos
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada con ID: " + mascotaId));

        // Verificar si la mascota está disponible para adopción
        if (!mascota.isDisponible()) {
            return "La mascota ya ha sido adoptada o no está disponible.";
        }

        // Aprobar la adopción: marcar la mascota como no disponible
        mascota.setDisponible(false);
        mascotaRepository.save(mascota);  // Guardar solo si es necesario

        return "Aprobada";
    }
    */

}








