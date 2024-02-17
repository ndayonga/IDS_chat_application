//put the classpath righ
export CLASSPATH=$CLASSPATH:<yourpath>/classes

//launch rmiregistry on mandelbrot on port 6090
rmiregistry 6090 &

//launch the server
java -Djava.rmi.server.hostname=localhost  -cp classes HelloServer 6090

//launch the client
java -cp classes/ HelloClient localhost 6090
