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
            // Responder con un mensaje claro en caso de error
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Capturar cualquier otra excepción y devolver un error 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la solicitud: " + e.getMessage());
        }
    }


    // PUT: Actualizar solicitud
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
        try {
            Solicitud solicitudActualizada = solicitudService.updateSolicitud(id, solicitudDTO);
            return ResponseEntity.ok(solicitudActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la solicitud");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSolicitud(@PathVariable Long id) {
        try {
            boolean deleted = solicitudService.deleteSolicitud(id);
            if (deleted) {
                return ResponseEntity.ok("La solicitud se eliminó exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La solicitud con el ID proporcionado no existe");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al intentar eliminar la solicitud: " + e.getMessage());
        }
    }



    // Metodo para simular la adopción de una mascota
    @PostMapping("/simular-adopcion")
    public ResponseEntity<String> simularAdopcion(@RequestBody SolicitudDTO solicitudDTO) {
        try {
            // Llamamos al servicio para procesar la solicitud de adopción
            String resultado = solicitudService.procesarSolicitudAdopcion(solicitudDTO);

            if (resultado.equals("Aprobada")) {
                return ResponseEntity.ok("La adopción ha sido aprobada.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La adopción ha sido rechazada o la mascota no está disponible.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error en la solicitud: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al procesar la adopción: " + e.getMessage());
        }
    }


}






