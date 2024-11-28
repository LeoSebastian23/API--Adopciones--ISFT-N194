package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Services.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.api_adopciones.DTOs.MascotaDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public List<Mascota> getAllMascotas() {
        return mascotaService.getAllMascotas();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMascotaById(@PathVariable Long id) {
        try {
            Optional <Mascota> mascotaDTO = mascotaService.getMascotaById(id);
            return ResponseEntity.ok(mascotaDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la mascota.");
        }
    }


    @PostMapping
    public ResponseEntity<?> createMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota mascota = mascotaService.createMascota(mascotaDTO);
            return ResponseEntity.ok(mascota);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la excepción en los logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la mascota: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMascota(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota updatedMascota = mascotaService.updateMascota(id, mascotaDTO);
            return ResponseEntity.ok("Mascota con ID " + id + " actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la mascota.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMascota(@PathVariable Long id) {
        if (mascotaService.deleteMascota(id)) {
            return ResponseEntity.ok("Mascota con ID " + id + " eliminada exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota con ID " + id + " no encontrada.");
        }
    }

}



