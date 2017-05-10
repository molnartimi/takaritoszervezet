// Agent cleaner in project cleanersystem

/* Initial beliefs and rules */

// scenario szerint mikor m�gsem koszos a terem

/* Initial goals */

/* Plans */

// koszosod�s m�rt�k�t random sz�mokkal szimul�ljuk

// mosson fel
+!mossfel(A) : true <- .random(N); !felmos(A,N); .send(manager,tell,szabad); .send(manager,tell,felmosasota(A,0)).
+!felmos(A,N) : N>=0.3 <- .print(A," koszos volt, felmostam.").
+!felmos(A,N) : N<0.3 <- .print(A," nem volt koszos.").

// konferencia eset�n �s minden reggel nincs random
+!mindenkeppmossfel(A) : A=="folyoso" <- .print("Felmostam a folyos�t."); .send(manager,tell,szabad); .send(manager,tell,folyosonfelmosasota(0)).
+!mindenkeppmossfel(A) : true <- .print(A,"-ban konferencia lesz, felmostam."); .send(manager,tell,szabad); .send(manager,tell,felmosasota(A,0)).

// �r�tsen kuk�t
+!uritskukat(A) : true <- .random(N); !viddki(A,N); .send(manager,tell,szabad); .send(manager,tell,kukauritesota(A,0)).
+!viddki(A,N) : N>=0.7 <- .print(A," kuk�ja tele volt, kivittem.").
+!viddki(A,N) : N<0.7 <- .print(A," kuk�ja nem volt tele.").

// takar�tsa ki a mosd�t
+!tisztitsmosdot : true <- .random(N); !mosdotpucol(N); .send(manager,tell,szabad).
+!mosdotpucol(N) : N>=0.5 <- .print("Mosd� koszos volt, kitakar�tottam.").
+!mosdotpucol(N) : N<0.5 <- .print("Mosd� nem volt koszos.").
