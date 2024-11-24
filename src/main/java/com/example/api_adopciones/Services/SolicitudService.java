package com.example.api_adopciones.Services;

import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Models.Solicitud;
import com.example.api_adopciones.Repositories.MascotaRepository;
import com.example.api_adopciones.Repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final MascotaRepository mascotaRepository;

    // Constructor
    public SolicitudService(SolicitudRepository solicitudRepository, MascotaRepository mascotaRepository) {
        this.solicitudRepository = solicitudRepository;
        this.mascotaRepository = mascotaRepository;
    }

    // Obtener todas las solicitudes
    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Obtener solicitud por ID
    public Optional<Solicitud> getSolicitudById(Long id) {
        return solicitudRepository.findById(id);
    }

    // Crear una nueva solicitud
    public Solicitud createSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // Actualizar una solicitud
    public Optional<Solicitud> updateSolicitud(Long id, Solicitud solicitudDetails) {
        return solicitudRepository.findById(id).map(s -> {
            s.setAdoptante(solicitudDetails.getAdoptante());
            s.setMascota(solicitudDetails.getMascota());
            s.setFechaSolicitud(solicitudDetails.getFechaSolicitud());
            return solicitudRepository.save(s);
        });
    }

    // Eliminar una solicitud
    public boolean deleteSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Procesar la solicitud de adopción simulada
    public String procesarSolicitudAdopcion(Solicitud solicitud) {
        // Obtenemos el ID de la mascota de la solicitud
        Long mascotaId = solicitud.getMascota().getId();

        // Buscar la mascota en la base de datos
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        // Verificar si la mascota está disponible para adopción
        if (!mascota.isDisponible()) {
            return "La mascota ya ha sido adoptada o no está disponible.";
        }

        // Aprobar la adopción: marcar la mascota como no disponible
        mascota.setDisponible(false);
        mascotaRepository.save(mascota);  // Guardar la actualización de la mascota
        return "Aprobada";
    }

}




