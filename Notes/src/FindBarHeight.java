import java.util.ArrayList;

import processing.core.PGraphics;
import processing.core.PVector;

public class FindBarHeight {
	public static int find(SketchWindow p, PVector start) {
		ArrayList<ArrayList<Integer>> pixelsArr = p.ir.getAllPixels();
		
//		PGraphics drawable = p.ir.getDrawObject();
//		p.ir.addDrawObject(drawable);
		
		int [][] pixels = new int [p.ir.img.width][p.ir.img.height];
		for (int x=0; x<pixels.length; x++) {
			for (int y=0; y<pixels[x].length; y++) {
				pixels[x][y] = pixelsArr.get(x).get(y);
			}
		}
		
		int barCount = 0;
		boolean onBar = false;
		
		int x = (int) start.x;
		int y = (int) start.y;
		
		int startY=0, endY=0;
		
		while (barCount < 5) {
			if (pixels[x][y]== 0 && !onBar) {
				p.println("Bar hit: " + y);
				if (barCount == 0) {
					startY = y;
				}
				barCount ++;
				onBar = true;
			} else {
				onBar = false;
			}
			y++;
		}
		
		endY = y;
		
		float height = endY - startY;
//		drawable.beginDraw();
//		
//		for (y=startY; y<endY; y += height) {
//			drawable.stroke(255,0,0);
//			drawable.line(x-5, y, x+5, y);
//		}
//		
//		drawable.endDraw();
		
		System.out.println(height);
		
		return (int) height;
	}
	public static void findRests(SketchWindow p, float barHeight, int clickX, int clickY, int boundX, int boundY) {
		ArrayList<ArrayList<Integer>> pixelsArr = p.ir.getAllPixels();
		
		PGraphics drawable = p.ir.getDrawObject();
		p.ir.addDrawObject(drawable);
		
		int [][] pixels = new int [p.ir.img.width][p.ir.img.height];
		for (int x=0; x<pixels.length; x++) {
			for (int y=0; y<pixels[x].length; y++) {
				pixels[x][y] = pixelsArr.get(x).get(y);
			}
		}
		
		// will use box to check for rests, will find if the box if over amt filled
		float fillAmt = 0.75f;
		
		
		int boxW = (int) (barHeight/5);
		int boxH = (int) (barHeight/2);
		
		drawable.beginDraw();
		
		for (int x=clickX; x<boundX - boxW; x++) {
			for (int y=clickY; y<boundY - boxH; y++) {
				float amt = scan(pixels,x,y,boxW,boxH);
				if (amt > fillAmt) {
					drawable.stroke(0,0,255);
					drawable.fill(0,0,255);
					drawable.rect(x, y, boxW, boxH);
				}
			}
		}
		
		drawable.endDraw();
	}
	public static float scan(int [][] pixels, int xS, int yS, int w, int h) {
		int total = w * h;
		float count = 0;
		
		for (int x=xS; x<xS+w; x++) {
			for (int y=yS; y<yS+h; y++) {
				if (pixels[x][y] == 0) {
					count ++;
				}
			}
		}
		
		return count / total;
	}
}
