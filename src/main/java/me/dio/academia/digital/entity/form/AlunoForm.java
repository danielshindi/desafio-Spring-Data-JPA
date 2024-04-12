package me.dio.academia.digital.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoForm {

  @NotEmpty(message = "Preencha o campo corretamente.")
  // nome precisa estar no intervalo, caso não esteja, a mensagem é exibida
  @Size(min = 3, max = 70,message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String nome;

  @NotEmpty(message = "Preencha o campo corretamente.")
  @CPF(message = "'{$validatedValue} é inválido!")
  private String cpf;


  @NotNull(message = "Preencha o campo corretamente.")
  // @Past - a data deve estar no passado
  @Past(message = "Data '%{validatedVelue}' é inválida.")
  private LocalDate dataDeNascimento;

  @NotEmpty(message = "Preencha o campo corretamente.")
  @Size(min = 2, max = 50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
  private String bairro;
}
