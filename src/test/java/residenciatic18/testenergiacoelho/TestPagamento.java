package residenciatic18.testenergiacoelho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import residenciatic18.pratica8.entities.Fatura;
import residenciatic18.pratica8.entities.Pagamento;
import residenciatic18.pratica8.exceptions.FaturaQuitadaException;

public class TestPagamento {

  @Test
  public void testRegistraPagamentoQuitandoTotalmente() {

    try {

      Fatura fatura = new Fatura("123", 100, 50);

      // Simula um pagamento quitando totalmente a fatura
      Pagamento pagamento = new Pagamento(fatura.getValorTotal());
      fatura.registraPagamento(pagamento);

      // Verifica se a fatura está quitada
      assertTrue(fatura.isQuitado());
      assertEquals(0, fatura.getValorPendente());

    } catch (FaturaQuitadaException e) {
      System.out.println("Erro ao registrar pagamento: " + e.getMessage());
    }
  }

  @Test
  public void testRegistraPagamentoQuitandoParcialmente() {

    Fatura fatura = new Fatura("123", 100, 50);

    // Simula um pagamento quitando parcialmente a fatura
    Pagamento pagamento = new Pagamento(fatura.getValorTotal() / 2);
    fatura.registraPagamento(pagamento);

    // Verifica se a fatura não está quitada
    assertTrue(!fatura.isQuitado());
    // Verifica se o valor pendente foi atualizado corretamente
    assertEquals(fatura.getValorTotal() / 2, fatura.getValorPendente());
  }

  @Test
  public void testRegistraPagamentoExcedendoValor() {

    Fatura fatura = new Fatura("123", 100, 50);

    // Simula um pagamento que excede o valor total da fatura
    Pagamento pagamento = new Pagamento(fatura.getValorTotal() * 2);
    fatura.registraPagamento(pagamento);

    // Verifica se a fatura está quitada
    assertTrue(fatura.isQuitado());
    // Verifica se foi gerado um reembolso
    assertEquals(fatura.getValorTotal(), pagamento.getValor() + fatura.getReembolso().getValor());
  }

  @Test
  public void testRegistraPagamentoFaturaQuitada() {

    Fatura fatura = new Fatura("123", 100, 50);

    // Simula um pagamento quitando totalmente a fatura
    Pagamento pagamento1 = new Pagamento(fatura.getValorTotal());
    fatura.registraPagamento(pagamento1);

    // Tenta registrar outro pagamento em uma fatura já quitada
    Pagamento pagamento2 = new Pagamento(50);

    try {
      fatura.registraPagamento(pagamento2);
      fail("Deveria lançar uma exceção IllegalStateException.");
      
    } catch (IllegalStateException e) {
      assertEquals("A fatura já está quitada!", e.getMessage());
    }
  }
}
