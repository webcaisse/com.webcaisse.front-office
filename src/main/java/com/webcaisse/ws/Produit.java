
package com.webcaisse.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour produit complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="produit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="famille" type="{http://ws.webcaisse.com/}famille" minOccurs="0"/>
 *         &lt;element name="libelle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prix" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "produit", propOrder = {
    "famille",
    "libelle",
    "prix"
})
public class Produit {

    protected Famille famille;
    protected String libelle;
    protected Double prix;

    /**
     * Obtient la valeur de la propriété famille.
     * 
     * @return
     *     possible object is
     *     {@link Famille }
     *     
     */
    public Famille getFamille() {
        return famille;
    }

    /**
     * Définit la valeur de la propriété famille.
     * 
     * @param value
     *     allowed object is
     *     {@link Famille }
     *     
     */
    public void setFamille(Famille value) {
        this.famille = value;
    }

    /**
     * Obtient la valeur de la propriété libelle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Définit la valeur de la propriété libelle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibelle(String value) {
        this.libelle = value;
    }

    /**
     * Obtient la valeur de la propriété prix.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Définit la valeur de la propriété prix.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrix(Double value) {
        this.prix = value;
    }

}
