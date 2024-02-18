//put the classpath righ
export CLASSPATH=$CLASSPATH:<yourpath>/classes

// Compile in this order :
javac -d $CLASSPATH Info_itf.java

javac -d $CLASSPATH Chat.java

javac -d $CLASSPATH ChatImpl.java

javac -d $CLASSPATH ChatServer.java

javac -d $CLASSPATH ChatClient.java

//launch rmiregistry on mandelbrot on port 6090
rmiregistry 6090 &

//launch the server
java -Djava.rmi.server.hostname=localhost  -cp classes ChatServer 6090

//launch the client
java -cp classes/ ChatClient localhost 6090 <name>
