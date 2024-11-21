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

    public Optional<Solicitud> getSolicitudById(Long id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);
        return solicitud; // Devuelve Optional vacío si no encuentra la solicitud
    }


    // Crear una nueva solicitud
    public Solicitud createSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // Actualiza una solicitud
    public Optional<Solicitud> updateSolicitud(Long id, Solicitud solicitudDetails) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);
        solicitud.ifPresent(s -> {
            s.setAdoptante(solicitudDetails.getAdoptante());
            s.setMascota(solicitudDetails.getMascota());
            s.setEstadoSolicitud(solicitudDetails.isEstadoSolicitud());
            s.setFechaSolicitud(solicitudDetails.getFechaSolicitud());
            solicitudRepository.save(s);
        });
        return solicitud;
    }


    // Eliminar solicitud
    public boolean deleteSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


