import java.util.ArrayList;

import processing.core.PGraphics;

public class WhereTheNoteIs {

	ArrayList<ArrayList<Integer>> pixels = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> barlines = new ArrayList<Integer>();
	ArrayList<Character> notes = new ArrayList<Character>();
	double staffHeight = 0;
	

	public WhereTheNoteIs(ArrayList<ArrayList<Integer>> pixels, double staffHeight) {
		this.pixels = pixels;
		this.staffHeight = staffHeight;
		whatNoteIsIt(notes);
	}

	public ArrayList<Integer[]> whereTheBarlineIs() {
		ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
		boolean thisIsABarline = true;
		for(int i = 0; i < pixels.size(); i++) {
			for(int j = 0; j < pixels.get(0).size(); j++) {
				for(int k = 0; k < staffHeight; k++) {
					if(pixels.get(i).get(j + k) == 1) {
						thisIsABarline = false;
					}
				}
				if(thisIsABarline == true) {
					barlines.add(j);
				}
				thisIsABarline = true;
			}
		}
		return temp;
	}

	public void whatNoteIsIt(ArrayList<Character> notes) {
		int smallestValue = 0;
		int theBarlineNumber = 0;
		for(int i = 0; i < NoteIsolation.clusters.size(); i++){
			for (int j = 0; j < barlines.size(); j++) {
				smallestValue = Math.abs(NoteIsolation.clusters.get(i)[1] - barlines.get(j) + (int)(7 * staffHeight/9)); 
				if (Math.abs(NoteIsolation.clusters.get(i)[1] - barlines.get(j)) > smallestValue + 7 * staffHeight/9) {
					smallestValue = Math.abs(NoteIsolation.clusters.get(i)[1] - barlines.get(j) + (int)(7 * staffHeight/9));
					theBarlineNumber = j;
				}
			}
			double difference = barlines.get(theBarlineNumber) - NoteIsolation.clusters.get(i)[1];
			if (difference <= 5 || difference >= -5) {
				notes.add('F');
			}else if(difference <= staffHeight/9 + 5 || difference >= staffHeight/9 - 5) {
				notes.add('E');
			}else if(difference <= 2*staffHeight/9 + 5 || difference >= 2*staffHeight/9 - 5) {
				notes.add('D');
			}else if(difference <= 3*staffHeight/9 + 5 || difference >= 3*staffHeight/9 - 5) {
				notes.add('C');
			}else if(difference <= 4*staffHeight/9 + 5 || difference >= 4*staffHeight/9 - 5) {
				notes.add('B');
			}else if(difference <= 5*staffHeight/9 + 5 || difference >= 5*staffHeight/9 - 5) {
				notes.add('A');
			}else if(difference <= 6*staffHeight/9 + 5 || difference >= 6*staffHeight/9 - 5) {
				notes.add('G');
			}else if(difference <= 7*staffHeight/9 + 5 || difference >= 7*staffHeight/9 - 5) {
				notes.add('F');
			}else if(difference <= 8*staffHeight/9 + 5 || difference >= 8*staffHeight/9 - 5) {
				notes.add('E');
			}else if(difference <= 9*staffHeight/9 + 5 || difference >= 9*staffHeight/9 - 5) {
				notes.add('D');
			}else if(difference <= 10*staffHeight/9 + 5 || difference >= 10*staffHeight/9 - 5) {
				notes.add('C');
			}else if(difference <= 11*staffHeight/9 + 5 || difference >= 11*staffHeight/9 - 5) {
				notes.add('B');
			}else if(difference <= 12*staffHeight/9 + 5 || difference >= 12*staffHeight/9 - 5) {
				notes.add('A');
			}else if(difference <= 13*staffHeight/9 + 5 || difference >= 13*staffHeight/9 - 5) {
				notes.add('G');
			}else if(difference <= 14*staffHeight/9 + 5 || difference >= 14*staffHeight/9 - 5) {
				notes.add('F');
			}else if(difference <= 15*staffHeight/9 + 5 || difference >= 15*staffHeight/9 - 5) {
				notes.add('E');
			}else if(difference <= 16*staffHeight/9 + 5 || difference >= 16*staffHeight/9 - 5) {
				notes.add('D');
			}else if(difference <= 17*staffHeight/9 + 5 || difference >= 17*staffHeight/9 - 5) {
				notes.add('C');
			}else if(difference <= 18*staffHeight/9 + 5 || difference >= 18*staffHeight/9 - 5) {
				notes.add('B');
			}else if(difference <= 19*staffHeight/9 + 5 || difference >= 19*staffHeight/9 - 5) {
				notes.add('A');
			}else if(difference <= 20*staffHeight/9 + 5 || difference >= 20*staffHeight/9 - 5) {
				notes.add('G');
			}else if(difference <= 21*staffHeight/9 + 5 || difference >= 21*staffHeight/9 - 5) {
				notes.add('F');
			}

		}
	}
}

