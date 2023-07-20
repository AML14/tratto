compile:
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q compile
	cd tratto && mvn -q compile

clean:
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q clean
	cd tratto && mvn -q clean

test:
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q package
	cd tratto && mvn -q package
