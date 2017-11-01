import java.util.ArrayList;
import processing.core.PGraphics;
//window size 800x600
//0 is black, 1 is white
public class NoteIsolation {

    ArrayList<ArrayList<Integer>> pixels = new ArrayList<ArrayList<Integer>>();
    double staffHeight = 0;
    public static ArrayList<Integer[]> clusters = new ArrayList<Integer[]>();
    public ArrayList<Integer[]> finalClusters = new ArrayList<Integer[]>();


    public NoteIsolation(ArrayList<ArrayList<Integer>> pixels, double staffHeight) {
        this.pixels = pixels;
        this.staffHeight = staffHeight;
        clusters = cluster(pixels, staffHeight);        
        //DRAW THE CLUSTERS

    }

    private ArrayList<Integer[]> cluster(ArrayList<ArrayList<Integer>> pixels, double staffHeight) {
        double correctedStaffHeight = staffHeight / 4.5;

        ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
        boolean isACluster = true;
        double random1 = (Math.random() * correctedStaffHeight) - correctedStaffHeight/2;
        double random2 = (Math.random() * correctedStaffHeight) - correctedStaffHeight/2;

        //i is vertical, j is horizontal
        for (int i = 50; i < pixels.size() - 50; i++) {
            for(int j = 50; j < pixels.get(i).size() - 50; j++) {
                for(int k = 0; k < 1000; k++) {
                    //Make sure the radius is in check
                    do{
                        random1 = ((Math.random() * (correctedStaffHeight - 2)) - correctedStaffHeight/2); // NEED TO FIX
                        random2 = ((Math.random() * (correctedStaffHeight - 2)) - correctedStaffHeight/2); // NEED TO FIX
                    }while(Math.pow(random1, 2) + Math.pow(random2, 2) > Math.pow(staffHeight/2,2));

                    if (pixels.get((int)(i + random1)).get((int)(j + random2)) == 1) {
                        //System.out.println("Point " + (i + random1) + " " + (j + random2));
                        isACluster = false;
                        break;
                    }
                }
                if(isACluster == true) {
                    Integer[] point = new Integer[2];
                    point[0] = i;
                    point[1] = j;
                    temp.add(point);
                }
                isACluster = true;
            }
        }
        return temp;
    }

    public void drawEllipses(PGraphics drawable){
        for(int i = 0; i < clusters.size(); i++) { 
            if(clusters.get(i) != null) {
                drawable.ellipse((float)clusters.get(i)[0],(float)clusters.get(i)[1],10,10);
            }
        }
    }

}

