package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Solicitud;
import com.example.api_adopciones.Services.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    // Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }

    // Obtener una solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.getSolicitudById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva solicitud (sin validación)
    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        Solicitud nuevaSolicitud = solicitudService.createSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    // Actualizar una solicitud (sin validación)
    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitudDetails) {
        return solicitudService.updateSolicitud(id, solicitudDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        if (solicitudService.deleteSolicitud(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



