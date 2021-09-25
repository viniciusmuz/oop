package practice;

import processing.core.PApplet;
import processing.core.PImage;

import java.time.LocalTime;

public class HelloGUI extends PApplet {

    public void setup() {
        size(300,300);
    }

    public void draw() {
        PImage bg = loadImage("https://www1.nyc.gov/assets/home/images/programs/doh-covid-19-vaccine.jpg", "jpg");
        bg.resize(0, height);
        image(bg, 0, 0);

        int red;
        int green;
        int blue = 0;
        int time = LocalTime.now().getHour();

        if (time > 7 && time < 19) {
            red = 255;
            green = 215;
        }
        else {
            red = 55;
            green = 51;
        }

        fill(red,green,blue);
        ellipse(width/4,height/4,width/5,height/5);
    }
}
