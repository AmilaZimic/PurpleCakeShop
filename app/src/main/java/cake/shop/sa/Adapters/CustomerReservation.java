package cake.shop.sa.Adapters;

/**
 * Created by Amila on 03/08/2017.
 */

public class CustomerReservation {

    public String name;
    public String lastname;
    public String email;
    public String phone;
    public String date;
    public String time;
    public String location;
    public String seats;

    public CustomerReservation() {
    }

    public CustomerReservation(String name, String lastname, String email, String phone, String date, String time, String location, String seats) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.location = location;
        this.seats = seats;

    }
}
