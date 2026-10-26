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
            if (ehStrike(indice)) {
                pontos += 10 + bonusStrike(indice);
                indice += 1;
            } else if (ehSpare(indice)) {
                pontos += 10 + bonusSpare(indice);
                indice += 2;
            } else {
                pontos += somaDoFrame(indice);
                indice += 2;
            }
        }
        return pontos;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        return false;
    }

    private boolean ehStrike(int indice) {
        return jogadas[indice] == 10;
    }

    private boolean ehSpare(int indice) {
        return jogadas[indice] + jogadas[indice + 1] == 10;
    }

    private int bonusStrike(int indice) {
        return jogadas[indice + 1] + jogadas[indice + 2];
    }

    private int bonusSpare(int indice) {
        return jogadas[indice + 2];
    }

    private int somaDoFrame(int indice) {
        return jogadas[indice] + jogadas[indice + 1];
    }
}