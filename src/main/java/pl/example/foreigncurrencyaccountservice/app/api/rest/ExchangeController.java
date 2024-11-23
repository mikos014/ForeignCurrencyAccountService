package pl.example.foreigncurrencyaccountservice.app.api.rest;

import org.springframework.web.bind.annotation.RestController;

/**
 * Wymaganie
 * Aplikacja powinna udostępnić REST API do wymiany pieniędzy w parze PLN<->USD (czyli PLN na USD oraz USD na PLN),
 * a aktualny kurs wymiany pobrać z publicznego API NBP (http://api.nbp.pl/).
 */

@RestController("/exchange")
class ExchangeController {
}
