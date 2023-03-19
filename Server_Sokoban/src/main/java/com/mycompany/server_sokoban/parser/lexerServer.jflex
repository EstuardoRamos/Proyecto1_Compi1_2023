package com.mycompany.server_sokoban.parser;

import static com.mycompany.server_sokoban.parser.sym.EOF;
import static com.mycompany.server_sokoban.parser.sym.*;
import java_cup.runtime.*;

/*import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.practica1_compiladores1.analizadores.CounterLexer;
import com.example.practica1_compiladores1.analizadores.Sintactico;
import java.io.Reader;
import java.io.StringReader;*/
%%

/*segunda seccion: configuracion*/


%class CounterLexer
%unicode
%line
%column
%cup
%public

L=[a-zA-Z_]+
D=[0-9]
PUNTO=[.]
COMA = [,]

espacioSimple=[ ]+
tab = [\t]+
salto = [\n]+
rot = [\r]+

espacio={tab}|{espacioSimple}|{salto}|{rot}
comilla = [\"]
decimal = ({D}+{punto}{D}+)
entero = {D}+
hexadecimal=[0-9|a-f]+
cadena = {L}({L}|{D})*
sym = [&!@#$%|<>\?a-zA-Z\\]+



%{
    public String lexeme;

    private Symbol token(int type, Object value) {
        return new Symbol(type, new Token(value.toString(), type, yyline + 1, yycolumn + 1));
    }

    private Symbol token(int type) {
        return new Symbol(type, new Token(null, type, yyline + 1, yycolumn + 1));
    }
%}

%eofval{
    return token(EOF);
%eofval}
%eofclose

%%

{comilla}({espacio}|{espacioSimple})*name({espacio}|{espacioSimple})**{comilla}	{System.out.println("name");return token(NAME, yytext());  }
{comilla}{espacio}*rows{espacio}*{comilla}	{System.out.println("rows");return token(ROWS, yytext()); }
{comilla}{espacio}*cols{espacio}*{comilla}	{System.out.println("cols");return token(COLS, yytext()); }
{comilla}{espacio}*config{espacio}*{comilla}	{System.out.println("config"); return token(CONFIG, yytext());}
{comilla}{espacio}*box_color{espacio}*{comilla}	{System.out.println("boxcolor");return token(BOX_COLOR, yytext());  }
{comilla}{espacio}*box_on_target_color{espacio}*{comilla}	{System.out.println("boxOnTargetcolor");  }
{comilla}{espacio}*target_color{espacio}*{comilla}	{System.out.println("tarColro"); return token(TARGET_COLOR, yytext()); }
{comilla}{espacio}*brick_color{espacio}*{comilla} 	{System.out.println("brickcolor");return token(BRICK_COLOR, yytext()); }
{comilla}{espacio}*hall_color{espacio}*{comilla}	{System.out.println("hall_color"); return token(HALL_COLOR, yytext());}
{comilla}{espacio}*undefined_color{espacio}*{comilla} {System.out.println("undfined_color");return token(UNDEFINED_COLOR, yytext()); }  
{comilla}{espacio}*player_color{espacio}*{comilla} 	{System.out.println("layercolor");return token(PLAYER_COLOR, yytext()); }
{comilla}{espacio}*board{espacio}*{comilla} 		{System.out.println("board");return token(BOARD, yytext()); }
{comilla}{espacio}*posX{espacio}*{comilla}		{System.out.println("x");return token(POSX, yytext());}
{comilla}{espacio}*posY{espacio}*{comilla}		{System.out.println("y");return token(POSY, yytext());}
{comilla}{espacio}*type{espacio}*{comilla}		{System.out.println("type");return token(TYPE, yytext());}
{comilla}{espacio}*targets{espacio}*{comilla}		{System.out.println("targets");return token(TARGETS, yytext()); }
{comilla}{espacio}*player{espacio}*{comilla}		{System.out.println("palyer");return token(PLAYER, yytext());}
{comilla}{espacio}*boxes{espacio}*{comilla}		{System.out.println("boxes");return token(BOXES, yytext());}
{comilla}{espacio}*BRICK{espacio}*{comilla}		{System.out.println("BRICK");return token(BRICK, yytext());}
{comilla}{espacio}*HALL{espacio}*{comilla}		{System.out.println("HALL");return token(HALL, yytext());}
FLOOR                                           {System.out.println("FLOOR");return token(FLOOR, yytext());}
CEIL                                           {System.out.println("CEIL");return token(CEIL, yytext());}
{comilla}{espacio}*#{hexadecimal}{espacio}*{comilla} {System.out.println("HEXADEIMAL");return token(HEXADECIMAL, yytext());}

{espacio}|{tab}|{salto}|{rot} {/*Ignore*/}


"#".* {/*Ignore*/}
"\"" 	{System.out.println("\""); return token(COMILLAS, yytext()); }
"+" 		{System.out.println("PLUS");return token(PLUS, yytext()); }
"-" 		{System.out.println("MINUS");return token(MINUS, yytext());}
"*" 		{System.out.println("TIMES");return token(TIMES, yytext());}
"/" 		{System.out.println("DIVIDE");return token(DIVIDE, yytext()); }
"(" 		{System.out.println("(");return token(LPAREN, yytext());}
")" 		{System.out.println(")");return token(RPAREN, yytext());}
":" 		{System.out.println(":");return token(DOS_PUNTOS, yytext());}
"{" 		{System.out.println("{");return token(LLAVE_ABRE, yytext());}
"}" 		{System.out.println("}");return token(LLAVE_CIERRA, yytext());}
"[" 		{System.out.println("[");return token(COR_ABRE, yytext());}
"]" 		{System.out.println("]");return token(COR_CIERRA, yytext());}

{comilla}{espacio}*{L}({L}|{D}|{espacio})*{comilla} 	{System.out.println("STRING"); return token(STRING, yytext());}
{D}+ 		                                            {System.out.println("INTEGER "+yytext());return token(INTEGER, yytext());}
{D}+{PUNTO}{D}+                                         {System.out.println("DECIMAL");return token(DECIMAL, yytext());}
 {PUNTO} 	                                            {System.out.println("PUNTO");return token(PUNTO, yytext());}
{COMA}                                                  {System.out.println(","); return token(COMA, yytext());  }
 
{sym}
                                                        {
                                                            System.out.println("Finding something else: <" + yytext() + ">");
                                                            return token(SYM, yytext());
                                                        }

[^]				                                        {
                                                            System.out.println("Error: <" + yytext() + ">");
                                                            return token(ERROR, yytext());
                                                        }
