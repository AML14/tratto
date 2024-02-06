compile:
	tratto/scripts/install_dependencies.sh
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q compile
	cd tratto && mvn -q compile

clean:
	tratto/scripts/install_dependencies.sh
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q clean
	cd tratto && mvn -q clean
# TODO: Deleting `tratto/target/` should really be handled by `tratto/pom.xml`.
	rm -rf tratto/target

test:
	tratto/scripts/install_dependencies.sh
	cd oracle-grammar/MavenVersion/oracle-grammar && mvn -q package
	cd tratto && mvn -q package

tags:
	cd oracle-grammar && etags `find . -name '*.java'`
	cd tratto && etags `find . -name '*.java'`
	etags --include oracle-grammar/TAGS --include tratto/TAGS
