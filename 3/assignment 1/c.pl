sentence --> noun_phrase(N), verb_phrase(N).
noun_phrase(N) --> determiner(N), noun(N).
verb_phrase(N) --> verb(N), noun_phrase(_).
verb_phrase(N) --> verb(N), sentence.

determiner(singular) --> [a].
determiner(_)        --> [the].
determiner(plural)   --> [].
noun(N) --> [X], { morph(noun(N),X) }.
verb(N) --> [X], { morph(verb(N),X) }.

morph(noun(singular),dog).       % Singular nouns
morph(noun(singular),cat).
morph(noun(singular),boy).
morph(noun(singular),girl).
morph(noun(singular),child).

morph(noun(plural),children).    % Irregular plural nouns

morph(noun(plural),X) :-         % Rule for regular plural nouns
     remove_s(X,Y),
     morph(noun(singular),Y).

morph(verb(plural),chase).       % Plural verbs
morph(verb(plural),see).
morph(verb(plural),say).
morph(verb(plural),believe).

morph(verb(singular),X) :-       % Rule for singular verbs
     remove_s(X,Y),
     morph(verb(plural),Y).

remove_s(X,X1) :-
     name(X,XList),
     remove_s_list(XList,X1List),
     name(X1,X1List).

remove_s_list("s",[]).

remove_s_list([Head|Tail],[Head|NewTail]) :-
     remove_s_list(Tail,NewTail).
