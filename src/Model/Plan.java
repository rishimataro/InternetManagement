/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Ngoc Thao
 */
public class Plan {
    private int plan_id;
    private String name;
    private double price;
    private double max_speed_domestic;
    private double max_speed_international;
    private double min_speed_domestic;
    private double min_speed_international;

    public Plan() {
    }

    public Plan(int plan_id, String name, double price, double max_speed_domestic, double max_speed_international, double min_speed_domestic, double min_speed_international) {
        this.plan_id = plan_id;
        this.name = name;
        this.price = price;
        this.max_speed_domestic = max_speed_domestic;
        this.max_speed_international = max_speed_international;
        this.min_speed_domestic = min_speed_domestic;
        this.min_speed_international = min_speed_international;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMax_speed_domestic() {
        return max_speed_domestic;
    }

    public void setMax_speed_domestic(double max_speed_domestic) {
        this.max_speed_domestic = max_speed_domestic;
    }

    public double getMax_speed_international() {
        return max_speed_international;
    }

    public void setMax_speed_international(double max_speed_international) {
        this.max_speed_international = max_speed_international;
    }

    public double getMin_speed_domestic() {
        return min_speed_domestic;
    }

    public void setMin_speed_domestic(double min_speed_domestic) {
        this.min_speed_domestic = min_speed_domestic;
    }

    public double getMin_speed_international() {
        return min_speed_international;
    }

    public void setMin_speed_international(double min_speed_international) {
        this.min_speed_international = min_speed_international;
    }
    @Override
    public String toString() {
        return name;
    }
    
   
   
}
