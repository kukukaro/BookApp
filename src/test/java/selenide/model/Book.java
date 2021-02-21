package selenide.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Book {
    private String title;
    private String author;
    private String year;

    public static Book createRandomBook(){
        String title = RandomStringUtils.randomAlphabetic(6);
        String author = RandomStringUtils.randomAlphabetic(6);
        Random random = new Random();
        String year = Integer.toString(random.nextInt(2022));

        return new Book(title, author, year);
    }

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }
}
