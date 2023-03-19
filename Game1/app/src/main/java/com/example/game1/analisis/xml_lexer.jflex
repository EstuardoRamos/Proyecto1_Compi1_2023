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
"<"	        {System.out.println("<");return token(NAME, yytext()); }
"<name>"	        {System.out.println("name");return token(NAME, yytext()); }
"</name>"	        {System.out.println("nameC");return token(NAME_C, yytext()); }
"<rows>"	        {System.out.println("rows");return token(ROWS, yytext()); }
"<rows>"	        {System.out.println("rows_C");return token(ROWS_C, yytext()); }
"<cols>"	        {System.out.println("cols");return token(COLS, yytext()); }
"</cols>"	        {System.out.println("cols_C");return token(COLS_C, yytext()); }
"<config>"	        {System.out.println("config"); return token(CONFIG, yytext());}
"</config>"	        {System.out.println("config_C_c"); return token(CONFIG_C, yytext());}
"<box_color>"	    {System.out.println("boxcolor");return token(BOX_COLOR, yytext());  }
"</box_color>"	    {System.out.println("boxcolor_C");return token(BOX_COLOR_C, yytext());  }
"<box_on_target_color>"	{System.out.println("boxOnTargetcolor");  }
"<box_on_target_color>"
"</box_on_target_color>"	{System.out.println("boxOnTargetcolor_C");  }
"<target_color>"	{System.out.println("tarColro"); return token(TARGET_COLOR, yytext()); }
"</target_color>"	{System.out.println("tarColro_C"); return token(TARGET_COLOR_C, yytext()); }
"<brick_color>" 	{System.out.println("brickcolor");return token(BRICK_COLOR, yytext()); }
"</brick_color>" 	{System.out.println("brickcolor_C");return token(BRICK_COLOR_C, yytext()); }
"<hall_color>"	    {System.out.println("hall_color"); return token(HALL_COLOR, yytext());}
"</hall_color>"	    {System.out.println("hall_color_C"); return token(HALL_COLOR_C, yytext());}
"<undefined_color>" {System.out.println("undfined_color");return token(UNDEFINED_COLOR, yytext()); }  
"</undefined_color>" {System.out.println("undfined_color_C");return token(UNDEFINED_COLOR_C, yytext()); }  
"<player_color>" 	{System.out.println("layercolor");return token(PLAYER_COLOR, yytext()); }
"</player_color>" 	{System.out.println("layercolor_C");return token(PLAYER_COLOR_C, yytext()); }
"<board>" 		{System.out.println("board");return token(BOARD, yytext()); }
"</board>" 		{System.out.println("board_C");return token(BOARD_C, yytext()); }
"<posX>"		{System.out.println("x");return token(POSX, yytext());}
"</posX>"		{System.out.println("x_C");return token(POSX_C, yytext());}
"<posY>"		{System.out.println("y");return token(POSY, yytext());}
"</posY>"		{System.out.println("y_C");return token(POSY_C, yytext());}
"<type>"		{System.out.println("type");return token(TYPE, yytext());}
"</type>"		{System.out.println("type_C");return token(TYPE_C, yytext());}
"<targets>"		{System.out.println("targets");return token(TARGETS, yytext()); }
"</targets>"		{System.out.println("targets_C");return token(TARGETS_C, yytext()); }
"<player>"		{System.out.println("palyer");return token(PLAYER, yytext());}
"</player>"		{System.out.println("palyer_C");return token(PLAYER_C, yytext());}
"<boxes>"		    {System.out.println("boxes");return token(BOXES, yytext());}
"</boxes>"		    {System.out.println("boxes_C");return token(BOXES_C, yytext());}
BRICK		        {System.out.println("BRICK");return token(BRICK_C, yytext());}
HALL		        {System.out.println("HALL");return token(HALL_C, yytext());}
FLOOR                                           {System.out.println("FLOOR");return token(FLOOR, yytext());}
CEIL                                           {System.out.println("CEIL");return token(CEIL, yytext());}
#{hexadecimal}      {System.out.println("HEXADEIMAL");return token(HEXADECIMAL, yytext());}

{espacio}|{tab}|{salto}|{rot} {/*Ignore*/}


"#".* {/*Ignore*/}


{comilla}{espacio}*{L}({L}|{D}|{espacio})*{comilla} 	{System.out.println("STRING"); return token(STRING, yytext());}
{D}+ 		                                            {System.out.println("INTEGER "+yytext());return token(INTEGER, yytext());}
                                                  {System.out.println(","); return token(COMA, yytext());  }
 
{sym}
                                                        {
                                                            System.out.println("Finding something else: <" + yytext() + ">");
                                                            return token(SYM, yytext());
                                                        }

[^]				                                        {
                                                            System.out.println("Error: <" + yytext() + ">");
                                                            return token(ERROR, yytext());