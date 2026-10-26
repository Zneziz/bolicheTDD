package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int totalJogadas = 0;

    private int frameAtual = 1;
    private boolean primeiraJogadaDoFrame = true;
    private int pinosPrimeiroTiro = 0;
    private int jogadasExtrasRestantes = 0;
    private boolean emBonusDeStrikeRolo2 = false;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        if (pinos < 0 || pinos > 10) {
            throw new IllegalArgumentException("Quantidade de pinos inválida: " + pinos);
        }

        if (frameAtual < 10) {
            if (primeiraJogadaDoFrame) {
                if (pinos == 10) {
                    frameAtual++;
                } else {
                    pinosPrimeiroTiro = pinos;
                    primeiraJogadaDoFrame = false;
                }
            } else {
                if (pinosPrimeiroTiro + pinos > 10) {
                    throw new IllegalArgumentException("A soma dos pinos no frame não pode exceder 10.");
                }
                primeiraJogadaDoFrame = true;
                frameAtual++;
            }
        } else {
            if (jogadasExtrasRestantes > 0) {
                if (emBonusDeStrikeRolo2 && pinosPrimeiroTiro < 10 && pinosPrimeiroTiro + pinos > 10) {
                    throw new IllegalArgumentException("Bônus do 10º frame não pode exceder 10 pinos sem novo strike.");
                }
                if (jogadasExtrasRestantes == 2) {
                    pinosPrimeiroTiro = pinos;
                    emBonusDeStrikeRolo2 = true;
                }
                jogadasExtrasRestantes--;
            } else if (primeiraJogadaDoFrame) {
                if (pinos == 10) {
                    jogadasExtrasRestantes = 2;
                    primeiraJogadaDoFrame = false;
                } else {
                    pinosPrimeiroTiro = pinos;
                    primeiraJogadaDoFrame = false;
                }
            } else {
                if (pinosPrimeiroTiro + pinos > 10) {
                    throw new IllegalArgumentException("A soma dos pinos no frame não pode exceder 10.");
                }
                if (pinosPrimeiroTiro + pinos == 10) {
                    jogadasExtrasRestantes = 1;
                }
            }
        }

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