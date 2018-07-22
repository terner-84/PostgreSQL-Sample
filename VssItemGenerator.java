/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.terner.postgesqlsample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author terner
 */
public class VssItemGenerator {
    private Random rand;
    private VssItemDemo vi;
    private List<VssItemDemo> viList;
    private int count;
    
    public VssItemGenerator(int count) {
        this.count = count;
        viList = new ArrayList<>();
        rand = new Random();
    }

    private String generateVssuid() {
        char znak = (char) (rand.nextInt(25) + 65);
        String vssuid = znak + String.valueOf(System.currentTimeMillis());
        return vssuid;
    }
    
    private String generateTeilnummer() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(0);
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9) + " ");
        
        int x;
        for (int i = 0; i < 2; i++) {
            x = rand.nextInt(1000);
            sb.append(String.format("%03d ", x));
        }
        
        for (int i = 0; i < rand.nextInt(4); i++) {
            x = rand.nextInt(25) + 65;
            if (x == 73 || x == 79) {
                x = 84;
            } else {
                sb.append((char) x);
            }
        }
                
        return sb.toString();
    }
    
    private VssItemTypeDemo generateVsstype() {
        int typ = rand.nextInt(3);
        VssItemTypeDemo type;
        switch (typ) {
            case 0:
                type = VssItemTypeDemo.EBK;
                break;
            case 1:
                type = VssItemTypeDemo.EBOM;
                break;
            case 2:
                type = VssItemTypeDemo.NODE;
                break;
            default:
                type = VssItemTypeDemo.NODE;
        }
        return type;
    }
    
    private double generateEgew() {
        int a = rand.nextInt(10000);
        int b = rand.nextInt(100);
        double d = (double) b / 100;
        d += a;
        return d;
    }
    
    private double generateGgew() {
        int a = rand.nextInt(10000);
        int b = rand.nextInt(100);
        double d = (double) b / 100;
        d += a;
        return d;
    }
    
    public List<VssItemDemo> getViList() {
        for (int i = 0; i < count; i++) {
            viList.add(new VssItemDemo(generateVssuid(), generateTeilnummer(), generateVsstype(), generateEgew(), generateGgew()));
        }
        return viList;
    }
    
    
}
