package residenciatic18;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import residenciatic18.mastermind.Configuracao;

public class TestConfiguracao {

  @Test
  void testSetAlfabeto() {
    Configuracao configuracao = new Configuracao();
    String alfabeto = "ABCDEFGHIJ";
    configuracao.setAlfabeto(alfabeto);
    assertEquals(alfabeto, configuracao.getAlfabeto());
  }

  @Test
  void testSetTamanhoSenha() {
    Configuracao configuracao = new Configuracao();

    // Caso geral: um tamanho de senha válido (entre 1 e 4)
    int tam = 2;
    try {
      configuracao.setTamanhoSenha(tam);

    } catch (Exception e1) {
      fail("Não deveria gerar exceção aqui");
      e1.printStackTrace();
    }
    assertEquals(tam, configuracao.getTamanhoSenha());

    // Caso 1: tentar inserir uma senha de tamanho negativo
    tam = -1;
    try {
      configuracao.setTamanhoSenha(tam);
      fail("Deveria gerar exceção aqui");

    } catch (Exception e) {
      assertEquals("Senha deve ter ao menos 1 caracter", e.getMessage());
    }

    // o tamanho da senha não pode ter sido aceito
    assertNotEquals(tam, configuracao.getTamanhoSenha());

    // Caso 1 versão 2: tentar inserir uma senha de tamanho 0
    tam = 0;
    try {
      configuracao.setTamanhoSenha(tam);
      fail("Deveria gerar exceção aqui");

    } catch (Exception e) {
      assertEquals("Senha deve ter ao menos 1 caracter", e.getMessage());
    }
    // o tamanho da senha não pode ter sido aceito
    assertNotEquals(tam, configuracao.getTamanhoSenha());

    // Caso 2: tentar inserir senha de tamanho maior que o alfabeto (que tem 4
    // caracteres)
    tam = 5;
    try {
      configuracao.setTamanhoSenha(tam);
      fail("Deveria gerar exceção aqui");

    } catch (Exception e) {
      assertEquals("Senha não pode ser maior que o alfabeto (4 caracteres)", e.getMessage());
    }

    // Caso 2 versão 2: inserir com sucesso senha de tamanho igual ao alfabeto (que
    // tem 4 caracteres)
    tam = 4;
    try {
      configuracao.setAlfabeto("1234");
      configuracao.setTamanhoSenha(tam);

    } catch (Exception e) {
      fail("Não era para gerar exceção aqui");
    }
    // o tamanho da senha precisa ter sido aceito
    assertEquals(tam, configuracao.getTamanhoSenha());

    // Caso 2 versão 3: tentar inserir senha de tamanho maior que o alfabeto (que
    // agora tem 10 caracteres)
    try {
      configuracao.setAlfabeto("1234567890");

    } catch (Exception e1) {
      e1.printStackTrace();
    }

    tam = 11;
    try {
      configuracao.setTamanhoSenha(tam);
      fail("Deveria gerar exceção aqui");

    } catch (Exception e) {
      assertEquals("Senha não pode ser maior que o alfabeto (10 caracteres)", e.getMessage());
    }
    // o tamanho da senha não pode ter sido aceito
    assertNotEquals(tam, configuracao.getTamanhoSenha());
  }
}
