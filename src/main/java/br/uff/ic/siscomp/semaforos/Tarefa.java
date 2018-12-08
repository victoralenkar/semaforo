package br.uff.ic.siscomp.semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;

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
				System.out.println("[RECURSO ADQUIRIDO PELA ITERAÇÃO " + (i + 1) + "...]");
				// Inicia thread para imprimir Vermelho
				ImpressorVermelho iVermelho = new ImpressorVermelho();
				iVermelho.start();

				// Inicia thread para imprimir Azul
				ImpressorAzul iAzul = new ImpressorAzul();
				iAzul.start();

				// Inicia thread para imprimir Verde
				ImpressorVerde iVerde = new ImpressorVerde(i);
				iVerde.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class ImpressorVermelho extends Thread {
		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				System.out.println("[RECURSO ADQUIRIDO PELA THREAD VERMELHA]");
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				System.out.println("[THREAD VERMELHA DORMINDO POR " + x + " SEGUNDOS...]");
				sleep(x * 1000);

				System.out.println("Vermelho");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// Libera semáforo de impressão de cor
				System.out.println("[RECURSO LIBERADO PELA THREAD VERMELHA]");
				semaforoImpressao.release();
			}
		}
	}

	public class ImpressorAzul extends Thread {
		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				System.out.println("[RECURSO ADQUIRIDO PELA THREAD AZUL]");
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				System.out.println("[THREAD AZUL DORMINDO POR " + x + " SEGUNDOS...]");
				sleep(x * 1000);
				System.out.println("Azul");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("[RECURSO LIBERADO PELA THREAD AZUL]");
				semaforoImpressao.release();
			}
		}
	}

	public class ImpressorVerde extends Thread {
		private int iteracao;

		public ImpressorVerde(int i) {
			this.iteracao = i;
		}

		public void run() {
			try {
				// Adquire semáforo para impressão de cor
				semaforoImpressao.acquire();
				System.out.println("[RECURSO ADQUIRIDO PELA THREAD VERDE]");
				// Sorteia um número aleatório entre 0 e 9
				int x = new Random().nextInt(10);
				// Espera x segundos
				System.out.println("[THREAD VERDE DORMINDO POR " + x + " SEGUNDOS...]");
				sleep(x * 1000);
				System.out.println("Verde");
				System.out.println();
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				// Libera semáforo de impressão de cor
				System.out.println("[RECURSO LIBERADO PELA THREAD VERDE]");
				semaforoImpressao.release();
				//Libera semáforo de controle para próxima iteração
				System.out.println("[RECURSO LIBERADO PELA ITERAÇÃO " + (iteracao + 1) + "...]");
				semaforoControle.release();
				if (iteracao + 1 == total) {
					System.out.println("[" + total + " ITERAÇÕES REALIZADAS COM SUCESSO!]");
				}
			}
		}
	}

	public static void main(String[] args) {
		new Tarefa(Integer.parseInt(args[0]));
	}

}