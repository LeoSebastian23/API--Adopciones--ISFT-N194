package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.DTOs.AdoptanteDTO;
import com.example.api_adopciones.Exceptions.AdoptanteCreationException;
import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Services.AdoptanteService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> getAdoptanteById(@PathVariable Long id) {
        try {
            AdoptanteDTO adoptanteDTO = adoptanteService.getAdoptanteById(id);
            return ResponseEntity.ok(adoptanteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el adoptante.");
        }
    }

    @ExceptionHandler(AdoptanteCreationException.class)
    public ResponseEntity<String> handleAdoptanteCreationException(AdoptanteCreationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @PostMapping
    public ResponseEntity<?> createAdoptante(@RequestBody AdoptanteDTO adoptanteDTO) {
        try {
            // Mostrar el contenido del DTO recibido
            System.out.println("Recibido AdoptanteDTO: " + adoptanteDTO);

            // Crear el objeto Adoptante
            Adoptante adoptante = new Adoptante();
            adoptante.setNombre(adoptanteDTO.getNombre());
            adoptante.setApellido(adoptanteDTO.getApellido());
            adoptante.setDni(adoptanteDTO.getDni());
            adoptante.setCelular(adoptanteDTO.getCelular());
            adoptante.setEmail(adoptanteDTO.getEmail());

            // Guardar el adoptante (aquí deberías ver si se guarda correctamente o si hay errores)
            adoptante = adoptanteService.createAdoptante(adoptanteDTO);

            return ResponseEntity.ok("Adoptante creado exitosamente con ID: " + adoptante.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el adoptante: " + e.getMessage());
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdoptante(@PathVariable Long id, @RequestBody AdoptanteDTO adoptanteDTO) {
        try {
            Adoptante updatedAdoptante = adoptanteService.updateAdoptante(id, adoptanteDTO);
            return ResponseEntity.ok("Adoptante con ID " + id + " actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el adoptante.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdoptante(@PathVariable Long id) {
        boolean isDeleted = adoptanteService.deleteAdoptante(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Adoptante con ID " + id + " eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adoptante con ID " + id + " no encontrado.");
        }
    }

}


