package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadsAcess extends Thread{
	
	int id;
	Semaphore sem;
	int min, max;
	int module = id % 3;
	
	public ThreadsAcess(int id, Semaphore sem) {
		this.id = id;
		this.sem = sem;
	}
	
	private void calc() {
		
		Random rand = new Random();
		verTempo();
		
		try {
			System.out.println("Silêncio! A thread " + id + " está calculando...");
			Thread.sleep(rand.nextInt(min, max));
		} catch (Exception e) { e.printStackTrace(); } 
	}
	
	private void verTempo() {
		switch (module) {
		case 1: 
			min = 200;
			max = 1000;
			break;
		case 2:
			min = 500;
			max = 1500;
			break;
		case 0:
			min = 1000;
			max = 2000;
			break;
		}
	}
	
	private void accessDataBase() {
		try {
			System.out.println("Silêncio! A thread " + id + " está mexendo na foto de perfil do colevati no github");
			if (module == 1) Thread.sleep(1000);
			else Thread.sleep(1500); } 
		catch (Exception e) { e.printStackTrace(); } 
	}
	
	
	@Override
	public void run() {
		calc();
		//-------zona crítica--------//
		try { 
			sem.acquire();
			accessDataBase();
		} 
		catch (Exception e) { e.printStackTrace(); }
		finally { sem.release(); }
	}
}