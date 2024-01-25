package residenciatic18;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import residenciatic18.mastermind.Configuracao;

public class TestConfiguracao {

  Configuracao configuracao = new Configuracao();

  @Test
  void testSetAlfabeto() {

    // Caso de teste 1: Alfabeto válido
    String alfabetoValido = "ABCDEFGHIJ";
    configuracao.setAlfabeto(alfabetoValido);
    assertEquals(alfabetoValido, configuracao.getAlfabeto(), "Alfabeto deve ser igual ao valor definido");

    // Caso de teste 2: Alfabeto nulo
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setAlfabeto(null);
    }, "Deveria lançar uma exceção para alfabeto nulo");

    // Caso de teste 3: Alfabeto vazio
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setAlfabeto("");
    }, "Deveria lançar uma exceção para alfabeto vazio");

    // Caso de teste 4: Alfabeto com caracteres duplicados
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setAlfabeto("AAB");
    }, "Deveria lançar uma exceção para alfabeto com caracteres duplicados");

    // Caso de teste 5: Verificar se o alfabeto foi alterado
    String novoAlfabeto = "XYZ";
    configuracao.setAlfabeto(novoAlfabeto);
    assertNotEquals(alfabetoValido, configuracao.getAlfabeto(), "Alfabeto deve ter sido alterado");
    assertEquals(novoAlfabeto, configuracao.getAlfabeto(), "Alfabeto deve ser igual ao novo valor definido");
  }

  @Test
  void testSetTamanhoSenha() {

    // Caso geral: um tamanho de senha válido (entre 1 e 4)
    int tam = 2;
    assertDoesNotThrow(() -> {
      configuracao.setTamanhoSenha(tam);
    });
    assertEquals(tam, configuracao.getTamanhoSenha());

    // Casos de tamanho inválido

    // Caso 1: tentar inserir uma senha de tamanho negativo
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setTamanhoSenha(-1);
    });

    // Caso 1 versão 2: tentar inserir uma senha de tamanho 0
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setTamanhoSenha(0);
    });

    // Caso 2: tentar inserir senha de tamanho maior que o alfabeto (que tem 4 caracteres)
    try {
      configuracao.setTamanhoSenha(5);
      fail("Deveria lançar exceção para senha maior que o alfabeto" );

    } catch (IllegalArgumentException e) {
      System.out.println("Exceção capturada: " + e.getMessage());
    }

    // Verificar se o tamanho da senha não foi alterado após a exceção
    assertNotEquals(5, configuracao.getTamanhoSenha(), "O tamanho da senha não deve ter sido alterado após a exceção");

    // Caso 2 versão 2: inserir com sucesso senha de tamanho igual ao alfabeto (que tem 4 caracteres)
    configuracao.setAlfabeto("1234");
    assertDoesNotThrow(() -> {
      configuracao.setTamanhoSenha(4);
    });
    assertEquals(4, configuracao.getTamanhoSenha());

    // Caso 2 versão 3: tentar inserir senha de tamanho maior que o alfabeto (que agora tem 10 caracteres)
    configuracao.setAlfabeto("1234567890");
    assertThrows(IllegalArgumentException.class, () -> {
      configuracao.setTamanhoSenha(11);
    });
    assertNotEquals(11, configuracao.getTamanhoSenha());
  }

}