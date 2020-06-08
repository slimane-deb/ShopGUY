package sd.shopGUY.Metier;

import java.io.Serializable;

import sd.shopguy.Metier.ClassProduit;

/**
 * Created by Slimane on 31/03/2016.
 */
public class Produit implements Serializable {

    protected String nom;
    protected String marque;
    protected int prix;
    protected ClassProduit classProduit ;
    protected int anneeFab;
    protected String typeClient;
    protected String description;
    protected String image;
    protected int quantite ;
    protected String filename ;

    public Produit(ClassProduit classProduit) {
        this.classProduit = classProduit;
    }

    public Produit(String nom, String marque, int prix, ClassProduit classProduit, int anneeFab, String typeClient, String description, String image) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
        this.classProduit = classProduit;
        this.anneeFab = anneeFab;
        this.typeClient = typeClient;
        this.description = description;
        this.image = image;

    }

    public Produit() {
    }

    public ClassProduit getClassProduit() {
        return classProduit;
    }

    public void setClassProduit(ClassProduit classProduit) {
        this.classProduit = classProduit;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getAnneeFab() {
        return anneeFab;
    }

    public void setAnneeFab(int annee) {
        this.anneeFab = annee;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}