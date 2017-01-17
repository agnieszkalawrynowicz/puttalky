# Jak uruchomić PUTtalky?
## NetBeans 8.2
1. *File*->*Open Project*
2. Wskazać katalog z plikiem `pom.xml`
3. *Run*->*Run Project (puttalky)*
4. W oknie *Select Main Class for Execution* wskazać `pl.poznan.put.cs.si.puttalky.PuttalkyMain`
5. Kliknąć *Select Main Class*

Jeśli na liście *Select Main Class* nie pojawiają się żadne elementy,
należy najpierw odpalić budowanie Mavenem, np.:
Prawym na projekt ->*Build with Dependencies*


## Eclipse Neon
1. *File*->*Import*->*Maven*->*Existing Maven Project*
2. *Browse*
2. Wskazać katalog z plikiem `pom.xml`
3. *Finish*
4. Prawym na `puttalky`
5. *Run As* -> *Java Application*
6. Wskazać `PuttalkyMain` z pakietu `pl.poznan.put.cs.si.puttalky`
7. Kliknąć *OK*

## Wiersz poleceń
1. Przejść do katalogu ze źródłami
2. `mvn package`
3. `java -jar target/puttalky-1.0-SNAPSHOT.jar`
