package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Services.MascotaService;
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
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.getMascotaById(id);
        return mascota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            Mascota mascota = mascotaService.createMascota(mascotaDTO);
            return ResponseEntity.ok(mascota);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la mascota");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable Long id, @RequestBody Mascota mascotaDetails) {
        Optional<Mascota> updatedMascota = mascotaService.updateMascota(id, mascotaDetails);
        return updatedMascota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        if (mascotaService.deleteMascota(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



