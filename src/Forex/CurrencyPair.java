package Forex;

public class CurrencyPair {
    private String symbol;
    private Double price;
    private Double ask;
    private Double bid;
    private Long timeStamp;

    public CurrencyPair(String symbol, Double price, Double ask, Double bid, Long timeStamp) {
        this.symbol = symbol;
        this.price = price;
        this.ask = ask;
        this.bid = bid;
        this.timeStamp = timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", ask=" + ask +
                ", bid=" + bid +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
