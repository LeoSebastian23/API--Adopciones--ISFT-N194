package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Services.AdoptanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adoptantes")
public class AdoptanteController {

    private final AdoptanteService adoptanteService;

    @Autowired
    public AdoptanteController(AdoptanteService adoptanteService) {
        this.adoptanteService = adoptanteService;
    }

    @GetMapping
    public List<Adoptante> getAllAdoptantes() {
        return adoptanteService.getAllAdoptantes();
    } // Obtener todos los adoptantes

    @GetMapping("/{id}")
    public ResponseEntity<Adoptante> getAdoptanteById(@PathVariable Long id) {
        return adoptanteService.getAdoptanteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } // Obtener adoptante por ID

    @PostMapping
    public ResponseEntity<Adoptante> createAdoptante(@RequestBody Adoptante adoptante) {
        Adoptante nuevoAdoptante = adoptanteService.createAdoptante(adoptante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdoptante);
    } // Crear nuevo adoptante

    @PutMapping("/{id}")
    public ResponseEntity<Adoptante> updateAdoptante(@PathVariable Long id, @RequestBody Adoptante adoptanteDetails) {
        return adoptanteService.updateAdoptante(id, adoptanteDetails)
                .map(updatedAdoptante -> ResponseEntity.ok(updatedAdoptante))
                .orElseGet(() -> ResponseEntity.notFound().build());
    } // Actualizar adoptante

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptante(@PathVariable Long id) {
        if (adoptanteService.deleteAdoptante(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    } // Eliminar adoptante por ID
}


