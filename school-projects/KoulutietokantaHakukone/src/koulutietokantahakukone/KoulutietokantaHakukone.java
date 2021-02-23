/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koulutietokantaHakukone;

import java.sql.*;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author pinja
 */
public class KoulutietokantaHakukone {

    public static void main(String[] args) throws SQLException{
        Scanner lukija = new Scanner(System.in);
        Connection db = DriverManager.getConnection("jdbc:sqlite:kurssit.db");
        Statement s = db.createStatement();
        
        while(true){
            System.out.print("Valitse toiminto: ");
            String toiminto = lukija.nextLine();
            
                if(toiminto.equals("1")){
                    System.out.print("Anna vuosi: ");
                    String vuosi = lukija.nextLine();
                    try {
                        PreparedStatement p = db.prepareStatement("SELECT SUM(Kurssit.laajuus) AS summa FROM Kurssit, Suoritukset WHERE Suoritukset.kurssi_id=Kurssit.id AND Suoritukset.paivays LIKE ?");
                        p.setString(1, "%" + vuosi + "%");
                        ResultSet r = p.executeQuery();
                        int pisteet = r.getInt("summa");
                        if(pisteet == 0 ){
                            System.out.println("Vuotta ei lÃ¶ytynyt");
                        }else{
                            System.out.println("Opintopisteiden mÃ¤Ã¤rÃ¤: " + pisteet);
                        }
                    } catch (SQLException e) {
                        System.out.println("Vuotta ei lÃ¶ytynyt");
                    }
                }
            
                if(toiminto.equals("2")){
                    System.out.print("Anna opiskelijan nimi: ");
                    String nimi = lukija.nextLine();
                
                    try{
                        PreparedStatement p = db.prepareStatement("SELECT Kurssit.nimi AS kurssit, kurssit.laajuus AS op, suoritukset.paivays AS paivays, suoritukset.arvosana AS arvosana FROM Opiskelijat, Kurssit, Suoritukset WHERE Opiskelijat.id=Suoritukset.opiskelija_id AND kurssit.id=suoritukset.kurssi_id AND opiskelijat.nimi = ? ORDER BY suoritukset.paivays ");
                        p.setString(1, nimi);
                        ResultSet r = p.executeQuery();
                        // jotta virhe tapahtuu ennen otsikoiden tulostusta
                        String kurssi = r.getString("kurssit");
                        System.out.println("kurssi\t\top\tpÃ¤ivÃ¤ys\t\tarvosana");
                        while (r.next()) {
                            System.out.println(r.getString("kurssit") + "\t\t" + r.getInt("op") + "\t" + r.getString("paivays") + "\t" + r.getInt("arvosana"));
                        }
                    }catch (SQLException e) {
                        System.out.println("Opiskelijaa ei lÃ¶ytynyt");
                    }    
                }
            
                if(toiminto.equals("3")){
                    System.out.print("Anna kurssin nimi: ");
                    String nimi = lukija.nextLine();
                
                    try{
                        PreparedStatement p = db.prepareStatement("SELECT ROUND(1.0*SUM(suoritukset.arvosana)/COUNT(suoritukset.kurssi_id),2) AS ka FROM suoritukset, kurssit WHERE kurssit.id=suoritukset.kurssi_id AND kurssit.nimi=?");
                        p.setString(1,nimi);
                        ResultSet r = p.executeQuery();
                        Double ka = r.getDouble("ka");
                        if(ka == 0){
                            System.out.println("Kurssia ei lÃ¶ytynyt");
                        }else{
                            System.out.println("Keskiarvo: " + ka);
                        }
                    }catch (SQLException e) {
                        System.out.println("Kurssia ei lÃ¶ytynyt");
                    }    
                }
            
                if(toiminto.equals("4")){
                    System.out.print("Anna opettajien mÃ¤Ã¤rÃ¤: ");
                    int maara = Integer.valueOf(lukija.nextLine());
                    try{
                        PreparedStatement p = db.prepareStatement("SELECT opettajat.nimi AS nimet, SUM(kurssit.laajuus) AS yht FROM suoritukset, kurssit, opettajat WHERE kurssit.id=suoritukset.kurssi_id AND opettajat.id=kurssit.opettaja_id GROUP BY opettajat.nimi  ORDER BY yht desc LIMIT ?");
                        p.setInt(1,maara);
                        ResultSet r = p.executeQuery();
                        // jotta virhe lÃ¶ytyy ajoissa
                        String nimi = r.getString("nimet");
                        System.out.println("opettaja\t\top");
                        while(r.next()){
                            // varmistetaan oikea mÃ¤Ã¤rÃ¤ sisennyksiÃ¤ eri pituisille nimille
                            if(r.getString("nimet").length()<15){
                                System.out.println(r.getString("nimet") + "\t\t" + r.getInt("yht"));
                            }else{
                                System.out.println(r.getString("nimet") + "\t" + r.getInt("yht"));
                            }    
                        }
                            
                    }catch(SQLException e){
                        System.out.println("Kysely ei onnistunut");    
                }
            
                
                if(toiminto.equals("5")){
                    break;
                }
            }
        }
    }
}
