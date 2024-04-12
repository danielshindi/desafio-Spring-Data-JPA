package me.dio.academia.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data   // lombok - abstrair getters, setters, equals e hashCode
@NoArgsConstructor    // lombok - construtor vazio
@AllArgsConstructor   // lombok - construtor com todos os atributos
@Entity
@Table(name = "tb_alunos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String cpf;

  private LocalDate dataDeNascimento;

  private String bairro;

  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)    // FetchType.LAZY - carregamento da lista da forma LAZY,
                                                            // ou seja, quando solicitarmos pra ver os alunos,
                                                            // carrega todos os atributos, menos essa lista,
                                                            // carregando-a apenas quando for chamada

  @JsonIgnore   // Quando trabalhamos com esses relacionamentos, principalmente o LAZY, apresentam-se algumas exceptions
                // relacionadas ao Json, e uma forma de ignorar isso é a notação @JsonIgnore e @JsonIgnoreProperties
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

}
