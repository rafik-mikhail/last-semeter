s(s(X,Y)) --> noun_phrase(X), verb_phrase(Y).
s(s(A,X,Y)) --> noun_phrase(A), relative_phrase(X), verb_phrase(Y).
s(s(X,Y,Z)) --> noun_phrase(X), verb_phrase(Y), sentence_dash(Z).
s(s(A,X,Y,Z)) --> noun_phrase(A), relative_phrase(X), verb_phrase(Y), sentence_dash(Z).

sentence_dash(sentence_dash(X,Y)) --> conjunction_and(X), s(Y).
sentence_dash(sentence_dash(X,Y,Z)) --> conjunction_and(X), s(Y), sentence_dash(Z).
sentence_dash(sentence_dash(X,Y)) --> conjunction_while(X), s(Y).
sentence_dash(sentence_dash(X,Y,Z)) --> conjunction_while(X), s(Y), sentence_dash(Z).


relative_phrase(relative_phrase(X,Y)) --> relative_pronoun_who(X), verb_phrase(Y).


noun_phrase(noun_phrase(X)) --> noun(X).
noun_phrase(noun_phrase(X,Y)) --> determiner(X), noun(Y).
noun_phrase(noun_phrase(X,Y)) --> adjective(X), noun(Y).
noun_phrase(noun_phrase(X,A,Y)) --> determiner(X), adjective(A) ,noun(Y).
noun_phrase(noun_phrase(X,Y)) --> noun(X), prepositional_phrase(Y).
noun_phrase(noun_phrase(X,Y,Z)) --> determiner(X), noun(Y), prepositional_phrase(Z).
noun_phrase(noun_phrase(X,Y,Z)) --> adjective(X), noun(Y), prepositional_phrase(Z).
noun_phrase(noun_phrase(X,A,Y,Z)) --> determiner(X), adjective(A), noun(Y), prepositional_phrase(Z).
noun_phrase(noun_phrase(X,Y)) --> noun(X), noun_phrase_dash(Y).
noun_phrase(noun_phrase(X,Y,Z)) --> determiner(X), noun(Y), noun_phrase_dash(Z).
noun_phrase(noun_phrase(X,Y,Z)) --> adjective(X), noun(Y), noun_phrase_dash(Z).
noun_phrase(noun_phrase(X,A,Y,Z)) --> determiner(X), adjective(A) ,noun(Y), noun_phrase_dash(Z).
noun_phrase(noun_phrase(X,Y,Z)) --> noun(X), prepositional_phrase(Y), noun_phrase_dash(Z).
noun_phrase(noun_phrase(X,Y,Z,A)) --> determiner(X), noun(Y), prepositional_phrase(Z), noun_phrase_dash(A).
noun_phrase(noun_phrase(X,Y,Z,A)) --> adjective(X), noun(Y), prepositional_phrase(Z), noun_phrase_dash(A).
noun_phrase(noun_phrase(X,A,Y,Z,B)) --> determiner(X), adjective(A), noun(Y), prepositional_phrase(Z), noun_phrase_dash(B).

noun_phrase_dash(noun_phrase_dash(A,B)) --> preposition(A), noun_phrase(B).
noun_phrase_dash(noun_phrase_dash(A,B)) --> conjunction_and(A), noun_phrase(B). 
noun_phrase_dash(noun_phrase_dash(A,B,C)) --> preposition(A), noun_phrase(B), noun_phrase_dash(C). 
noun_phrase_dash(noun_phrase_dash(A,B,C)) --> conjunction_and(A), noun_phrase(B), noun_phrase_dash(C). 


verb_phrase(verb_phrase(X,Y)) --> verb(X), noun_phrase(Y).
verb_phrase(verb_phrase(X,Y)) --> verb(X), prepositional_phrase(Y).
verb_phrase(verb_phrase(A,X,Y)) --> adverb(A), verb(X), noun_phrase(Y).
verb_phrase(verb_phrase(A,X,Y)) --> adverb(A), verb(X), prepositional_phrase(Y).

verb_phrase(verb_phrase(X,Y,Z)) --> verb(X), noun_phrase(Y), verb_phrase_dash(Z).
verb_phrase(verb_phrase(X,Y,Z)) --> verb(X), prepositional_phrase(Y), verb_phrase_dash(Z).
verb_phrase(verb_phrase(A,X,Y,Z)) --> adverb(A), verb(X), noun_phrase(Y), verb_phrase_dash(Z).
verb_phrase(verb_phrase(A,X,Y,Z)) --> adverb(A), verb(X), prepositional_phrase(Y), verb_phrase_dash(Z).

