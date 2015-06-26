// Choose and read file
var file = fs.chooseFile("bib");
console.log("Reading --> " + file);

var txt = fs.readAsText(file);
console.log("Content --> " + txt);

// Choose a color
var color = media.chooseColor();
console.log(color);
