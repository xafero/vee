// Download it dynamically
env.require("http://search.maven.org/remotecontent?filepath="
		+ "org/python/jython-standalone/2.7.0/jython-standalone-2.7.0.jar");

// This one can't be loaded except with a browser
env.require("file://deps/ojdbc6-11g_R2.jar");

// Log it!
print('Loaded!');
