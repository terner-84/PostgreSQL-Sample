/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.terner.postgesqlsample;

/**
 *
 * @author terner
 */
public class VssItemDemo {
    private String vssuid;
    private String teilnummer;
    private VssItemTypeDemo vsstype;
    private double egew;
    private double ggew;

    public VssItemDemo(String vssuid, String teilnummer, VssItemTypeDemo vsstype, double egew, double ggew) {
        this.vssuid = vssuid;
        this.teilnummer = teilnummer;
        this.vsstype = vsstype;
        this.egew = egew;
        this.ggew = ggew;
    }

    public String getVssuid() {
        return vssuid;
    }

    public void setVssuid(String vssuid) {
        this.vssuid = vssuid;
    }

    public String getTeilnummer() {
        return teilnummer;
    }

    public void setTeilnummer(String teilnummer) {
        this.teilnummer = teilnummer;
    }

    public VssItemTypeDemo getVsstype() {
        return vsstype;
    }

    public void setVsstype(VssItemTypeDemo vsstype) {
        this.vsstype = vsstype;
    }

    public double getEgew() {
        return egew;
    }

    public void setEgew(double egew) {
        this.egew = egew;
    }

    public double getGgew() {
        return ggew;
    }

    public void setGgew(double ggew) {
        this.ggew = ggew;
    }
    
    
}
