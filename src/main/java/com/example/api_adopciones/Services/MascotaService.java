package com.example.api_adopciones.Services;


import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Models.TipoMascota;
import com.example.api_adopciones.Repositories.MascotaRepository;
import com.example.api_adopciones.Repositories.TipoMascotaRepository;
import org.springframework.stereotype.Service;
import com.example.api_adopciones.DTOs.MascotaDTO;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final TipoMascotaRepository tipoMascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository, TipoMascotaRepository tipoMascotaRepository) {
        this.mascotaRepository = mascotaRepository;
        this.tipoMascotaRepository = tipoMascotaRepository;
    }

    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getMascotaById(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota createMascota(MascotaDTO mascotaDTO) {
        // Buscar el TipoMascota por ID
        TipoMascota tipoMascota = tipoMascotaRepository.findById(mascotaDTO.getTipoMascota())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de mascota no encontrado"));

        // Mapear DTO a la entidad
        Mascota mascota = new Mascota();
        mascota.setNombreMascota(mascotaDTO.getNombreMascota());
        mascota.setTipoMascota(tipoMascota);
        mascota.setIngreso(mascotaDTO.getIngreso());
        mascota.setDescripcion(mascotaDTO.getDescripcion());
        mascota.setDisponible(mascotaDTO.isDisponible());

        // Guardar la entidad
        return mascotaRepository.save(mascota);
    }




    public Optional<Mascota> updateMascota(Long id, Mascota mascotaDetails) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        mascota.ifPresent(m -> {
            m.setNombreMascota(mascotaDetails.getNombreMascota());
            m.setTipoMascota(mascotaDetails.getTipoMascota());
            m.setIngreso(mascotaDetails.getIngreso());
            m.setDescripcion(mascotaDetails.getDescripcion());
            m.setDisponible(mascotaDetails.isDisponible());
            mascotaRepository.save(m);
        });
        return mascota;
    }

    public boolean deleteMascota(Long id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


