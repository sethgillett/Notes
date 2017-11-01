import java.util.ArrayList;

import processing.core.*;

public class ImageReader {
	ArrayList<PGraphics> drawLayers;
	
	PImage img;
	SketchWindow p;
	
	//public float imgToWindowScalaar;
	
	ImageReader(SketchWindow p, PImage img) {
		drawLayers = new ArrayList<PGraphics>();
		
		this.p = p;
		this.img = img;
	}
	
	public void fillRed() {
		p.stroke(255,0,0);
		p.fill(255,0,0);
	}
	public void fillRed(PGraphics p) {
		p.stroke(255,0,0);
		p.fill(255,0,0);
	}
	
	int getPixel(int x, int y) {
		// All images below the threshold (0 -> 1) will be black, all those equal to or above will be white
		return img.pixels[y*img.width + x];
		//return p.get(x, y);
	}
	public void contrast(float threshHold)  {
		img.filter(PApplet.THRESHOLD, threshHold);
	}
	
	
	public PGraphics getDrawObject() {
		PGraphics drawLayer = p.createGraphics(img.width,img.height);
		return drawLayer;
	}
	public void addDrawObject(PGraphics layer) {
		drawLayers.add(layer);
	}
	
	
	// PIXEL GETTER
	public ArrayList<ArrayList<Integer>> getAllPixels() {
		// Returns pixels as 0 (black) or 1 (white)
		
		img.loadPixels();
		
		PGraphics drawable = getDrawObject();
		//addDrawObject(drawable);
		
		drawable.beginDraw();
		drawable.loadPixels();
		//img.loadPixels();
		
		ArrayList<ArrayList<Integer>> returnArray;
		
		returnArray = new ArrayList<ArrayList<Integer>>();
		
		for (int x=0; x<img.width; x++) {
			ArrayList<Integer> col = new ArrayList<Integer>();
			for (int y=0; y<img.height; y++) {
				int pixel = (int) p.red(getPixel(x,y));
				int pxColor = p.color((pixel==0)?0:255);
				drawable.pixels[y*img.width + x] = pxColor;
				col.add((pxColor==p.color(255))?1:0);
			}
			returnArray.add(col);
		}
		
		drawable.updatePixels();
		drawable.endDraw();
		
		//img.updatePixels();
		
		return returnArray;
	}
}
