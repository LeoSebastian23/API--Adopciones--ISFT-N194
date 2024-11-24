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

    public SolicitudController(SolicitudService solicitudService) { this.solicitudService = solicitudService; }

    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() { return ResponseEntity.ok(solicitudService.getAllSolicitudes()); } // Obtener todas las solicitudes

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.getSolicitudById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    } // Obtener solicitud por ID

    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.createSolicitud(solicitud));
    } // Crear nueva solicitud

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitudDetails) {
        return solicitudService.updateSolicitud(id, solicitudDetails).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    } // Actualizar solicitud

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        return solicitudService.deleteSolicitud(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    } // Eliminar solicitud por ID
}




