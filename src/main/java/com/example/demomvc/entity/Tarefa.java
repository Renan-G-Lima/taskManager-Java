package com.example.demomvc.entity;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="TAREFA")
public class Tarefa extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Certificando-se de que a chave primária está mapeada corretamente
    private Long id;

    @NotBlank(message = "O nome da tarefa é obrigatória")
    @Size(min = 2, max = 60, message = "O nome deve conter no mínimo {min} caracteres")
    @Column(name = "nome", nullable = false, unique = true, length = 60)
    private String nome;

    @NotBlank(message = "O nome do responsável pela tarefa é obrigatório")
    @Column(name = "responsavel", nullable = false, length = 60)
    private String responsavel;
    
    @NotNull(message = "O campo de data é obrigatório")
    @Column(name = "data_entrega", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataEntrega;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
