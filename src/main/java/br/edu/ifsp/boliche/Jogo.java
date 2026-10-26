package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int totalJogadas = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        jogadas[totalJogadas++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int pontos = 0;
        int indice = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (jogadas[indice] + jogadas[indice + 1] == 10) { // Spare
                pontos += 10 + jogadas[indice + 2];
                indice += 2;
            } else {
                pontos += jogadas[indice] + jogadas[indice + 1];
                indice += 2;
            }
        }
        return pontos;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        return false;
    }
}