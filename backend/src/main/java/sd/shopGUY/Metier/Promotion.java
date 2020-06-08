package sd.shopGUY.Metier;


import java.sql.Date;

/**
 * Created by ProUser on 26/06/2016.
 */
public class Promotion {

    private Date date ;
    private String description ;

    public Promotion() {
    }

    public Promotion(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
