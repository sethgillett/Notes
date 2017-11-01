import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class SketchWindow extends PApplet {
	
	public StateMachine states;
	PImage img;
	ImageReader ir;
	String musicName;
	
	float mX, mY;
	
	public static void main(String [] args) {
		PApplet.main("SketchWindow");
	}
	public void settings() {
		musicName = "buns.jpg";
		img = loadImage(musicName);
		if (img.width < 600 && img.height < 600) {
			size(img.width, img.height);
		}
		else {
			size((int) ((img.width/(float) img.height) * 600f), 600);
		}
	}
	
	public void setup() {
		states = new StateMachine(this);
		
		ir = new ImageReader(this, img);
		
		ir.contrast(0.81f);
		
		//SoundPitch.playSound("C D# E F G# A B");
		
		// STATES *************************************************************************
		
		// select 1 => line draw => select 2 => display
		Runnable mouseLineDraw = () -> {
			ir.fillRed();
			//PVector pos = states.getOutput("s1");
			//ir.sDraw.ellipse(pos.x, pos.y, 20, 20);
			ellipse(mouseX, mouseY, 3, 3);
			rect(mouseX, 0, 1, ir.img.height);
			line(0, mouseY, ir.img.width, mouseY);
			
			stroke(0,0,255);
			fill(255);
			ellipse(mX,mY,3,3);
			//ir.sDraw.ellipse(mX, mY, 20, 20);
		};
		
		Runnable mouseSelect = () -> {
			mouseLineDraw.run();
			if (mousePressed) {
				states.output("waitingRelease", true);
				states.output("selection", new PVector(mX, mY));
			} else if (!mousePressed && states.hasOutput("waitingRelease") && (boolean) states.getOutput("waitingRelease")) {
				states.setState("mouseSelect2");
			}
		};
		Runnable mouseSelect2 = () -> {
			mouseLineDraw.run();
			if (mousePressed) {
				states.output("waitingRelease2", true);
				states.output("selection2", new PVector(mX, mY));
			} else if (!mousePressed && states.hasOutput("waitingRelease2") && (boolean) states.getOutput("waitingRelease2")) {
				states.setState("calc");
			}
		};
		Runnable calcNotes = () -> {
			ir.getAllPixels();
			
			PVector m = states.getOutput("selection");
			PVector mFinal = states.getOutput("selection2");
			
			float barHeight = FindBarHeight.find(this, m);
			//float barHeight = 72;
			
			NoteIsolation ni = new NoteIsolation(ir.getAllPixels(), barHeight); //m.x, m.y, thingy	
			//WhereTheNoteIs wtni = new WhereTheNoteIs(ir.getAllPixels(), barHeight);
			PGraphics drawable = ir.getDrawObject();
			ir.addDrawObject(drawable);
			drawable.beginDraw();
			ir.fillRed(drawable);
			drawable.stroke(255,0,0);
			drawable.fill(255,0,0);
			ni.drawEllipses(drawable);
			println("DONE!!!!");
			drawable.endDraw();
			
			if (!musicName.equals("twinkle.png")) {
				FindBarHeight.findRests(this, barHeight,(int) m.x, (int) m.y, (int) mFinal.x, (int) mFinal.y);
			}
			
			states.setState("default");
		};
		
		states.state("mouseSelect", mouseSelect);
		states.state("mouseSelect2", mouseSelect2);
		states.state("calc", calcNotes);
		
		states.setState("mouseSelect");
		// STATES *************************************************************************
	}
	
	public void displaySheet() {
		image(ir.img, 0, 0, width, height);
		for (PGraphics drawLayer : ir.drawLayers) {
			image(drawLayer, 0, 0, width, height);
		}
	}
	
	public void draw() {
		float xScalar = ir.img.width / width;
		float yScalar = ir.img.height / height;
		
		mX = mouseX * xScalar;
		mY = mouseY * yScalar;
		
		states.runState();
	}
}