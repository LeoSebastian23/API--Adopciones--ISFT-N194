package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Repositories.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    // Obtener todas las mascotas
    @GetMapping
    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    // Obtener una mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        return mascota.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva mascota
    @PostMapping
    public Mascota createMascota(@RequestBody Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    // Actualizar una mascota
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable Long id, @RequestBody Mascota mascotaDetails) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        if (mascotaOptional.isPresent()) {
            Mascota mascota = mascotaOptional.get();
            mascota.setNombreMascota(mascotaDetails.getNombreMascota());
            mascota.setTipoMascota(mascotaDetails.getTipoMascota());
            mascota.setEdad(mascotaDetails.getEdad());
            mascota.setDescripcion(mascotaDetails.getDescripcion());
            return ResponseEntity.ok(mascotaRepository.save(mascota));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una mascota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

