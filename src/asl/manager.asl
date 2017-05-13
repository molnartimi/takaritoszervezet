// Agent manager2 in project cleanersystem

/* Initial beliefs and rules */

szabad[source(tak_Mari),source(tak_Bea),source(tak_Lali),source(tak_Izolda),source(tak_Zsombor)].
ebedszunet(13.25).

felmosasota("IB28",0).
felmosasota("IB27",0).
felmosasota("IB26",0).

kukauritesota("IB28",0).
kukauritesota("IB27",0).
kukauritesota("IB26",0).

folyosonfelmosasota(0).
// �rarendi hiedelmek, termek, id�j�r�s, koszosod�s m�rt�ke

//-----------------------------------------
// 1. nap
// t�z� naps�t�s
oravan(1,"IB28",8.25,300). //300 hallgat�
oravan(1,"IB28",10.25,100). //100 hallgat�

oravan(1,"IB27",10.25,20). //20 hallgat�
oravan(1,"IB27",14.25,30). //30 hallgat�

oravan(1,"IB26",12.25,150). //150 hallgat�
//-----------------------------------------

//-----------------------------------------
// 2. nap
esosido(2).
oravan(2,"IB28",8.25,400). //400 hallgat�
oravan(2,"IB28",10.25,400). //400 hallgat�
oravan(2,"IB28",14.25,600). //600 hallgat�

oravan(2,"IB27",10.25,150). //150 hallgat�

oravan(2,"IB26",8.25,15). //15 hallgat�
oravan(2,"IB26",12.25,60). //60 hallgat�
//-----------------------------------------

//-----------------------------------------
// 3. nap
esosido(3).
oravan(3,"IB28",8.25,285). //285 hallgat�
konferencia(3,"IB28",12.25).

oravan(3,"IB27",10.25,85). //85 hallgat�

oravan(3,"IB26",10.25,30). //30 hallgat�
//-----------------------------------------

/* Initial goals */

!start.

/* Plans */

// h�romnegyed 8-kor kezdj�k a sz�m�t�st
+!start : true <- .print("1. NAP"); 
				  !work(1,7.75).

+!work(N,T) : T<16 <- .print(T);
			  idoKiiras(N,T);
			  .send(sensor,achieve,count(N,T));
			  !koszosodas(N,T); 
			  !feladatotad(N,T); 
			  .wait(1000); 
			  !work(N,T+0.5).
			  
+!work(N,T) : T>16 & N<3 <- .print("Sz�p munka, viszl�t holnap!"); 
						 	napvege;
							.wait(3000); 
							.print(N+1,". NAP");
							!work(N+1,7.75).
							
+!work(N,T) : N==3 <- .print("Kellemes h�tv�g�t mindenkinek!");
					  hetvege.

+!feladatotad(N,T) : ebedszunet(T) <- .print("Eb�dsz�net van, nem kell dolgozni.");
									  ebed.
+!feladatotad(N,T) : not ebedszunet(T) <- !felmosas(N,T);
										  !kukaurites(N,T);
										  !mosdotisztitas(T).
		
// 1. FELADAT: felmos�s								
+!felmosas(N,T) : true <- A="IB26"; ?felmosasota(A,X); !teremfelmosas(N,A,T,X);
						  B="IB27"; ?felmosasota(B,Y); !teremfelmosas(N,B,T,Y);
						  C="IB28"; ?felmosasota(C,Z); !teremfelmosas(N,C,T,Z);
						  ?folyosonfelmosasota(F); !folyosofelmosas(F,T).

+!teremfelmosas(N,A,T,X) : konferencia(N,A,T) | konferencia(N,A,T-0.5) | konferencia(N,A,T-1) <- .print(A,"-ban most konferencia van.");
																								 konferencia(A).				
+!teremfelmosas(N,A,T,X) : oravan(N,A,T,Q) | oravan(N,A,T-0.5,Q) | oravan(N,A,T-1,Q) <- .print(A,"-ban most �ra van."); 
																						oravan(A,Q).
+!teremfelmosas(N,A,T,X) : X>=200 <- -felmosasota(A,X);
									 !kivalaszt(E); 
									 felmosat(E,A);
									 .send(E,achieve,mossfel(A)).
+!teremfelmosas(N,A,T,X) : konferencia(N,A,T+0.5) <- -felmosasota(A,X);
													 !kivalaszt(E);
													 felmosat(E,A);
													 .send(E,achieve,mindenkeppmossfel(A)).
+!teremfelmosas(N,A,T,X) : true.

+!folyosofelmosas(F,T) : uresfolyoso & F>=200 <- -folyosonfelmosasota(F);
												 !kivalaszt(E);
												 felmosat(E,"folyoso");
												 .send(E,achieve,mossfel("folyoso")).	// TODO sorsolhatunk ide t�bb embert, ha akarunk
