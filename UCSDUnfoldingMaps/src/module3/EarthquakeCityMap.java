package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Vinicius Xavier
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Threshold, color and radius for moderate earthquakes.
	public static final float THRESHOLD_MODERATE = 5;
	public final int COLOR_MODERATE = color(255, 0, 0);
	public final int RADIUS_MODERATE = 20;

	// Threshold, color and radius for light earthquakes.
	public static final float THRESHOLD_LIGHT = 4;
	public final int COLOR_LIGHT = color(255, 255, 0);
	public final int RADIUS_LIGHT = 15;

	// Color and radius for minor earthquakes.
	public final int COLOR_MINOR = color(220, 220, 255);
	public final int RADIUS_MINOR = 10;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(900, 700, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 0, 120, 900, 580, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 0, 120, 900, 580, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // Creates a marker for each earthquake in earthquakes.
	    for (PointFeature earthquake : earthquakes) {
			markers.add(this.createMarker(earthquake));
		}
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);
	}
		
	/* Takes in an earthquake feature and returns a SimplePointMarker for that earthquake.
	 */
	private SimplePointMarker createMarker(PointFeature feature)
	{
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());

		// Add style to the created marker, according to its intensity.
	    if (mag >= THRESHOLD_MODERATE) {
			marker.setColor(COLOR_MODERATE);
			marker.setRadius(RADIUS_MODERATE);
		}
		else if (mag >= THRESHOLD_LIGHT) {
			marker.setColor(COLOR_LIGHT);
			marker.setRadius(RADIUS_LIGHT);
		}
		else {
			marker.setColor(COLOR_MINOR);
			marker.setRadius(RADIUS_MINOR);
		}
	    
	    // Finally return the marker
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// Draw key in the GUI.
	private void addKey() 
	{
		// Draws the rectangle.
		fill(50, 50, 50);
		rect(10, 10, width - 20, 100, 20);

		// Draws the markers.
		int yMarkerPosition = 80;

		fill(COLOR_MODERATE);
		int xModeratePosition = 25;
		ellipse(xModeratePosition, yMarkerPosition, RADIUS_MODERATE, RADIUS_MODERATE);

		fill(COLOR_LIGHT);
		float xLightPosition = (width / 2) - 100;
		ellipse(xLightPosition, yMarkerPosition, RADIUS_LIGHT, RADIUS_LIGHT);

		fill(COLOR_MINOR);
		float xMinorPosition = width - 175;
		ellipse(xMinorPosition, yMarkerPosition, RADIUS_MINOR, RADIUS_MINOR);

		// Draws the text.
		fill(color(255,255,255));
		textSize(14);
		text("5.0+ magnitude", xModeratePosition + 25, yMarkerPosition + 5);
		text("4.0+ magnitude", xLightPosition + 25, yMarkerPosition + 5);
		text("Below 4.0 magnitude", xMinorPosition + 25, yMarkerPosition + 5);

		// Draws the title.
		textSize(28);
		text("Earthquake Key", (width / 2) - 110, 40);
	}
}
