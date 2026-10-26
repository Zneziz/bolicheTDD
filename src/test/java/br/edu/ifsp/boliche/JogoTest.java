package br.edu.ifsp.boliche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {

    private Jogo jogo;

    private void jogarMuitas(int quantidade, int pinos) {
        for (int i = 0; i < quantidade; i++) {
            jogo.jogar(pinos);
        }
    }

    @Test
    @DisplayName("Nenhum pino: 20 jogadas de 0 resultam em pontuação 0")
    void nenhumPino() {
        jogarMuitas(20, 0);
        assertEquals(0, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    @DisplayName("Um pino por jogada: 20 jogadas de 1 resultam em pontuação 20")
    void umPinoPorJogada() {
        jogarMuitas(20, 1);
        assertEquals(20, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

}
