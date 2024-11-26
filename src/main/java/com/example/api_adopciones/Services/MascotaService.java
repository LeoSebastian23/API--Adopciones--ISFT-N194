package com.example.api_adopciones.Services;

import com.example.api_adopciones.Models.Mascota;
import com.example.api_adopciones.Repositories.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getMascotaById(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota createMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }


    public Optional<Mascota> updateMascota(Long id, Mascota mascotaDetails) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        mascota.ifPresent(m -> {
            m.setNombreMascota(mascotaDetails.getNombreMascota());
            m.setTipoMascota(mascotaDetails.getTipoMascota());
            m.setIngreso(mascotaDetails.getIngreso());
            m.setDescripcion(mascotaDetails.getDescripcion());
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

