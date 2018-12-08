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


	public static void main(String[] args) {
		new Tarefa(Integer.parseInt(args[0]));
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

}