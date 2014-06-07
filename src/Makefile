CC= javac

all: CMTD Projet_main Test

Projet_main : Projet_main.java 
	$(CC) $<

CMTD : CMTD.java
	$(CC) $<

Test : Test.java
	$(CC) $<

CMTD_old : CMTD_old.java
	$(CC) $<

.PHONY: all proper clean test1 test2

proper:
	rm -f *~

clean: proper
	rm -f *.class

test1:
	clear
	java Projet_main 6 0.02 0.01 1

test2:
	clear
	java Projet_main 6 0.02 0.01 2
