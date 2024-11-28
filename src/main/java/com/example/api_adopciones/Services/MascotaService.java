package com.example.api_adopciones.Services;


import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Models.TipoMascota;
import com.example.api_adopciones.Repositories.MascotaRepository;
import com.example.api_adopciones.Repositories.TipoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api_adopciones.DTOs.MascotaDTO;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private  MascotaRepository mascotaRepository;
    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository, TipoMascotaRepository tipoMascotaRepository) {
    }

    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getMascotaById(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota createMascota(MascotaDTO mascotaDTO) {
        // Buscar el TipoMascota por ID

        TipoMascota tipoMascota = tipoMascotaRepository.findById(mascotaDTO.getTipoMascotaId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de mascota no encontrado " + mascotaDTO));

        // Mapear DTO a la entidad
        Mascota mascota = new Mascota();
        mascota.setNombreMascota(mascotaDTO.getNombreMascota());
        mascota.setTipoMascotaId(tipoMascota);
        mascota.setIngreso(mascotaDTO.getIngreso());
        mascota.setDescripcion(mascotaDTO.getDescripcion());
        mascota.setDisponible(mascotaDTO.isDisponible());

        // Guardar la entidad
        return mascotaRepository.save(mascota);
    }

    public Mascota updateMascota(Long id, MascotaDTO mascotaDTO) {
        // Verifica que la mascota existe
        Mascota existingMascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada con ID " + id));

        // Verifica que el tipo de mascota existe
        TipoMascota tipoMascota = tipoMascotaRepository.findById(mascotaDTO.getTipoMascotaId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de mascota no encontrado con ID " + mascotaDTO.getTipoMascotaId()));

        // Actualiza los campos
        existingMascota.setNombreMascota(mascotaDTO.getNombreMascota());
        existingMascota.setTipoMascotaId(tipoMascota);
        existingMascota.setIngreso(mascotaDTO.getIngreso());
        existingMascota.setDescripcion(mascotaDTO.getDescripcion());
        existingMascota.setDisponible(mascotaDTO.isDisponible());

        // Guarda los cambios
        return mascotaRepository.save(existingMascota);
    }



    public boolean deleteMascota(Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


