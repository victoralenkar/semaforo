# Projeto II da Disciplina de Sistemas Computacionais - IC/UFF 2018.2

Desenvolva um programa que lide com três operações básicas que devem ser executadas N vezes. O valor de N é dado como entrada do programa. As operações básicas consistem em imprimir as cores vermelho, azul e verde, exatamente nesta ordem, N vezes. A impressão de cada cor deve ser feita por uma Threads diferente. Cada uma das N vezes que for impressa a sequência de cores, ela deve ser completa e ininterrupta. Ou seja, você deve utilizar um semáforo para garantir a ordem correta da impressão (vermelho, azul e verde) para cada sequência e outro semáforo para garantir que a sequência n só se inicie após a conclusão da squência n-1 (onde n=1...N). Por fim, cada Thread deve sortear um número inteiro aleatório i (que varia entre 0-9) e que será utilizado para adormecer essa Thread por i segundos, após ganhar o direito de execução do semáforo, mas antes de imprimir a cor, cada uma das N vezes.

## Rodando o programa...

`N: Número de repetições desejado`

`java -jar semaforos-v1.0.jar N`

### Código comentado:
~~~~
public class Tarefa {
	int total;
	Semaphore semaforoImpressao;
	Semaphore semaforoControle;

	public Tarefa(int n) {
		this.semaforoImpressao = new Semaphore(1);
		this.semaforoControle = new Semaphore(1);
		this.total = n;
		for (int i = 0; i < this.total; i++) {
			try {
				// Adquire semáforo para controle das N iterações
				semaforoControle.acquire();
				// Inicia thread para imprimir Vermelho
				ImpressorVermelho iVermelho = new ImpressorVermelho();
				iVermelho.start();
				
				// Inicia thread para imprimir Azul
				ImpressorAzul iAzul = new ImpressorAzul();
				iAzul.start();
				
				// Inicia thread para imprimir Verde
				ImpressorVerde iVerde = new ImpressorVerde();
				iVerde.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// Libera semáforo de controle para próxima iteração
				semaforoControle.release();
			}
		}
	}

	public class ImpressorVermelho extends Thread {
		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				sleep(x * 1000);
				System.out.println("Vermelho");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// Libera semáforo de impressão de cor
				semaforoImpressao.release();
			}
		}
	}

	public class ImpressorAzul extends Thread {
		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				sleep(x * 1000);
				System.out.println("Azul");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoImpressao.release();
			}
		}
	}

	public class ImpressorVerde extends Thread {
		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				sleep(x * 1000);
				System.out.println("Verde");
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// Libera semáforo de impressão de cor
				semaforoImpressao.release();
			}
		}
	}
~~~~