package br.com.zup.edu.nossosistemadereservadesala.sala;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
public class ReservarSalaController {

    private final SalaRepository repository;

    public ReservarSalaController(SalaRepository repository) {
        this.repository = repository;
    }

    @PatchMapping("/salas/{id}")
    @Transactional
    public ResponseEntity<?> reservar(@PathVariable Long id) {
        Sala sala = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não cadastrada"));

        sala.reservar();

        repository.save(sala);

        return ResponseEntity.noContent().build();
    }
}
