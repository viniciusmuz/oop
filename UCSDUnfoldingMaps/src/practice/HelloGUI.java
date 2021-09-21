package practice;

import processing.core.PApplet;
import processing.core.PImage;

public class HelloGUI extends PApplet {
    public void setup() {
        size(300,300);
    }

    public void draw() {
        PImage bg = loadImage("https://www1.nyc.gov/assets/home/images/programs/doh-covid-19-vaccine.jpg", "jpg");
        bg.resize(0, height);
        image(bg, 0, 0);
        fill(255,215,0);
        ellipse(width/4,height/4,width/5,height/5);
    }
}
