package cake.shop.sa.Adapters;

import java.io.Serializable;

/**
 * Created by Amila on 06/08/2017.
 */

public class ImagesSmall implements Serializable {

    public String small_image;
    public String name_bih;
    public String name_eng;
    public String price;

    public ImagesSmall(String small_image, String name_bih, String name_eng, String price ){
        this.small_image = small_image;
        this.name_bih = name_bih;
        this.name_eng = name_eng;
        this.price = price;
    }
}
