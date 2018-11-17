all:
	cup Parser.cup
	javac DrawFinal.java
	javac -cp  .:java-cup-11b.jar parser.java
	java parser
