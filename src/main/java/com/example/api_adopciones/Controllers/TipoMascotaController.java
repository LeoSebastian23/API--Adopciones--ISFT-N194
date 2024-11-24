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

    @Autowired private TipoMascotaRepository tipoMascotaRepository; // Inyección del repositorio

    @GetMapping
    public List<TipoMascota> getAllTipoMascotas() { return tipoMascotaRepository.findAll(); } // Obtener todos los tipos de mascotas

    @GetMapping("/{id}")
    public ResponseEntity<TipoMascota> getTipoMascotaById(@PathVariable Long id) {
        return tipoMascotaRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } // Obtener tipo de mascota por ID

    @PostMapping
    public ResponseEntity<TipoMascota> createTipoMascota(@RequestBody TipoMascota tipoMascota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoMascotaRepository.save(tipoMascota));
    } // Crear nuevo tipo de mascota

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoMascota(@PathVariable Long id) {
        if (!tipoMascotaRepository.existsById(id)) return ResponseEntity.notFound().build();
        tipoMascotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    } // Eliminar tipo de mascota por ID
}


