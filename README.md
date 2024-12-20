# Physioplan-project
A simple java application to plan and monitor physiotherapy exercises
# Physioplan Project

Una piccola applicazione Java per gestire dati relativi a esercitazioni, che interagisce con un database MySQL.

## Requisiti

1. **Java JDK** (versione 8 o successiva).
2. **Server MySQL** .
3. **Client MySQL** per eseguire lo script SQL.
4. **Configurazione corretta del file `configurazione.xml`**.

## Configurazione del Database

Per configurare il database richiesto dall'applicazione:

1. **Installa MySQL**
   - Scarica e installa il server MySQL .

2. **Avvia il server MySQL**
   - Assicurati che il server MySQL sia in esecuzione.

3. **Crea il database**
   - Utilizza il client MySQL per eseguire il file SQL fornito (`physioplan.sql`) e creare il database.
   - Questo script crea:
     - Il database `physioplan`.
     - La tabella `esercitazioni`.
     - Dati di esempio nella tabella.

---

## Configurazione del Progetto

Prima di eseguire l'applicazione, è necessario configurare le porte e le credenziali per la connessione al database:

1. **Modifica il file di configurazione**
   - Apri il file `Physioplan/src/configurazione.xml`.
   - Configura le seguenti impostazioni:
     ```xml
     <configurazione>
       <porta>3307</porta> <!-- Porta del server MySQL (modifica se diversa) -->
       <utente>root</utente> <!-- Nome utente MySQL -->
       <password>password</password> <!-- Password MySQL -->
       <database>physioplan</database> <!-- Nome del database -->
     </configurazione>
     ```

2. **Verifica la porta**
   - La porta predefinita di MySQL è `3307`. Se hai configurato MySQL su una porta diversa, aggiornala nel file `configurazione.xml`.

---

## Esecuzione del Progetto

1. **Compila ed esegui il progetto**
   - Apri il progetto in un IDE come **NetBeans**.
   - Avvia l'applicazione.

2. **Funzionalità**
   -Per visualizzare casi d'uso vedere il file Documentazione_physioplan.pdf

---
