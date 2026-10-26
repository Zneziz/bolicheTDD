package br.edu.ifsp.boliche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoTest {

    private Jogo jogo;

    private void jogarSequencia(int... pinosPorJogada) {
        for (int pinos : pinosPorJogada) {
            jogo.jogar(pinos);
        }
    }

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

    @Test
    @DisplayName("Um spare: 5, 5, 3 e 17 jogadas de 0 resultam em 16")
    void umSpare() {
        jogo.jogar(5);
        jogo.jogar(5); // spare
        jogo.jogar(3);
        jogarMuitas(17, 0);
        assertEquals(16, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    @DisplayName("Um strike: 10, 3, 4 e 16 jogadas de 0 resultam em 24")
    void umStrike() {
        jogo.jogar(10); // strike
        jogo.jogar(3);
        jogo.jogar(4);
        jogarMuitas(16, 0);
        assertEquals(24, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    @DisplayName("Spare em todos os frames: 21 jogadas de 5 resultam em 150")
    void spareEmTodosOsFrames() {
        jogarMuitas(21, 5);
        assertEquals(150, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    @DisplayName("Jogo perfeito: 12 jogadas de 10 resultam em 300")
    void jogoPerfeito() {
        jogarMuitas(12, 10);
        assertEquals(300, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    @DisplayName("Partida mista resulta em 167")
    void partidaMista() {
        jogarSequencia(
                10,       // Frame 1: strike
                7, 3,     // Frame 2: spare
                9, 0,     // Frame 3: aberto
                10,       // Frame 4: strike
                0, 8,     // Frame 5: aberto
                8, 2,     // Frame 6: spare
                0, 6,     // Frame 7: aberto
                10,       // Frame 8: strike
                10,       // Frame 9: strike
                10, 8, 1  // Frame 10: strike + 2 bônus
        );
        assertEquals(167, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

}
