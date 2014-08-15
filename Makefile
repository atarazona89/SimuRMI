make:
	make impl
	make interf
	make client
	make serv

serv:
	javac src/Servidores
	mv src/Servidores/*.clas bin/Servidores

client:
	javac src/Clientes
	mv src/Clientes/*.clas bin/Clientes

impl:
	javac src/Implement
	mv src/Implement/*.clas bin/Implement

interf:
	javac src/Interfaces
	mv src/Interfacess/*.clas bin/Interfaces

clean:
	rm -r bin
	mkdir bin
	mkdir bin/Clientes
	mkdir bin/Servidores
	mkdir bin/Implement
	mkdir bin/Interfaces
