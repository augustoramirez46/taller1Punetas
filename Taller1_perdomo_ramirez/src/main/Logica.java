package main;

import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Logica implements Observer {

	PApplet app;
	Background fondo;
	Mano mano1;
	Mano mano2;
	int timer;
	int pantalla;
	public Comunicacion com;

	public Logica(PApplet app) {
		
		com=new Comunicacion();
		com.addObserver(this);
		Thread t= new Thread(com);
		t.start();
		
		
		
		this.app = app;
		this.fondo = new Background(app);
		this.mano1 = new Mano(app, 0);
		this.mano2 = new Mano(app, 1);
		this.timer = 15;
		this.pantalla = 0;

	}

	public void pintar() {

		app.background(255);
		fondo.pintar();
		if (mano1 != null && mano2 != null && pantalla == 2) {
			mano1.pintar();
			mano2.pintar();
			this.checarColision(mano1, mano2);
			this.salidoImbecil(mano1, mano2);
			this.aumentarTimer();
		}
		
		this.fondo.setPantalla(pantalla);


	}

	public void presionado() {
		if (mano1 != null && mano2 != null && pantalla == 2) {
			if (app.keyCode == 37) {
				mano1.avanzarDesgraciado();

			}
			if (app.keyCode == 39) {
				mano2.avanzarDesgraciado();
			}

		}

		if (app.keyCode == 32) {

			this.pantalla++;
			
			
			if (pantalla == 4 || pantalla == 3) {
				this.mano1 = new Mano(app, 0);
				this.mano2 = new Mano(app, 1);
				this.pantalla = 0;
				
				
			}
			
		}
	}

	public void aumentarTimer() {
		this.app.text(timer, 700, 620);
		
			if (app.frameCount % 60 == 0) {

				this.timer--;

			}
		

	}

	public void checarColision(Mano a, Mano b) {
		PVector aa = a.getPos();
		PVector bb = b.getPos();

		if (PApplet.dist(aa.x, aa.y, bb.x, bb.y) < 80) {
			
			
			mano1.recibirMov(mano2.getMov());
			mano2.recibirMov(mano1.getMov());
			
			
			
			System.out.println("Rebote");
			
		}
		
		

	}
	
	public void salidoImbecil(Mano a, Mano b) {
		PVector aa = a.getPos();
		PVector bb = b.getPos();
		
		if (aa.x <= 300 || aa.x >= 900 ||
			aa.y <= 150 || aa.y >= 600) {
			
			pantalla = 3;
			mano1 = null;
			mano2 = null;
		}
		
		if (bb.x <= 300 || bb.x >= 900 ||
				bb.y <= 150 || bb.y >= 600) {
			
			pantalla = 4;

			mano1 = null;
			mano2 = null;
				
	    }
		
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		String mensaje = (String) arg1;
		mensaje = mensaje.trim();
		//System.out.println(mensaje);
		
		System.out.println("aca");

		System.out.println(mensaje);

		if (mensaje.contains("a")) {

			System.out.println("holi");
			if (mano1 != null && mano2 != null && pantalla == 2) {
				mano1.avanzarDesgraciado();
			}
		}

	}

}
