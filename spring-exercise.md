# Sistema di Gestione Eventi - Traccia Esercizio Spring Boot

## Obiettivo
Sviluppare un'applicazione REST per la gestione di eventi e prenotazioni utilizzando Spring Boot 3.x e Java 17.

## Requisiti Funzionali

### Gestione Eventi
- CRUD completo per gli eventi
- Ogni evento deve avere: titolo, descrizione, data, luogo, capienza massima, prezzo
- Possibilità di cercare eventi per data e/o luogo
- Validazione dei dati in input

### Gestione Prenotazioni
- Gli utenti registrati possono prenotare posti per gli eventi
- Controllo della disponibilità dei posti
- Generazione di un codice univoco per ogni prenotazione
- Cancellazione prenotazione con politiche di rimborso basate sulla data

### Gestione Utenti
- Registrazione e autenticazione utenti
- Ruoli differenziati (ADMIN, USER)
- Profilo utente con storico prenotazioni

## Requisiti Tecnici

### Spring Boot Features da Utilizzare
1. **Spring Security**
   - JWT Authentication
   - Role-based authorization

2. **Spring Data JPA**
   - Entity relationships
   - Custom queries
   - Paginazione e ordinamento

3. **Spring Validation**
   - Bean validation
   - Custom validators

4. **Exception Handling**
   - Global exception handler
   - Custom exceptions

5. **Testing**
   - Unit test per i service
   - Integration test per i controller

### Database
- H2 per development
- Implementare migrations con Flyway

### API Documentation
- OpenAPI (Swagger)

### Bonus Points
- Implementazione caching
- Rate limiting
- Logging aspect
- Eventi asincroni con @Async

## Struttura Suggerita del Progetto
```
src/
├── main/
│   ├── java/
│   │   └── com/example/eventmanager/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── repository/
│   │       ├── security/
│   │       └── service/
│   └── resources/
│       ├── application.yml
│       └── db/migration/
└── test/
```

## Deliverables
1. Codice sorgente su GitHub
2. README con istruzioni per l'esecuzione
3. Collection Postman per testare le API
4. Documentazione API (Swagger)

## Tempistiche Suggerite
- Setup progetto e configurazione: 30 minuti
- Implementazione entità e repository: 45 minuti
- Implementazione security: 45 minuti
- Implementazione API base: 60 minuti
- Implementazione business logic: 45 minuti
- Testing: 30 minuti
- Documentazione e rifinitura: 30 minuti

## Note Aggiuntive
- Utilizzare le Java Records per i DTO
- Implementare pattern builder per gli oggetti complessi
- Utilizzare le nuove feature di Java 17 dove appropriato
- Seguire le best practice REST per i nomi degli endpoint
