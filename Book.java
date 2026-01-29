import java.util.*;

public class Book {
    private String Bookid;
    private String title;
    private String author;
    private int publicationYear;
    private double price;
    private String status;
    private List<String> reviews;
    private double rating;
    private int ratingCount;



    public Book(String bookid, String title, String author, int publicationYear, double price,String status) {
        this.Bookid = bookid;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
        this.status = status;
        this.reviews = new ArrayList<>();
        this.rating = 0.0;
        this.ratingCount = 0;

    }


    public Book() {
        this(null,null,null,0,0.0,null);
    }

    public Book(String bookID, String title, String author, int publicationYear, double price, String status, int rating, List<String> reviews) {
    }

    public String getBookid() {
        return Bookid;
    }

    public void setBookid(String bookId) {
        this.Bookid = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void addReview(String review, int userRating) {
        reviews.add(review);
        rating = (rating * ratingCount + userRating) / (++ratingCount);
    }

    @Override
    public String toString() {
        return "ID: " + Bookid + " | " + title + " by " + author + " (" + publicationYear + ") - $" + price + " (Status: " + status + ")" + " - Rating: " + rating + " (" + reviews.size() + " reviews)";
    }


}

