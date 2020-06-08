package sd.shopguy.Metier;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Slimane on 15/04/2016.
 */
public class Command implements Serializable {

    private int numero ;
    private String date ;
    private Boolean etat ;
    private Double montant ;
    private String idClient ;

    public Command(int numero, Date date, Boolean etat, Double montant ) {
        this.numero = numero;
        this.etat = etat;
        this.montant = montant;
        /*String string = "January 2, 2010";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(string, formatter);*/
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM, DateFormat.MEDIUM);
        this.date = mediumDateFormat.format(date);
        this.idClient = idClient ;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getEatText(){
        if (etat) {
            return  "Validée" ;

        } else {
            return "Encours";

        }
    }
}
