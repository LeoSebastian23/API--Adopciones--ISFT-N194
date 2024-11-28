package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.DTOs.SolicitudDTO;
import com.example.api_adopciones.Models.Solicitud;
import com.example.api_adopciones.Services.SolicitudService;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        return ResponseEntity.ok(solicitudService.getAllSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        return solicitudService.getSolicitudById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        try {
            Solicitud solicitud = solicitudService.createSolicitud(solicitudDTO);
            return ResponseEntity.ok(solicitud);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la solicitud");
        }
    }
}


/* @PutMapping("/{id}")
public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
    try {
        Solicitud solicitudActualizada = solicitudService.updateSolicitud(id, solicitudDTO);
        return ResponseEntity.ok(solicitudActualizada);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(null);
    }
}*/


    /* @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        return solicitudService.deleteSolicitud(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    } */

    /*
    // Metodo para simular la adopción de una mascota
    @PostMapping("/simular-adopcion")
    public ResponseEntity<String> simularAdopcion(@RequestBody SolicitudDTO solicitudDTO) {
        // Llamamos al servicio para procesar la solicitud de adopción
        String resultado = solicitudService.procesarSolicitudAdopcion(SolicitudDTO);

        if (resultado.equals("Aprobada")) {
            return ResponseEntity.ok("La adopción ha sido aprobada.");
        } else {
            return ResponseEntity.status(400).body("La adopción ha sido rechazada o la mascota no está disponible.");
        }
    }
    */









