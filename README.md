# Projeto II da Disciplina de Sistemas Computacionais - IC/UFF 2018.2

Desenvolva um programa que lide com três operações básicas que devem ser executadas N vezes.
O valor de N é dado como entrada do programa. As operações básicas consistem em imprimir as
cores vermelho, azul e verde, exatamente nesta ordem, N vezes. A impressão de cada cor deve ser
feita por uma Threads diferente. Cada uma das N vezes que for impressa a sequência de cores, ela
deve ser completa e ininterrupta. Ou seja, você deve utilizar um semáforo para garantir a ordem
correta da impressão (vermelho, azul e verde) para cada sequência e outro semáforo para garantir
que a sequência n só se inicie após a conclusão da squência n-1 (onde n=1...N). Por fim, cada
Thread deve sortear um número inteiro aleatório i (que varia entre 0-9) e que será utilizado para
adormecer essa Thread por i segundos, após ganhar o direito de execução do semáforo, mas antes
de imprimir a cor, cada uma das N vezes.

### Rodando o programa...

`N: Número de repetições desejado`

`java -jar semaforos-v1.0.jar N`