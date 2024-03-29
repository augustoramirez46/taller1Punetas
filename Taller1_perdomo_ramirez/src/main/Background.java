package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Background extends PApplet {

	private PApplet app;
	private PImage[] fondin;
	private PImage[] animacion;
	private int pantalla;

	public Background(PApplet app) {
		this.app = app;
		fondin = new PImage[6];
		fondin[0] = this.app.loadImage("./res/bgf/Tit.jpg");
		fondin[1] = this.app.loadImage("./res/bgf/Instr.jpg");
		fondin[2] = this.app.loadImage("./res/bgf/Gm1.jpg");
		fondin[3] = this.app.loadImage("./res/bgf/Gm2.jpg");
		animacion = new PImage[2];
		animacion[0] = this.app.loadImage("./res/bgf/M0.jpg");
		animacion[1] = this.app.loadImage("./res/bgf/M1.jpg");
		this.pantalla = 0;

	}

	public void pintar() {
		this.app.imageMode(CORNER);
		if (pantalla <= 1 ) {

			this.app.image(fondin[pantalla], 0, 0);
		}
		if (pantalla >= 3) {
			this.app.image(fondin[pantalla-1], 0, 0);
		} 
		
		if(pantalla == 2){
			this.app.image(animacion[app.frameCount / 10 % animacion.length], 0, 0);

		}
	}

	public int getPantalla() {
		return pantalla;
	}

	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}

}
