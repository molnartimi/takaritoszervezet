// Agent sensor in project cleanersystem

/* Initial beliefs and rules */
// Kezdõidõpont itt is 7:45
// kezdeti beállítások, mikor vannak kevesen/sokan a folyosón
// 1. nap
tele(1,9.75).
tele(1,11.75).
tele(1,13.75).

// 2. nap
tele(2,9.75).
tele(2,11.75).
tele(2,13.75).

// 3. nap
tele(3,9.75).
tele(3,10.25).
tele(3,10.75).
tele(3,11.25).
tele(3,11.75).
tele(3,13.75).
tele(3,14.25).
tele(3,14.75).
tele(3,15.25).
tele(3,15.75).


/* Initial goals */

/* Plans */

+!count(N,T) : not tele(N,T) <- .send(manager,tell,uresfolyoso).

+!count(N,T) : tele(N,T) <- .send(manager,untell,uresfolyoso).
