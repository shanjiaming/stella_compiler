grammar Mx;
@header {
    package Parser;
}

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

Whitespace:[ \t\n\r]+ -> skip;
Comment:('//' ~[\n\r]* | '/*' .*? '*/') -> skip;

BoolConst: 'true' | 'false';
IntConst: '0' | [1-9][0-9]*;
StringConst: '"' (~["\n\r\\] | '\\' ["nr\\])*? '"';

Identifier: [a-zA-Z][a-zA-Z0-9_]* ;

baseType: Bool | Int | String | Identifier ;
typename: typename '[' ']' | baseType;
constType: IntConst | StringConst | BoolConst | Null;


varDeclare: typename varSingleDeclare (',' varSingleDeclare)*;
varSingleDeclare: Identifier ('['expression']' | '='expression)?;
funcDeclare:(typename | Void) Identifier parameterList '{' statement* '}';
parameterList: '('(typename Identifier(','typename Identifier)*)?')';

classDeclare:Class Identifier '{' classIdentity* '}';
classIdentity:';' | constructDeclare | varDeclare | funcDeclare;
constructDeclare:Identifier '('')' '{' statement* '}';



expressionList: '('(expression(','expression)*)?')';
value: Identifier | constType | newExpr | Identifier expressionList | lambda | This;
newExpr: New (baseType | baseType '('expression')'| baseType ('['expression']')+ ('['']')* | Identifier parameterList);
lambda:'[&]'parameterList'->''{'statement*'}' expressionList;

expression
    :   value                                           #valueExpr
    |   '('expression')'                                #parenExpr
    |   expression '['expression']'                     #indexExpr
    |   expression '.' expression                       #binaryExpr
    |   <assoc=right> op=('!'|'~')   expression         #unaryExpr
    |   <assoc=right> op=('+'|'-')   expression         #unaryExpr
    |   <assoc=right> op=('++'|'--') expression         #prefixExpr
    |   <assoc=right> expression op=('++'|'--')         #suffixExpr
    |   expression op=('*'|'/'|'%')   expression        #binaryExpr
    |   expression op=('+'|'-')   expression            #binaryExpr
    |   expression op=('<<'|'>>') expression            #binaryExpr
    |   expression op=('<'|'>'|'<='|'>=') expression    #binaryExpr
    |   expression op=('=='|'!=') expression            #binaryExpr
    |   expression '&'  expression                      #binaryExpr
    |   expression '^'  expression                      #binaryExpr
    |   expression '|'  expression                      #binaryExpr
    |   expression '&&' expression                      #binaryExpr
    |   expression '||' expression                      #binaryExpr
    |   <assoc=right> expression '=' expression         #binaryExpr
    ;

suite:'{' statement* '}';



statement
    :   ';'                                             #emptyStmt
    |   typename Identifier '=' expression ';'          #assignStmt
    |   typename Identifier (',' Identifier)* ';'       #listStmt
    |   expression ';'                                  #exprStmt
    |   If '('expression')' statement (Else statement)? #condStmt
    |   While '('expression?')' statement               #whileStmt
    |   For '('forInit';'expression?';'expression?')' statement #forStmt
    |   Return expression? ';'                          #retStmt
    |   Break ';'                                       #breakStmt
    |   Continue ';'                                    #continStmt
    |   suite                                           #blockStmt
    ;


forInit
    :   (expression(','expression)*)?       //maybe empty
    ;

LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
Minus : '-';

And : '&';
Or : '|';
AndAnd : '&&';
OrOr : '||';
Caret : '^';
Not : '!';
Tilde : '~';

Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

Assign : '=';
Equal : '==';
NotEqual : '!=';