# ForeignCurrencyAccountService

W wymaganiach do zadania, słowo "konto" zastępuje czasami słowo "rachunek".
W tej aplikacji zostało to rozdzielone:
- prefix "UserAccount" oznacza konto użytkownika
- prefix "CurrencyAccount" oznacza konto walutowe/rachunek

Ze względu na ograniczenia czasowe nie napisałem testów.


Wymagania:
- gradle 8.10.2
- java 21

Uruchomienie:
1. gradle clean build
2. docker-compose up -d
3. gradle bootRun

Przykładowe zapytania:

curl --location 'http://localhost:8080/user-account' \
--header 'Content-Type: application/json' \
--data '{
    "name" : "Jan",
    "surname" : "Kowalski",
    "amount" : "100"
}'

curl --location 'http://localhost:8080/currency-account/setup' \
--header 'Content-Type: application/json' \
--data '{
    "userAccountUuid": "6f850797-d9b0-44cc-89ee-81256f80041b",
    "requestedAccountCurrency": "USD"
}'
