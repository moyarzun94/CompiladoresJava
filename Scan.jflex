import java_cup.runtime.*;
import java.io.*;

/**
* Tarea de Compiladores
*/
%%

%{
    //Constructor

%}

%class Scan
%unicode
%ignorecase
%cup
%line
%column



SaltoLinea = \r|\n|\r\n
EspacioBlanco = [ \t\f]




//Identificadores
Identificador = [A-Z]

//Colores
Colores =  rojo | verde | azul | amarillo | blanco

//Enteros
Entero = 0 | [1-9][0-9]*


%%

// Palabras Claves

<YYINITIAL> "color"	{ return new Symbol(sym.COLOR); }
<YYINITIAL> "pos"	    { return new Symbol(sym.POS);}
<YYINITIAL> "der"    { return new Symbol(sym.DER);}
<YYINITIAL> "izq"   { return new Symbol(sym.IZQ);}
<YYINITIAL> "arr" { return new Symbol(sym.ARR);}
<YYINITIAL> "aba"     { return new Symbol(sym.ABA);}
<YYINITIAL> "davalor"   { return new Symbol(sym.DAVALOR);}
<YYINITIAL> "termino"   { return new Symbol(sym.TERMINO);}
<YYINITIAL> "editar"   {return new Symbol(sym.EDITAR);}

<YYINITIAL> {Colores}	{ return new Symbol(sym.NCOLOR, new String(yytext())); }
<YYINITIAL> {Identificador} {return new Symbol(sym.ID, new String(yytext()));}



<YYINITIAL>
{


  // Numero
  {Entero}	{ return new Symbol(sym.NUMERO, new String(yytext())); }


  // Simbolos
  "="			{ return new Symbol(sym.EQ); }
  "("			{ return new Symbol(sym.LP); }
  ")"			{ return new Symbol(sym.RP); }
  ","			{ return new Symbol(sym.COMA); }


  // Espacios en Blanco
{EspacioBlanco}		{ /* ignorar */ }

// Enter
  {SaltoLinea}		{ return new Symbol(sym.SALTOLI); }


}


// Error
.|\n			{ throw new Error("Caracter Ilegal <"+yytext()+">"); }
