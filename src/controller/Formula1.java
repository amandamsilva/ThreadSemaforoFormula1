package controller;

import java.util.concurrent.Semaphore;

public class Formula1 extends Thread {
	
	private Semaphore semCarro;
	private Semaphore semEscuderia;
	private int idThread;
	private int esc;
	private int i = 1;
	private int escuderia;
	
	int[] escuderias = {1, 2, 3, 4, 5, 6, 7};
	
	public Formula1(Semaphore semCarro, Semaphore semEscuderia, int idThread, int esc) {
		this.semCarro = semCarro;
		this.semEscuderia = semEscuderia;
		this.idThread = idThread;
		this.esc = esc;
	}
	
	@Override
	public void run() {
		metodo();
	}

	private void metodo() {
		if (esc % 2 == 0) {
			i++;
			escuderia = escuderias[i];
		}
		System.out.println("Carro #" + idThread + ". Escuderia " + escuderia);
		esc++;
		
		try {
			semEscuderia.acquire();
			pista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semEscuderia.release();
		}
		
	}

	private void pista() {
		try {
			semCarro.acquire();
			System.out.println("Carro #" + idThread + " da escuderia " + esc + " entrou na pista");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semCarro.release();
		}
		
	}

}