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
    private long price;
    private int max_speed_domestic;
    private int max_speed_international;
    private int min_speed_domestic;
    private int min_speed_international;

    public Plan() {
    }

    public Plan(int plan_id, String name, long price, int max_speed_domestic, int max_speed_international, int min_speed_domestic, int min_speed_international) {
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getMax_speed_domestic() {
        return max_speed_domestic;
    }

    public void setMax_speed_domestic(int max_speed_domestic) {
        this.max_speed_domestic = max_speed_domestic;
    }

    public int getMax_speed_international() {
        return max_speed_international;
    }

    public void setMax_speed_international(int max_speed_international) {
        this.max_speed_international = max_speed_international;
    }

    public int getMin_speed_domestic() {
        return min_speed_domestic;
    }

    public void setMin_speed_domestic(int min_speed_domestic) {
        this.min_speed_domestic = min_speed_domestic;
    }

    public int getMin_speed_international() {
        return min_speed_international;
    }

    public void setMin_speed_international(int min_speed_international) {
        this.min_speed_international = min_speed_international;
    }
   
   
}
