package com.AgriculturalSales.bean;


import java.util.List;
import java.util.Map;

public class Kind {

    private Kind1 kind1;

    private List<Kind2> kind2;

    private Map<Integer,List<Kind3>> map;

    public Kind() {
    }

    public Kind(Kind1 kind1, List<Kind2> kind2, Map<Integer, List<Kind3>> map) {
        this.kind1 = kind1;
        this.kind2 = kind2;
        this.map = map;
    }

    @Override
    public String toString() {
        return "Kind{" +
                "kind1=" + kind1 +
                ", kind2=" + kind2 +
                ", map=" + map +
                '}';
    }

    public List<Kind2> getKind2() {
        return kind2;
    }

    public void setKind2(List<Kind2> kind2) {
        this.kind2 = kind2;
    }

    public Map<Integer, List<Kind3>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<Kind3>> map) {
        this.map = map;
    }

    public Kind1 getKind1() {
        return kind1;
    }

    public void setKind1(Kind1 kind1) {
        this.kind1 = kind1;
    }


}
