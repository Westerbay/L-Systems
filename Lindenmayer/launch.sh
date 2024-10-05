clear

mkdir -p build
cd src
javac -d ../build "main/MainClass.java"

if [ $? -eq 0 ] 
then 

	cd ../build
	
	jar cfe L-Systems.jar main.MainClass .
	mv L-Systems.jar ../../
	clear
	
	java "main.MainClass" $@
	
fi

