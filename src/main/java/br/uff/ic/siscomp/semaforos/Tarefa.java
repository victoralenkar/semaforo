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
				semaforoControle.acquire();
				ImpressorVermelho iVermelho = new ImpressorVermelho();
				iVermelho.start();
				ImpressorAzul iAzul = new ImpressorAzul();
				ImpressorVerde iVerde = new ImpressorVerde();
				iAzul.start();
				iVerde.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoControle.release();
			}
			System.out.println(i);
		}
	}


	public static void main(String[] args) {
		new Tarefa(Integer.parseInt(args[0]));
	}

	public class ImpressorVermelho extends Thread {
		public void run() {
			try {
				semaforoImpressao.acquire();
				int x = new Random().nextInt(10);
				sleep(x * 1000);
				System.out.println("Vermelho");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoImpressao.release();
			}
		}
	}

	public class ImpressorAzul extends Thread {
		public void run() {
			try {
				semaforoImpressao.acquire();
				int x = new Random().nextInt(10);
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
				semaforoImpressao.acquire();
				int x = new Random().nextInt(10);
				sleep(x * 1000);
				System.out.println("Verde");
				System.out.println();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforoImpressao.release();
			}
		}
	}

}