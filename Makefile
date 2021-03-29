# 204071427 302918412
# shoshal2 chamiel
compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt
jar:
	jar cfm ass5game.jar manifest.txt -C bin .
run:
	java -jar ass5game.jar 1 2 3 4
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*.java
bin:
	mkdir bin