verb_phrase_dash(verb_phrase_dash(A,B)) --> conjunction_and(A), verb_phrase(B).
verb_phrase_dash(verb_phrase_dash(A,B,C)) --> conjunction_and(A), verb_phrase(B), verb_phrase_dash(C).
verb_phrase_dash(verb_phrase_dash(A,B,C)) --> relative_pronoun_whom(A), pronoun_they(B), verb_phrase(C).
verb_phrase_dash(verb_phrase_dash(A,B,C,D)) --> relative_pronoun_whom(A), pronoun_they(B), verb_phrase(C), verb_phrase_dash(D).


prepositional_phrase(prepositional_phrase(A,B)) --> preposition(A), noun_phrase(B).
prepositional_phrase(prepositional_phrase(A,B,C)) --> preposition(A), noun_phrase(B), prepositional_phrase(C).

% prepositional_phrase(prepositional_phrase(A,B)) --> preposition(A), verb_phrase(B).


/* determiners */
determiner(determiner(the)) --> [the].
determiner(determiner(a)) --> [a].
determiner(determiner(every)) --> [every].
determiner(determiner(some)) --> [some].
determiner(determiner(many)) --> [many].
determiner(determiner(this)) --> [this].
determiner(determiner(that)) --> [that].
determiner(determiner(these)) --> [these].
determiner(determiner(those)) --> [those].
determiner(determiner(each)) --> [each].


/* prepositions */
preposition(preposition(for)) --> [for].
preposition(preposition(in)) --> [in].
preposition(preposition(after)) --> [after].
preposition(preposition(behind)) --> [behind].
preposition(preposition(before)) --> [before].
preposition(preposition(between)) --> [between].
preposition(preposition(below)) --> [below].
preposition(preposition(beneath)) --> [beneath].
preposition(preposition(inside)) --> [inside].
preposition(preposition(near)) --> [near].


/* adverbs */
adverb(adverb(quickly)) --> [quickly].
adverb(adverb(secretly)) --> [secretly].
adverb(adverb(calmly)) --> [calmly].
adverb(adverb(awkwardly)) --> [awkwardly].
adverb(adverb(brutally)) --> [brutally].
adverb(adverb(beautifully)) --> [beautifully].
adverb(adverb(carefully)) --> [carefully].
adverb(adverb(cheerfully)) --> [cheerfully].
adverb(adverb(happily)) --> [happily].
adverb(adverb(quickly)) --> [forcefully].


/* adjectives */
adjective(adjective(young)) --> [young].
adjective(adjective(old)) --> [old].
adjective(adjective(big)) --> [big].
adjective(adjective(large)) --> [large].
adjective(adjective(empty)) --> [empty].
adjective(adjective(poor)) --> [poor].
adjective(adjective(white)) --> [white].
adjective(adjective(brilliant)) --> [brilliant].
adjective(adjective(talented)) --> [talented].
adjective(adjective(bright)) --> [bright].
adjective(adjective(dark)) --> [dark].
adjective(adjective(nice)) --> [nice].
adjective(adjective(bad)) --> [bad].
adjective(adjective(cool)) --> [cool].
adjective(adjective(small)) --> [small].
adjective(adjective(tight)) --> [tight].
adjective(adjective(baggy)) --> [baggy].
adjective(adjective(long)) --> [long].
adjective(adjective(short)) --> [short].
adjective(adjective(black)) --> [black].
adjective(adjective(young,A)) --> [young], adjective_dash(A).
adjective(adjective(old,A)) --> [old], adjective_dash(A).
adjective(adjective(big,A)) --> [big], adjective_dash(A).
adjective(adjective(large,A)) --> [large], adjective_dash(A).
adjective(adjective(empty,A)) --> [empty], adjective_dash(A).
adjective(adjective(poor,A)) --> [poor], adjective_dash(A).
adjective(adjective(white,A)) --> [white], adjective_dash(A).
adjective(adjective(brilliant,A)) --> [brilliant], adjective_dash(A).
adjective(adjective(talented,A)) --> [talented], adjective_dash(A).
adjective(adjective(bright,A)) --> [bright], adjective_dash(A).
adjective(adjective(dark,A)) --> [dark], adjective_dash(A).
adjective(adjective(nice,A)) --> [nice], adjective_dash(A).
adjective(adjective(bad,A)) --> [bad], adjective_dash(A).
adjective(adjective(cool,A)) --> [cool], adjective_dash(A).
adjective(adjective(small,A)) --> [small], adjective_dash(A).
adjective(adjective(tight,A)) --> [tight], adjective_dash(A).
adjective(adjective(baggy,A)) --> [baggy], adjective_dash(A).
adjective(adjective(long,A)) --> [long], adjective_dash(A).
adjective(adjective(short,A)) --> [short], adjective_dash(A).
adjective(adjective(black,A)) --> [black], adjective_dash(A).

adjective_dash(adjective_dash(A)) --> adjective(A).
adjective_dash(adjective_dash(A,B)) --> adjective(A), adjective_dash(B).

