package fitme.ai.databasetest.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Goods {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String name;
    private float price;
    @Unique
    private double barCode;
    @Generated(hash = 288446926)
    public Goods(Long id, String name, float price, double barCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.barCode = barCode;
    }
    @Generated(hash = 1770709345)
    public Goods() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public double getBarCode() {
        return this.barCode;
    }
    public void setBarCode(double barCode) {
        this.barCode = barCode;
    }

}