+!folyosofelmosas(F,T) : T==7.75 <- -folyosonfelmosasota(F);
									!kivalaszt(E); 
									felmosat(E,"folyoso");
									.send(E,achieve,mindenkeppmossfel("folyoso")).
+!folyosofelmosas(F,T) : true.

// 2. FELADAT: kuka�r�t�s
// TODO csin�lhatn� az, aki am�gy is bement a terembe?
+!kukaurites(N,T) : true <- A="IB26"; ?kukauritesota(A,X); !kukaurites(N,A,T,X);
						    B="IB27"; ?kukauritesota(B,Y); !kukaurites(N,B,T,Y);
						    C="IB28"; ?kukauritesota(C,Z); !kukaurites(N,C,T,Z).
						    //!kukauritesfolyoson(T).  // kuka�r�t�s folyos�n f�ggv�nye annak, hogy sokan vannak-e ott?
						    						 // PL menj�nk minden p�ros �ra 15-kor
						
+!kukaurites(N,A,T,X) : not oravan(N,A,T,Q) & not oravan(N,A,T-0.5,Q) & not oravan(N,A,T-1,Q) & X>=300 &
						not konferencia(N,A,T) & not konferencia(N,A,T-0.5) & not konferencia(N,A,T-1) <- !kivalaszt(E); 
																										  -kukauritesota(A,X);
																										  kukaturit(E,A);
																									  	  .send(E,achieve,uritskukat(A)).
+!kukaurites(N,A,T,X) : true.

/* +!kukauritesfolyoson(T) : (T div 1 mod 2)==0 & T-(T div 1)==0.25 <- !kivalaszt(E);
 																	kukaturit(E,"folyoso");
																	.send(E,achieve,uritskukat("folyoso")).
+!kukauritesfolyoson(T) : true.
*/

// 3. FELADAT: mosd�tiszt�t�s
// PL : menj�nk minden p�ros �ra 45-kor						
//+!mosdotisztitas(T) : (T div 1 mod 2)==1 & T-(T div 1)==0.75 <- .print("�rak�zi sz�netben nem takar�tunk mosd�t.").
+!mosdotisztitas(T) : ((T div 1 mod 2)==1 & T-(T div 1)==0.25) |
					  ((T div 1 mod 2)==0 & T-(T div 1)==0). // !!! p�ratalan �ra 45-kor, �s p�ros �rakor nem mehetek mosd�t takar�tani, akkor sz�net van az �r�k k�z�tt
+!mosdotisztitas(T) : true <- !kivalaszt(E);
							  mosdottisztit(E);
							  .send(E,achieve,tisztitsmosdot).


// ki csin�lja meg a feladatot?
+!kivalaszt(A) : true <- .random(N); R=((N*5+1) div 1); !ember(R,A).

+!ember(R,tak_Bea) : R==1 & szabad[source(tak_Bea)] <- -szabad[source(tak_Bea)].
+!ember(R,tak_Izolda) : R==2 & szabad[source(tak_Izolda)] <- -szabad[source(tak_Izolda)].
+!ember(R,tak_Lali) : R==3 & szabad[source(tak_Lali)] <- -szabad[source(tak_Lali)].
+!ember(R,tak_Mari) : R==4 & szabad[source(tak_Mari)] <- -szabad[source(tak_Mari)].
+!ember(R,tak_Zsombor) : R==5 & szabad[source(tak_Zsombor)]<- -szabad[source(tak_Zsombor)].
+!ember(R,A) : true <- !kivalaszt(A).

// koszosod�s szimul�l�sa termekben
+!koszosodas(N,T) : true <- A="IB28"; !koszosodik(N,A,T);
							B="IB27"; !koszosodik(N,B,T);
							C="IB26"; !koszosodik(N,C,T).
								   
+!koszosodik(N,A,T) : oravan(N,A,T,B) <- ?oravan(N,A,T,B);
									     ?felmosasota(A,X); -felmosasota(A,X); +felmosasota(A,X+B); koszosodik(A,X+B);
									     ?kukauritesota(A,Y); -kukauritesota(A,Y); +kukauritesota(A,Y+B); kukatelik(A,Y+B);
									     ?folyosonfelmosasota(F); !folyosokoszosodik(N,F,B).

+!koszosodik(N,A,T) : not oravan(N,A,T,B).

+!folyosokoszosodik(N,F,B) : esosido(N) <- -folyosonfelmosasota(F); +folyosonfelmosasota(F+2*B); koszosodik("folyoso",F+2*B).
+!folyosokoszosodik(N,F,B) : not esosido(N) <- -folyosonfelmosasota(F); +folyosonfelmosasota(F+B); koszosodik("folyoso",F+B).
+!folyosokoszosodik(N,F,B) : true.
								   