/* nouns */
noun(noun(mouse)) --> [mouse].
noun(noun(cat)) --> [cat].
noun(noun(boy)) --> [boy].
noun(noun(man)) --> [man].
noun(noun(box)) --> [box].
noun(noun(school)) --> [school].
noun(noun(room)) --> [room].
noun(noun(woman)) --> [woman].
noun(noun(envelope)) --> [envelope].
noun(noun(shed)) --> [shed].
noun(noun(building)) --> [building].
noun(noun(tree)) --> [tree].
noun(noun(girl)) --> [girl].
noun(noun(students)) --> [students].
noun(noun(professors)) --> [professors].
noun(noun(lecturers)) --> [lecturers].
noun(noun(scientists)) --> [scientists].
noun(noun(researchers)) --> [researchers].
noun(noun(car)) --> [car].
noun(noun(pen)) --> [pen].


/* verbs */
verb(verb(hated)) --> [hated].
verb(verb(scared)) --> [scared].
verb(verb(worked)) --> [worked].
verb(verb(pushed)) --> [pushed].
verb(verb(stored)) --> [stored].
verb(verb(gave)) --> [gave].
verb(verb(liked)) --> [liked].
verb(verb(climbed)) --> [climbed].
verb(verb(watched)) --> [watched].
verb(verb(admired)) --> [admired].
verb(verb(appreciated)) --> [appreciated].
verb(verb(played)) --> [played].
verb(verb(crashed)) --> [crashed].
verb(verb(drowned)) --> [drowned].
verb(verb(burned)) --> [burned].
verb(verb(hanged)) --> [hanged].
verb(verb(jumped)) --> [jumped].
verb(verb(listened)) --> [listened].
verb(verb(loved)) --> [loved].
verb(verb(helped)) --> [helped].
verb(verb(hated,A)) --> [hated], verb_dash(A).
verb(verb(scared,A)) --> [scared], verb_dash(A).
verb(verb(worked,A)) --> [worked], verb_dash(A).
verb(verb(pushed,A)) --> [pushed], verb_dash(A).
verb(verb(stored,A)) --> [stored], verb_dash(A).
verb(verb(gave,A)) --> [gave], verb_dash(A).
verb(verb(liked,A)) --> [liked], verb_dash(A).
verb(verb(climbed,A)) --> [climbed], verb_dash(A).
verb(verb(watched,A)) --> [watched], verb_dash(A).
verb(verb(admired,A)) --> [admired], verb_dash(A).
verb(verb(appreciated,A)) --> [appreciated], verb_dash(A).
verb(verb(played,A)) --> [played], verb_dash(A).
verb(verb(crashed,A)) --> [crashed], verb_dash(A).
verb(verb(drowned,A)) --> [drowned], verb_dash(A).
verb(verb(burned,A)) --> [burned], verb_dash(A).
verb(verb(hanged,A)) --> [hanged], verb_dash(A).
verb(verb(jumped,A)) --> [jumped], verb_dash(A).
verb(verb(listened,A)) --> [listened], verb_dash(A).
verb(verb(loved,A)) --> [loved], verb_dash(A).
verb(verb(helped,A)) --> [helped], verb_dash(A).

verb_dash(verb_dash(A,B)) --> conjunction_and(A), verb(B).
verb_dash(verb_dash(A,B,C)) --> conjunction_and(A), verb(B), verb_dash(C).


/* pronouns */
relative_pronoun_who(relative_pronoun_who(who)) --> [who].
relative_pronoun_whom(relative_pronoun_whom(whom)) --> [whom].


pronoun_they(pronoun_they(they)) --> [they].


/* conjunctions */
conjunction_and(conjunction_and(and)) --> [and].

conjunction_while(conjunction_while(while)) --> [while].





/* the,young,boy,who,worked,for,the,old,man,pushed,and,stored,a,big,box,in,the,large,empty,room,after,school
the,old,woman,and,the,old,man,gave,the,poor,young,man,whom,they,liked,a,white,envelope,in,the,shed,behind,the,building
every,boy,quickly,climbed,some,big,tree,while,every,girl,secretly,watched,some,boy
some,brilliant,students,and,many,professors,watched,and,admired,talented,lecturers,and,appreciated,bright,scientists,and,researchers */

/* s(S,[the,young,boy,who,worked,for,the,old,man,pushed,and,stored,a,big,box,in,the,large,empty,room,after,school],[]).
s(S,[the,old,woman,and,the,old,man,gave,the,poor,young,man,whom,they,liked,a,white,envelope,in,the,shed,behind,the,building],[]).
s(S,[every,boy,quickly,climbed,some,big,tree,while,every,girl,secretly,watched,some,boy],[]).
s(S,[some,brilliant,students,and,many,professors,watched,and,admired,talented,lecturers,and,appreciated,bright,scientists,and,researchers],[]). */
