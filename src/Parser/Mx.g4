grammar Mx;

program: (';'|varDef';'|classDef|funcDef)* EOF;

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
type: baseType ('[' ']')*;
constValue: IntConst | StringConst | BoolConst | Null;


varDef: type varSingleDef (',' varSingleDef)*;
varSingleDef: Identifier ('['expr']' | '='expr)?;
funcDef:(type | Void) Identifier parameterList suite;
parameterList: '('(type Identifier(','type Identifier)*)?')';

classDef:Class Identifier '{' (';' | constructDef | varDef | funcDef)* '}';
constructDef:Identifier '('')' suite;


exprList: '('(expr(','expr)*)?')';
value: Identifier | constValue | newItem | Identifier exprList | lambda | This;
newItem:  baseType ('[' expr ']')* ('['']')+ ('[' expr ']')+          #newInvalidArray
         	| baseType ('[' expr ']')+ ('[' ']')*                     #newArray
         	| baseType ('('')')?                                      #newClass;

lambda:'[&]'parameterList'->'suite exprList;

expr
    :   value                                        #valueExpr
    |   '('expr')'                                   #parenExpr
    |   expr op=('++'|'--')                          #suffixExpr
    |   <assoc=right> New newItem                    #newArrayExpr
    |   expr '['expr']'                              #indexExpr
    |   expr '.' Identifier exprList?                #memberExpr
    |   <assoc=right> op=('++'|'--') expr            #prefixExpr
    |   <assoc=right> op=('!'|'~')   expr            #unaryExpr
    |   <assoc=right> op=('+'|'-')   expr            #unaryExpr
    |   expr op=('*'|'/'|'%')   expr                 #binaryExpr
    |   expr op=('+'|'-')   expr                     #binaryExpr
    |   expr op=('<<'|'>>') expr                     #binaryExpr
    |   expr op=('<'|'>'|'<='|'>=') expr             #binaryExpr
    |   expr op=('=='|'!=') expr                     #binaryExpr
    |   expr op='&'  expr                            #binaryExpr
    |   expr op='^'  expr                            #binaryExpr
    |   expr op='|'  expr                            #binaryExpr
    |   expr op='&&' expr                            #binaryExpr
    |   expr op='||' expr                            #binaryExpr
    |   <assoc=right> expr '=' expr                  #assignExpr
    ;
suite:'{' stmt* '}';

stmt
    :   ';'                                       #emptyStmt
    |   varDef';'                                 #varDefStmt
    |   expr ';'                                  #exprStmt
    |   If '('expr')' stmt (Else stmt)?           #ifStmt
    |   While '('expr?')' stmt                    #whileStmt
    |   For '('forInit = expr?';'forCondition = expr?';'forIncrease = expr?')' stmt    #forStmt
    |   Return expr? ';'                          #returnStmt
    |   Break ';'                                 #breakStmt
    |   Continue ';'                              #continueStmt
    |   suite                                     #blockStmt
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