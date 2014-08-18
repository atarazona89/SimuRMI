make:
	javac src/Servidores/*.java src/Despachadores/*.java src/Interfaces/*.java src/Interfaces/*.java src/Implement/*.java src/Clientes/*.java
	make impl
	make interf
	make client
	make desp
	make serv

serv:
	mv src/Servidores/*.class bin/Servidores

client:
	mv src/Clientes/*.class bin/Clientes

impl:
	mv src/Implement/*.class bin/Implement

interf:
	mv src/Interfaces/*.class bin/Interfaces

desp:
	mv src/Despachadores/*.class bin/Despachadores


clean:
	rm -r bin
	mkdir bin
	mkdir bin/Clientes
	mkdir bin/Servidores
	mkdir bin/Implement
	mkdir bin/Interfaces
	mkdir bin/Despachadores

commit:
	git add src/*
	git add Makefile
	git add README.md
	git add .classpath
	git add .project
	git commit

push:
	git push origin master

pull:
	git pull origin master
