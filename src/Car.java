import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public class Car implements Serializable {

    @Save
    private String maker;
    @Save
    private String model;
    @Save
    private String year;
    private String color;
    private String price;

    public Car(String maker, String model, String year, String color, String price) {
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public Car(String maker, String model, String year) {
        this.maker = maker;
        this.model = model;
        this.year = year;
    }

    public Car() {
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car that = (Car) o;
        return Objects.equals(maker, that.maker) && Objects.equals(model, that.model) && Objects.equals(year, that.year) && Objects.equals(color, that.color) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maker, model, year, color, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Save {}
