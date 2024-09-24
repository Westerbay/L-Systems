cd src
javac -d ../build -cp ../lib/junit.jar test/*.java system/*.java system/rules/*.java turtle/space/*.java turtle/*.java
cd ../build
java -cp ./:../lib/junit.jar test.TestClass
