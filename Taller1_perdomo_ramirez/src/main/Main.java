package main;

import processing.core.PApplet;

public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main("main.Main");
		final Comunicacion ref = new Comunicacion();
        Thread t= new Thread(ref);
        t.start();

	}

	private Logica logica;
	
	public void settings() {
		size(1200, 700);
	}

	public void setup() {
		logica = new Logica(this);
        
	}

	public void draw() {
		logica.pintar();
	}

	public void keyPressed() {
		
			logica.presionado();
		
		
		

	}

}
