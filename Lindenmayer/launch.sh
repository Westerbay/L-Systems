clear

cd src
javac -d ../build "main/MainClass.java"

if [ $? -eq 0 ] 
then 

	cd ../build
	
	jar cfe Lindenmayer.jar main.MainClass .
	mv Lindenmayer.jar ../../
	clear
	
	java "main.MainClass" $@
	
fi

