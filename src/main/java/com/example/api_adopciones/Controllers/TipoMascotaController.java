package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.TipoMascota;
import com.example.api_adopciones.Repositories.TipoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo_mascotas")
public class TipoMascotaController {

    @Autowired
    private TipoMascotaRepository tipoMascotaRepository; // Inyección del repositorio

    // Obtener todos los tipos de mascotas
    @GetMapping
    public ResponseEntity<List<TipoMascota>> getAllTipoMascotas() {
        List<TipoMascota> tipoMascotas = tipoMascotaRepository.findAll();
        if (tipoMascotas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoMascotas);
        }
        return ResponseEntity.ok(tipoMascotas);
    }

    // Obtener tipo de mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoMascota> getTipoMascotaById(@PathVariable Long id) {
        return tipoMascotaRepository.findById(id)
                .map(tipoMascota -> ResponseEntity.ok(tipoMascota))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Crear nuevo tipo de mascota
    @PostMapping
    public ResponseEntity<String> createTipoMascota(@RequestBody TipoMascota tipoMascota) {
        TipoMascota savedTipoMascota = tipoMascotaRepository.save(tipoMascota);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tipo de mascota con ID " + savedTipoMascota.getId() + " y nombre " + savedTipoMascota.getTipo() + " ha sido creado exitosamente.");
    }

    // Eliminar tipo de mascota por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipoMascota(@PathVariable Long id) {
        if (!tipoMascotaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de mascota con ID " + id + " no encontrado.");
        }

        TipoMascota tipoMascota = tipoMascotaRepository.findById(id).orElseThrow();
        tipoMascotaRepository.deleteById(id);

        return ResponseEntity.ok("Tipo de mascota con ID " + id + " y nombre " + tipoMascota.getTipo() + " ha sido eliminado exitosamente.");
    }

    // Actualizar tipo de mascota por ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTipoMascota(@PathVariable Long id, @RequestBody TipoMascota tipoMascota) {
        if (!tipoMascotaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tipo de mascota con ID " + id + " no encontrado.");
        }
        TipoMascota existingTipoMascota = tipoMascotaRepository.findById(id).orElseThrow();
        existingTipoMascota.setTipo(tipoMascota.getTipo());
        tipoMascotaRepository.save(existingTipoMascota);

        return ResponseEntity.ok("Tipo de mascota con ID " + id + " ha sido actualizado exitosamente.");
    }
}



