// Download it dynamically
env.require("http://search.maven.org/remotecontent?filepath="
		+ "org/python/jython-standalone/2.7.0/jython-standalone-2.7.0.jar");

// This one can't be loaded except with a browser
env.require("file://deps/ojdbc6-11g_R2.jar");

// Log it!
var pyv = env.createObject('org.python.Version');
console.log(pyv);

var odrv = env.createObject('oracle.jdbc.OracleDriver');
console.log(odrv);
