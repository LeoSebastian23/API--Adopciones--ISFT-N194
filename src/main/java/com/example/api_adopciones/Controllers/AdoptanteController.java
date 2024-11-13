package com.example.api_adopciones.Controllers;

import com.example.api_adopciones.Models.Adoptante;
import com.example.api_adopciones.Repositories.AdoptanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //Indica que esta clase funciona como controlador.
@RequestMapping("/api/adoptantes")//Ruta de gestion a peticiones.

public class AdoptanteController {

    //indicamos con que repositorios va a trabajar.
    @Autowired // Evita crear controladoras nuevas en cada ejecución.
    private AdoptanteRepository adoptanteRepository;

    //ACCIONES CRUD
    // Define qué controlador maneja cada petición basándote en el verbo (acción) y el URL.

    // Solicita todos los Adoptantes.
    // @CrossOrigin permite acceso desde otros orígenes.
    // @GetMapping define que el metodo responderá a solicitudes GET.
    @GetMapping
    public List<Adoptante> getAllAdoptante() {
        // Retorna todos los adoptantes usando el metodo findAll() del repositorio JPA.
        return adoptanteRepository.findAll();
    }


    // Solicita un Adoptante por su ID.
    // @GetMapping("/{id}") define la ruta y el metodo HTTP para esta solicitud.
    @GetMapping("/{id}")
    public ResponseEntity<Adoptante> getAdoptanteById(@PathVariable Long id) {
        // @PathVariable vincula el parámetro id al valor en la URL.
        // Buscamos el Adoptante por ID, devolviendo 200 OK si se encuentra, o 404 Not Found si no.
        Optional<Adoptante> adoptante = adoptanteRepository.findById(id);
        return adoptante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crea una nueva Movie.
    // Usamos @PostMapping para una solicitud POST y @CrossOrigin para acceso desde otros orígenes.
    @PostMapping
    public ResponseEntity<Adoptante> createMovie(@RequestBody Adoptante adoptante) {
        // @RequestBody vincula la entidad Movie enviada en el cuerpo de la solicitud.
        // Guardamos la Movie en el repositorio y devolvemos el objeto creado con estado 201 Created.
        Adoptante savedAdoptante = adoptanteRepository.save(adoptante);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdoptante);
    }


    // Elimina una Movie por ID.
    // @DeleteMapping("/{id}") define que el metodo responderá a solicitudes DELETE en esta URL.
    // @CrossOrigin permite acceso desde otros orígenes.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptante(@PathVariable Long id) {
        // Si el ID no existe, devolvemos 404 Not Found.
        if (!adoptanteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Si existe, eliminamos la Movie y devolvemos 204 No Content.
        adoptanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


//crud
