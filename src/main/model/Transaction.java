package main.model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Comparable<Transaction> {
    public enum Type {
        WITHDRAW, DEPOSIT
    }

    private Type type;
    private long timestamp;
    private String id;
    private double amount;

    public Transaction(Type type, long timestamp, String id, double amount) {
        this.type = type;
        this.timestamp = timestamp;
        this.id = id;
        this.amount = amount;
    }

    public Transaction(Transaction source) {
        this.type = source.type;
        this.timestamp = source.timestamp;
        this.id = source.id;
        this.amount = source.amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String returnDate() {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("America/Chicago"));
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return localDateTime.format(format);
    }

    @Override
    public String toString() {
        return (type) + "      " +
                "\t\t" + returnDate() + "" +
                "\t\t\t" + id + "" +
                "\t\t\t\t$" + amount + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction transaction = (Transaction) o;

        return Objects.equals(type, transaction.type)
                && returnDate().equals(transaction.returnDate())
                && getId().equals(transaction.getId())
                && getAmount() == transaction.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), returnDate(), getId(), getAmount());
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(this.timestamp, o.timestamp);
    }
}
