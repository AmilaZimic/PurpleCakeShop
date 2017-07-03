package cake.shop.sa.Adapters;

/**
 * Created by Amila on 27/06/2017.
 */

public class RowItemAdapter {

    private int ImageId;
    private String Title;

    public RowItemAdapter(String Title, int ImageId){

        this.Title = Title;
        this.ImageId = ImageId;
    }

    public String getTitle(){

        return Title;
    }

    public int getImageId(){

        return ImageId;
    }

    @Override
    public String toString() {
        return Title ;
    }

}
