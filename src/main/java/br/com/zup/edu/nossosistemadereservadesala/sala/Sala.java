package br.com.zup.edu.nossosistemadereservadesala.sala;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.zup.edu.nossosistemadereservadesala.sala.StatusOcupacao.*;

@Entity
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOcupacao status = LIVRE;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();


    public Sala(String nome) {
        this.nome = nome;
    }

    @Deprecated
    /**
     * @deprecated construtor para uso
     */
    public Sala() {
    }

    public boolean isOcupada() {
        return this.status == OCUPADO;
    }

    public Long getId() {
        return id;
    }

    public void reservar() {
        if (this.isOcupada()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "A sala está ocupada");
        }

        this.status = OCUPADO;
        this.atualizadoEm = LocalDateTime.now();
    }
}
