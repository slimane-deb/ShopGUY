package sd.shopguy.Metier;

/**
 * Created by Slimane on 17/04/2016.
 */
public class ProduitPanier extends Produit{

    //private HashMap<Produit,Integer> element ;
    private int quantityPanier ;

    public ProduitPanier(Produit p,int quantity ) {

        super.nom = p.nom;
        super.marque = p.marque ;
        super.anneeFab = p.anneeFab ;
        super.classProduit = p.classProduit ;
        super.image = p.image ;
        super.filename = p.filename ;
        super.description = p.description ;
        super.typeClient = p.typeClient ;
        super.quantite=p.quantite ;
        super.prix = p.prix ;

        this.quantityPanier = quantity;
    }

    public int getQuantityPanier() {
        return quantityPanier;
    }

    public void setQuantityPanier(int quantityPanier) {
        this.quantityPanier = quantityPanier;
    }

    public void setQuantity(int quantity) {
        this.quantityPanier = quantity;
    }
    public void decQuantity(){
        if (quantityPanier>0){
            quantityPanier--;
        }
    }
}
