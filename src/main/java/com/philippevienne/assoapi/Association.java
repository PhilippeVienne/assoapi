package com.philippevienne.assoapi;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.sql.Date;

@Entity
@Data
public class Association {

    /**
     * Numéro Waldec national unique de l’associatiuon
     */
    @Column(length = 255)
    @Id
    private String id;
    /**
     * Ancien numéro de l’association
     */
    @Column(length = 255)
    private String id_ex;
    /**
     * N° siret ( facultatif )
     */
    @Column(length = 14)
    private String siret;
    /**
     * N° de RUP attribué par le Ministère
     */
    @Column(length = 11)
    private String rup_mi;
    /**
     * Code du site gestionnaire de l’association
     */
    @Column(length = 4)
    private String gestion;
    /**
     * Date de déclaration de création ( date de dépôt du dossier en Préfecture )
     */
    private String date_creat;
    /**
     * Date de la dernière déclaration
     */
    private String date_decla;
    /**
     * Date de publication JO de l’avis de création
     */
    private String date_publi;
    /**
     * Date de déclaration de dissolution de l’association
     */
    private String date_disso;
    /**
     * Simplement déclarée 1901 ou autre
     */
    @Column(length = 1)
    private String nature;
    /**
     * Simple ou union ou fédération ( S, U, F )
     */
    @Column(length = 1)
    private String groupement;
    /**
     *
     */
    @Column(length = 250)
    private String titre;
    /**
     * Utilisé par DJO comme destinataire de la facturation
     */
    @Column(length = 38)
    private String titre_court;

    @Column(length = 10485760)
    private String objet;
    /**
     * Code obligatoire dans la nomenclature nationale
     */
    @Column(length = 6)
    private String objet_social1;
    /**
     * 2ème code ( facultatif ) dans la nomenclature nationale
     */
    @Column(length = 6)
    private String objet_social2;
    /**
     * Adresse du siège de l’association ( compatible DJO )
     */
    @Column(length = 76)
    private String adrs_complement;
    /**
     *
     */
    @Column(length = 10)
    private String adrs_numvoie;
    /**
     *
     */
    @Column(length = 1)
    private String adrs_repetition;
    /**
     *
     */
    @Column(length = 5)
    private String adrs_typevoie;
    /**
     * Code du type de voie ( selon codification MI )
     */
    @Column(length = 42)
    private String adrs_libvoie;
    /**
     *
     */
    @Column(length = 38)
    private String adrs_distrib;
    /**
     * Adrs_repetition ( B, T, Q ) n’est plus utlisé = regroupé avec
     */
    @Column(length = 5)
    private String adrs_codeinsee;
    /**
     *
     */
    @Column(length = 5)
    private String adrs_codepostal;
    /**
     *
     */
    @Column(length = 45)
    private String adrs_libcommune;
    /**
     * Nom patronymique du déclarant ( présence temporaire )
     */
    @Column(length = 38)
    private String adrg_declarant;
    /**
     * Adresse de gestion de l’association ( compatibilité format postal )
     */
    @Column(length = 38)
    private String adrg_complemid;
    /**
     *
     */
    @Column(length = 38)
    private String adrg_complemgeo;
    /**
     *
     */
    @Column(length = 38)
    private String adrg_libvoie;
    /**
     * Adresse de facturation pour la DJO
     */
    @Column(length = 38)
    private String adrg_distrib;
    /**
     *
     */
    @Column(length = 5)
    private String adrg_codepostal;
    /**
     *
     */
    @Column(length = 32)
    private String adrg_achemine;
    /**
     *
     */
    @Column(length = 38)
    private String adrg_pays;
    /**
     * Code de la civilié du dirigeant principal
     */
    @Column(length = 2)
    private String dir_civilite;
    /**
     * Renseignements facultatifs événtuellement présents sur les documents fournis par l’association
     */
    @Column(length = 10)
    private String telephone;
    /**
     *
     */
    @Column(length = 64)
    private String siteweb;
    /**
     *
     */
    @Column(length = 64)
    private String email;
    /**
     * Indicateur d’autorisation de publication sur le WEB
     */
    @Column(length = 1)
    private String publiweb;
    /**
     *
     */
    @Column(length = 255)
    private String observation;
    /**
     * Position d’activité de l’association (Active, Dissoute ou Supprimée)
     */
    @Column(length = 1)
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp maj_time;

}
