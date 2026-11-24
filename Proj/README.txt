Board Game Night Planner
Descriere:
Aplicatie RESTful (Spring Boot) pentru planificarea si gestionarea serilor de
board games. Datele sunt organizate ca o structura arborescenta (Composite):

GameNight (seara de jocuri)
 └─ Game (jocul: Catan, Uno etc.)
     └─ Round (runda)
         └─ Rule      - regula / nota (text)
         └─ Photo     - imagine (masa, carti)
         └─ Score     - puncte
         └─ Duration  - durata in minute

Functionalitati:
- add: adaugare seara, joc, runda si elemente (reguli, poze, scoruri, durate)
- view: afisarea arborelui complet al unei seri de jocuri
- summary: statistici pe seara (total puncte, durata totala, numar de jocuri
  si runde)

Tehnologii:
- Spring Boot (REST API), Spring Data JPA
- Baza de date H2
- Interfata web simpla (HTML + JavaScript)
- Design patterns: Composite, Visitor, Command, Observer

