package com.example.ape;


public class Case {
    int ipnut;
    String name;
    String condition;
    String expect;
    public int getIpnut() {
        return ipnut;
    }
    public void setIpnut(int ipnut) {
        this.ipnut = ipnut;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getExpect() {
        return expect;
    }
    public void setExpect(String expect) {
        this.expect = expect;
    }
    public String print(){
        String text = "//"+ ipnut+"\n"
                +"it(\n"+
                "'"+condition +"\n'"
                +"'==>"+expect+"'()==>{})";
        return text;
    }


}

