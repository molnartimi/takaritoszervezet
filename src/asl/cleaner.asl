// Agent cleaner in project cleanersystem

/* Initial beliefs and rules */

// scenario szerint mikor mégsem koszos a terem

/* Initial goals */

/* Plans */

// koszosodás mértékét random számokkal szimuláljuk

// mosson fel
+!mossfel(A) : true <- .random(N); !felmos(A,N); .send(manager,tell,szabad); .send(manager,tell,felmosasota(A,0)).
+!felmos(A,N) : N>=0.3 <- .print(A," koszos volt, felmostam.").
+!felmos(A,N) : N<0.3 <- .print(A," nem volt koszos.").

// konferencia esetén és minden reggel nincs random
+!mindenkeppmossfel(A) : A=="folyoso" <- .print("Felmostam a folyosót."); .send(manager,tell,szabad); .send(manager,tell,folyosonfelmosasota(0)).
+!mindenkeppmossfel(A) : true <- .print(A,"-ban konferencia lesz, felmostam."); .send(manager,tell,szabad); .send(manager,tell,felmosasota(A,0)).

// ürítsen kukát
+!uritskukat(A) : true <- .random(N); !viddki(A,N); .send(manager,tell,szabad); .send(manager,tell,kukauritesota(A,0)).
+!viddki(A,N) : N>=0.7 <- .print(A," kukája tele volt, kivittem.").
+!viddki(A,N) : N<0.7 <- .print(A," kukája nem volt tele.").

// takarítsa ki a mosdót
+!tisztitsmosdot : true <- .random(N); !mosdotpucol(N); .send(manager,tell,szabad).
+!mosdotpucol(N) : N>=0.5 <- .print("Mosdó koszos volt, kitakarítottam.").
+!mosdotpucol(N) : N<0.5 <- .print("Mosdó nem volt koszos.").
