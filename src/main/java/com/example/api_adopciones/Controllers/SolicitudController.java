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
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.createSolicitud(solicitud));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitudDetails) {
        return solicitudService.updateSolicitud(id, solicitudDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        return solicitudService.deleteSolicitud(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Metodo para simular la adopción de una mascota
    @PostMapping("/simular-adopcion")
    public ResponseEntity<String> simularAdopcion(@RequestBody Solicitud solicitud) {
        // Llamamos al servicio para procesar la solicitud de adopción
        String resultado = solicitudService.procesarSolicitudAdopcion(solicitud);

        if (resultado.equals("Aprobada")) {
            return ResponseEntity.ok("La adopción ha sido aprobada.");
        } else {
            return ResponseEntity.status(400).body("La adopción ha sido rechazada o la mascota no está disponible.");
        }
    }

}






