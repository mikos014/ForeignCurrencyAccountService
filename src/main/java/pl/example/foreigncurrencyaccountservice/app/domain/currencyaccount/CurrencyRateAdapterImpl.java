package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.api.NbpApi;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyRateDto;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
class CurrencyRateAdapterImpl implements CurrencyRateAdapter {
    private final NbpApi nbpApi;

    @Override
    public BigDecimal getSellRate(CurrencyEnum currencyEnum) {
        return getRate(currencyEnum).getSellRate();
    }

    @Override
    public BigDecimal getBuyRate(CurrencyEnum currencyEnum) {
        return getRate(currencyEnum).getBuyRate();
    }

    @Override
    public CurrencyRateDto getRate(CurrencyEnum currencyEnum) {
//        to skrót/założenie, że enumy walut odpowiadają wartościom w NBP
//        na tą chwile tak to implementuje
        String currencyToLowerCase = currencyEnum.name().toLowerCase();
        var responseRate = nbpApi.getRateByCurrency(currencyToLowerCase).getRates().getFirst();
        return CurrencyRateDto.builder()
                .currency(currencyEnum)
//        odwrócona logika sprzedaży w tej aplikacji i api nbp
                .sellRate(responseRate.getBid())
//        odwrócona logika kupna w tej aplikacji i api nbp
                .buyRate(responseRate.getAsk())
                .build();
    }

}
