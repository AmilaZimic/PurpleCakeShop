package cake.shop.sa.Adapters;

/**
 * Created by Amila on 01/08/2017.
 */

public class User {

    public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}