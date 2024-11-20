package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.TipoMascota;
import com.example.api_adopciones.Repositories.TipoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Indica que esta clase funciona como controlador.
@RequestMapping("/api/tipo_mascotas")//Ruta de gestion a peticiones.

public class TipoMascotaController {

    //indicamos con que repositorios va a trabajar.
    @Autowired // Evita crear controladoras nuevas en cada ejecución.
    private TipoMascotaRepository tipoMascotaRepository;

    //ACCIONES CRUD
    // Define qué controlador maneja cada petición basándote en el verbo (acción) y el URL.

    // Solicita todos los tipos de mascotas.
    // @CrossOrigin permite acceso desde otros orígenes.
    // @GetMapping define que el metodo responderá a solicitudes GET.
    @GetMapping
    public List<TipoMascota> getAllTipoMascotas() {
        // Retorna todos los tipos de mascotas usando el metodo findAll() del repositorio JPA.
        return tipoMascotaRepository.findAll();
    }


    // Solicita un tipo de mascota por su ID.
    // @GetMapping("/{id}") define la ruta y el metodo HTTP para esta solicitud.
    @GetMapping("/{id}")
    public ResponseEntity<TipoMascota> getTipoMascotaById(@PathVariable Long id) {
        // @PathVariable vincula el parámetro id al valor en la URL.
        // Buscamos el tipo por ID, devolviendo 200 OK si se encuentra, o 404 Not Found si no.
        Optional<TipoMascota> tipoMascota = tipoMascotaRepository.findById(id);
        return tipoMascota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crea una nuevo tipo de mascota.
    // Usamos @PostMapping para una solicitud POST y @CrossOrigin para acceso desde otros orígenes.
    @PostMapping
    public ResponseEntity<TipoMascota> createTipoMascota(@RequestBody TipoMascota tipoMascota) {
        // @RequestBody vincula la entidad TipoMascota enviada en el cuerpo de la solicitud.
        // Guardamos el tipo de mascota en el repositorio y devolvemos el objeto creado con estado 201 Created.
        TipoMascota savedTipoMascota= tipoMascotaRepository.save(tipoMascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoMascota);
    }


    // Elimina un tipo de mascota por ID.
    // @DeleteMapping("/{id}") define que el metodo responderá a solicitudes DELETE en esta URL.
    // @CrossOrigin permite acceso desde otros orígenes.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoMascota(@PathVariable Long id) {
        // Si el ID no existe, devolvemos 404 Not Found.
        if (!tipoMascotaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Si existe, eliminamos la Movie y devolvemos 204 No Content.
        tipoMascotaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

