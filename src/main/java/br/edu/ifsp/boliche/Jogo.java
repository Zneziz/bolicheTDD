package br.edu.ifsp.boliche;

public class Jogo {

    private int pontos = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        pontos += pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        return pontos;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        return false;
    }
}