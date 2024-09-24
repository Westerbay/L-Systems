rm -r doc
cd src
javadoc turtle/*.java turtle/space/*.java system/*.java system/rules/*.java main/*.java -d ../doc
firefox ../doc/index.html
