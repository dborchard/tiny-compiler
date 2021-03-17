grammar SimplerLang;

program : statement+;

statement : let | show ;

let : VAR '=' INT ;
show : 'show' (INT | VAR) ;

VAR : ('a'..'z'|'A'..'Z')+ ;
INT : '0'..'9'+ ;
WS : [ \n\t]+ -> skip;
