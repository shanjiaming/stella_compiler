grammar Mxstar;

program: declare* EOF;
declare:';'|varDeclare';'|classDeclare|funcDeclare;

Int:'int';
Bool:'bool';
String:'string';
Null:'null';
Void:'void';
If:'if';
Else:'else';
For:'for';
While:'while';
Break:'break';
Continue:'continue';
Return:'return';
New:'new';
Class:'class';
This:'this';

Whitespace:[ \t\n]+ -> skip;
Comment:('//' ~[\n]* | '/*' .*? '*/') -> skip;

BoolConst: 'true' | 'false';
IntConst: '0' | [1-9][0-9]*;
StringConst: '"' (~["\n\\] | '\\' ["n\\])*? '"';

Identifier: [a-zA-Z][a-zA-Z0-9_]* ;

baseType: Bool | Int | String | Identifier ;
typename: typename '[' ']' | baseType;
constType: IntConst | StringConst | BoolConst | Null;


varDeclare: typename varSingleDeclare (',' varSingleDeclare)*;
varSingleDeclare: Identifier ('['expr']' | '='expr)?;
funcDeclare:(typename | Void) Identifier parameterList '{' statement* '}';
parameterList: '('(typename Identifier(','typename Identifier)*)?')';

classDeclare:Class Identifier '{' classIdentity* '}';
classIdentity:';' | constructDeclare | varDeclare | funcDeclare;
constructDeclare:Identifier '('')' '{' statement* '}';



expressionList: '('(expr(','expr)*)?')';
value: Identifier | constType | newExpr | Identifier expressionList | lambda | This;
newExpr: New (baseType | baseType '('expr')'| baseType ('['expr']')+ ('['']')* | Identifier parameterList);
lambda:'[&]'parameterList'->''{'statement*'}' expressionList;

expr
    :   value
    |   '('expr')'
    |   expr ('['expr']')+ ('['']')*
    |   expr '.' expr
    |   <assoc=right> op=('!'|'~')   expr
    |   <assoc=right> op=('+'|'-')   expr
    |   <assoc=right> op=('++'|'--') expr
    |   <assoc=right> expr op=('++'|'--')
    |   expr op=('*'|'/'|'%')   expr
    |   expr op=('+'|'-')   expr
    |   expr op=('<<'|'>>') expr
    |   <assoc=right> expr '=' expr
    |   expr op=('<'|'>'|'<='|'>='|'=='|'!=') expr
    |   expr ('&'|'^'|'|'|'&&'|'||')  expr
    ;


statement
    :   ';'
    |   varDeclare ';'
    |   expr ';'
    |   If '('expr')' statement (Else statement)?
    |   For '('(varDeclare|   (expr(','expr)*)? )';'expr?';'expr?')' statement
    |   While '('expr')' statement
    |   Return expr? |Break|Continue ';'
    |   '{' statement* '}';
