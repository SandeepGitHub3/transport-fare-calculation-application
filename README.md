# transport-fare-calculation-application
Fare calculation system for a typical Public transport system with flexible surcharge rules

# DB Design:

**1. Fare**

Used to Store Fare Details From One Zone to Another.

 ID        |FromZone        | ToZone        | Base Fare    | Daily Cap    | Weekly Cap   |
 | --------|------- | ------------- | ------------ | ------------ | ------------ |
 | 1      | 1      | 1 | 25       |  100            |    500          |
 | 2      | 1      | 2 | 30       |  120            |    600          |
 | 3      | 2      | 1 | 30       |  120            |    600          |
 | 4      | 2      | 2 | 20       |  80            |    400          |
 
 **2. Surcharge**

Used to Store Various Surcharge Rules.

Each Surcharge Rule maps to a specific Fare Record.

This will give us flexibility to add more surcharge rules, 
Eg: If we want to apply different charges on Holidays.

 * Morning Weekedays
 * Evening Weekdays
 * Morning Weekend
 * Evening Weekend

  ID             |Fare{ForeignKey}  | Surcharge     | Applicable Day     | Time From    | Time To      | Surcharge Type   |
 | ------------- | ---------------- | ------------- | ------------------ | ------------ | ------------ | ---------------- |
 | 1             | 1                | 5             |  Monday            |    07        | 10:30        | Weeday Peak      |
 
 **2. User Journey**
 
 This table Will maintain the User Journey Details and the Calculated Fare. 
We can easily Lookup this table while deciding on the applicable fare for a new Journey and apply Daily/Weekly Caps accprdingly.

  ID             | User Id          |Fare{ForeignKey}      | Surcharge{ForeignKey}| Applicable Fare | JourneyDateTime   | 
 | ------------- | ---------------- | ---------------------| -------------------- | --------------- | ----------------- | 
 | 1             | 1                | 1                    |  1                   |    30           | Monday-10:30      | 
 
 
 # Tech Stack
 1. Java 8
 2. Spring Boot
 3. Spring JPA
 4. H2 [In memory DB]
 5. Lombok
 
 # Notes:
 * Application will load the Fare table data on startup
 * To populate Surcharge table use below rest endpoint
 `localhost:8080/populateSurcharge`
 
