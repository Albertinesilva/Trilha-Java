package residenciatic18.testenergiacoelho;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import residenciatic18.pratica8.entities.Fatura;

public class TestFatura {

  @Test
  public void testRegistraLeitura() {
  
    Fatura fatura = new Fatura("123", 100, 50);

    // Verifica se a fatura está correta antes do registro
    assertEquals(50, fatura.getPenultimaLeitura());
    assertEquals(100, fatura.getUltimaLeitura());

    // Simula o registro de uma nova leitura
    try {
      fatura.registraLeitura(120);

      // Verifica se a fatura foi atualizada corretamente
      assertEquals(100, fatura.getPenultimaLeitura());
      assertEquals(120, fatura.getUltimaLeitura());

    } catch (IllegalArgumentException e) {
      fail("Não deveria lançar exceção IllegalArgumentException: " + e.getMessage());
    }
  }

  @Test
  public void testRegistraLeituraComNovaLeituraMenor() {
   
    Fatura fatura = new Fatura("123", 100, 50);

    // Verifique se a fatura está correta antes do registro
    assertEquals(50, fatura.getPenultimaLeitura());
    assertEquals(100, fatura.getUltimaLeitura());

    // Simula o registro de uma nova leitura menor (o que deve lançar uma exceção)
    try {
      fatura.registraLeitura(80);
      fail("Deveria lançar uma exceção IllegalArgumentException.");

    } catch (IllegalArgumentException e) {
      assertEquals("A nova leitura deve ser maior que a última leitura.", e.getMessage());
    }

    // Verifique se a fatura não foi alterada após a exceção
    assertEquals(50, fatura.getPenultimaLeitura());
    assertEquals(100, fatura.getUltimaLeitura());
  }
  
}