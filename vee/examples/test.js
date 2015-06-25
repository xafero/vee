// Prompt and alert example
var text;
var favDrink = window.prompt("What's your favorite cocktail drink?");
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
window.alert(text)

// Open a web-site
window.open("http://www.w3schools.com");

// Confirm and alert example
var txt;
var r = window.confirm("Press a button!");
if (r == true) {
	txt = "You pressed OK!";
} else {
	txt = "You pressed Cancel!";
}
window.alert(txt);
