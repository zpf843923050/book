package com.snut.book.POJO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_bookinfo", schema = "book_db")
public class Book {
    private int id;
    private String qrCode;
    private String bookName;
    private String author;
    private String amount;
    private BigDecimal price;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "QR_code")
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Basic
    @Column(name = "book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return id == that.id &&
                Objects.equals(qrCode, that.qrCode) &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(author, that.author) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(price, that.price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", qrCode='" + qrCode + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", amount='" + amount + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qrCode, bookName, author, amount, price);
    }
}
