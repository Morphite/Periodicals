package ua.nure.leonov.summarytask.entity;

public class Edition extends Entity {
    private int id;
    private Theme theme;
    private String title;
    private String publisher;
    private String text;
    private double price;

    private String imgPath;

    public Edition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", theme=" + theme +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                '}';
    }


}
