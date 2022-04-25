package br.com.zup.edu.nossosistemadereservadesala.sala;

import javax.persistence.*;
import java.time.LocalDateTime;

import static br.com.zup.edu.nossosistemadereservadesala.sala.StatusOcupacao.*;

@Entity
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

    public Long getId() {
        return id;
    }
}
