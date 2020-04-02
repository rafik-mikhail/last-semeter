sentence(s(NP,VP)) --> 
	noun_phrase(N, NP), verb_phrase(N,VP).
noun_phrase(N,np(Det,Noun,Rel)) --> 
	determiner(N, Det), noun(N,Noun), rel_clause(N,Rel).
noun_phrase(singular,np(Name)) --> 
	name(Name).
verb_phrase(N,vp(TV,NP)) --> 
	trans_verb(N,TV), noun_phrase(_,NP).
verb_phrase(N,vp(IV)) --> 
	intrans_verb(N,IV).
rel_clause(N,rel(that,VP)) --> 
	[that],verb_phrase(N,VP).
rel_clause(_,rel(nil)) --> [].
determiner(N,det(W)) --> [W],{is_determiner(W,N)}.
determiner(plural,det(nil)) --> [].

noun(N,n(Root)) --> [W],{is_noun(W,N,Root)}.
name(name(W)) --> [W],{is_name(W)}.
trans_verb(N,tv(Root)) --> [W],{is_trans(W,N,Root)}.
intrans_verb(N,iv(Root)) --> [W],{is_intrans(W,N,Root)}.

is_determiner(every,singular).
is_determiner(all,plural).
is_noun(man,singular,man).
is_noun(men,plural,men).
is_name(mary).
is_trans(likes,singular,like).
is_trans(like,plural,like).
is_intrans(live,plural,live).
