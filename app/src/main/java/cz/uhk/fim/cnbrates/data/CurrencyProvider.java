package cz.uhk.fim.cnbrates.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Nacita a parsuje data z weboveho zdroje
 * <br>
 * Singleton
 */
public class CurrencyProvider {
    private static final String CNB_URL = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.txt?date=%s";
    private static CurrencyProvider _INSTANCE = null;

    private Date lastDate = null;
    private List<Currency> currencies = new ArrayList<>();

    private CurrencyProvider() {
    }

    public static CurrencyProvider getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new CurrencyProvider();
        }
        return _INSTANCE;
    }

    public List<Currency> getAllCurrencies(Date date) {

        if (date == null) {
            date = new Date();
        }

        if (lastDate != null && lastDate.equals(date)) {
            return currencies;
        }
        currencies.clear();
        String dateStr = new SimpleDateFormat("dd.MM.yyyy").format(date);
        try {
            URL url = new URL(String.format(CNB_URL, dateStr));

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line = null;

                //skip first two lines
                reader.readLine();
                reader.readLine();
                while ((line = reader.readLine()) != null) {
                    Currency c = new Currency(line);
                    currencies.add(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return currencies;
    }

}
