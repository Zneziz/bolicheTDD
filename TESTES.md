# Planejamento de Testes — Kata do Boliche

## Partidas de Exemplo (Pontuação e Encerramento)
- [ ] **Nenhum pino (Gutter game):** 20 jogadas de 0 pinos resultam em pontuação 0 e partida encerrada.
- [ ] **Um pino por jogada:** 20 jogadas de 1 pino resultam em pontuação 20 e partida encerrada.
- [ ] **Um spare:** 5, 5, 3 e 17 jogadas de 0 resultam em pontuação 16.
- [ ] **Um strike:** 10, 3, 4 e 16 jogadas de 0 resultam em pontuação 24.
- [ ] **Spare em todos os frames:** 21 jogadas de 5 resultam em pontuação 150 e partida encerrada.
- [ ] **Jogo perfeito:** 12 jogadas de 10 resultam em pontuação 300 e partida encerrada.
- [ ] **Partida mista:** Sequência `10 | 7,3 | 9,0 | 10 | 0,8 | 8,2 | 0,6 | 10 | 10 | 10,8,1` resulta em 167.

## Regras de Validação (V1 a V3) e Estado da Partida
- [ ] **V1 (Pinos negativos):** Jogada com menos de 0 pinos (ex: -1) lança `IllegalArgumentException`.
- [ ] **V1 (Mais de 10 pinos):** Jogada com mais de 10 pinos (ex: 11) lança `IllegalArgumentException`.
- [ ] **V2 (Soma do frame > 10 nos frames 1 a 9):** Segunda jogada do frame que ultrapassa 10 pinos (ex: 7 e depois 4) lança `IllegalArgumentException`.
- [ ] **V2 no 10º frame (Bônus de Strike):** No 10º frame, após um strike (10), a primeira jogada extra ser 7 e a segunda ser 4 é inválido (não houve novo reset de pinos) e lança `IllegalArgumentException`. Mas se a primeira extra for 10 (novo strike), a segunda pode ser até 10.
- [ ] **V3 (Estado em andamento):** `encerrado()` retorna `false` no início e durante a partida (inclusive aguardando jogadas bônus no 10º frame).
- [ ] **V3 (Jogada após fim em frame aberto):** 21ª jogada após 20 jogadas de 0 lança `IllegalStateException`.
- [ ] **V3 (Jogada após fim em spare no 10º frame):** Jogada extra após o bônus do spare no 10º frame lança `IllegalStateException`.
- [ ] **V3 (Jogada após fim em strike no 10º frame):** Jogada extra após os dois bônus do strike no 10º frame lança `IllegalStateException`.