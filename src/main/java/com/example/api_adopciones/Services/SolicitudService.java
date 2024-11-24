package com.example.api_adopciones.Services;

import com.example.api_adopciones.Models.Solicitud;
import com.example.api_adopciones.Repositories.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
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
}



