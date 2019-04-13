package cz.uhk.fim.cnbrates.data;

public class Currency {
    private String country;
    private String name;
    private int amount;
    private double rate;
    private String code;

    public Currency() {
    }

    public Currency(String country, String name, int amount, double rate, String code) {
        this.country = country;
        this.name = name;
        this.amount = amount;
        this.rate = rate;
        this.code = code;
    }

    /**
     * Parsing constructor
     * @param line single line with | delimiter
     */
    public Currency(String line) {
        String[] items = line.split("\\|");
        country = items[0];
        name = items[1];
        amount = Integer.valueOf(items[2]);
        code = items[3];
        rate = Double.valueOf(items[4].replace(',', '.'));
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
