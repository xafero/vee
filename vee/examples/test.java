import com.xafero.vee.env.annots.Require;
import org.joda.time.*;
import java.awt.image.*;
import org.python.*;
import com.thoughtworks.xstream.*;
import oracle.jdbc.*;

// Prompt and alert example
String text;
String favDrink = window.prompt("What's your favorite cocktail drink?");
switch (favDrink) {
case "Martini":
	text = "Excellent choice! Martini is good for your soul.";
	break;
case "Daiquiri":
	text = "Daiquiri is my favorite too!";
	break;
case "Cosmopolitan":
	text = "Really? Are you sure the Cosmopolitan is your favorite?";
	break;
default:
	text = "I have never heard of that one..";
	break;
}
window.alert(text);

// Open a web-site
window.open("http://www.w3schools.com");

// Confirm and alert example
String txt;
boolean r = window.confirm("Press a button!");
if (r == true) {
	txt = "You pressed OK!";
} else {
	txt = "You pressed Cancel!";
}
window.alert(txt);

// Choose and read file
Object file = fs.chooseFile("bib");
console.log("Reading --> " + file);

txt = fs.readAsText(file);
console.log("Content --> " + txt);

// Choose a color
Object color = media.chooseColor();
console.log(color);

// Capture and open screenshot
RenderedImage image = media.captureScreen(1);
File imgFile = media.write(image, "test.png");
window.open(imgFile);

// Locale and date example
Object locale = time.currentLocale();
console.log("User's locale --> " + locale);

LocalDate date = time.chooseDate();
console.log("He or she chooses --> " + date);
console.log("In islamic calendar --> " + time.convertInto(date, "Islamic"));

LocalDate today = time.today();
console.log("Today is --> " + today);

Days days = time.days(today, date);
console.log("Difference in days --> " + days);
console.log("           in hours --> " + days.toStandardHours());

// Store, retrieve and remove
localStorage.setItem("name", "Franz");
localStorage.setItem("birth", time.today());
console.log(localStorage.getItem("name"));
localStorage.removeItem("name");

// The same, but temporary
sessionStorage.setItem("clickCount", 1);
console.log("Step #" + sessionStorage.getItem("clickCount"));
sessionStorage.setItem("clickCount", Integer.parseInt(sessionStorage.getItem("clickCount").toString()) + 1);
console.log("Step #" + sessionStorage.getItem("clickCount"));

@Require({ /* Download it dynamically */ 
		"http://search.maven.org/remotecontent?filepath="
		+ "org/python/jython-standalone/2.7.0/jython-standalone-2.7.0.jar", 
		
		  /* This one can't be loaded except with a browser */ 
		"file://deps/ojdbc6-11g_R2.jar", 
		
		/* A Maven-driven example */ 
		"mvn://com.thoughtworks.xstream:xstream:1.4.8"})

// Log it!
Version pyv = new Version();
console.log(pyv.getClass());

OracleDriver odrv = new OracleDriver();
int major = odrv.getMajorVersion();
console.log(odrv+", ver="+major);

XStream xml = new XStream();
console.log(xml);

// Just return nothing
return null;
