/*************************************************************************************
 * Copyright (C) 2015-2016 GENERAL BYTES s.r.o. All rights reserved.
 *
 * This software may be distributed and modified under the terms of the GNU
 * General Public License version 2 (GPL2) as published by the Free Software
 * Foundation and appearing in the file GPL2.TXT included in the packaging of
 * this file. Please note that GPL2 Section 2[b] requires that all works based
 * on this software must also be made publicly available under the terms of
 * the GPL2 ("Copyleft").
 *
 * Contact information
 * -------------------
 *
 * GENERAL BYTES s.r.o
 * Web      :  http://www.generalbytes.com
 *
 ************************************************************************************/

package com.generalbytes.batm.server.extensions.extra.burstcoin.sources;

import com.generalbytes.batm.server.extensions.Currencies;
import com.generalbytes.batm.server.extensions.IRateSource;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class FixPriceRateSource implements IRateSource {
    private final BigDecimal rate;

    private String preferredFiatCurrency = Currencies.USD;

    public FixPriceRateSource(BigDecimal rate, String preferredFiatCurrency) {
        this.rate = rate;
        if (Currencies.EUR.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = Currencies.EUR;
        } else if (Currencies.USD.equalsIgnoreCase(preferredFiatCurrency)) {
            this.preferredFiatCurrency = Currencies.USD;
        }
    }

    @Override
    public Set<String> getCryptoCurrencies() {
        Set<String> result = new HashSet<>();
        result.add(Currencies.BURST);
        return result;
    }

    @Override
    public BigDecimal getExchangeRateLast(String cryptoCurrency, String fiatCurrency) {
        if (Currencies.BURST.equalsIgnoreCase(cryptoCurrency)) {
            return rate;
        }
        return null;
    }

    @Override
    public Set<String> getFiatCurrencies() {
        Set<String> result = new HashSet<>();
        result.add(Currencies.USD);
        result.add(Currencies.EUR);
        return result;
    }

    @Override
    public String getPreferredFiatCurrency() {
        return preferredFiatCurrency;
    }
}
