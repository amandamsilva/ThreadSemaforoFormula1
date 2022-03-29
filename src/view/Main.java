package view;

import java.util.concurrent.Semaphore;

import controller.Formula1;

public class Main {

	public static void main(String[] args) {

		Semaphore semCarro = new Semaphore(5);
		Semaphore semEscuderia = new Semaphore(1);
		int esc = 1;
		
		for (int idThread = 1; idThread < 15; idThread++, esc+=2) {
			Thread tCarro = new Formula1(semCarro, semEscuderia, idThread, esc);
			tCarro.start();			
		}
	}

}
