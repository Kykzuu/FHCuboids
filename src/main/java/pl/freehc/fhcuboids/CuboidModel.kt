package pl.freehc.fhcuboids;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Cuboids")
public class CuboidModel {
    @Id
    @GeneratedValue
    private long ID;
    private double MaX;
    private double MiX;
    private double MaZ;
    private double MiZ;
    private double jajX;
    private double jajZ;
    private double jajY;
    private float jajYaw = 0.0f;
    private float jajPitch = 0.0f;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isWarpPurchased = false;
    private double warpX = 0;
    private double warpZ = 0;
    private double warpY = 0;
    private float warpYaw = 0.0f;
    private float warpPitch = 0.0f;
    @Type(type = "uuid-char")
    private UUID OwnerUUID;
    private String OwnerNickname;
    private long ExpireTime;
    private long CreatedTime;
    private int Size;
    @Column(columnDefinition="double precision default '0'")
    private double PricePaid;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Cuboids_friends")
    @OrderColumn(name = "pos")
    @Type(type="uuid-char")
    private List<UUID> FriendsUUID;

    // Getter
    public double getID() {
        return ID;
    }

    public double getMaX() {
        return MaX;
    }

    public double getMiX() {
        return MiX;
    }

    public double getMaZ() {
        return MaZ;
    }

    public double getMiZ() {
        return MiZ;
    }

    public double getjajX() {
        return jajX;
    }

    public double getjajZ() {
        return jajZ;
    }

    public double getjajY() {
        return jajY;
    }

    public float getjajYaw() {
        return jajYaw;
    }

    public float getjajPitch() {
        return jajPitch;
    }

    public boolean getisWarpPurchased() {
        return isWarpPurchased;
    }
    public double getwarpX() {
        return warpX;
    }

    public double getwarpZ() {
        return warpZ;
    }

    public double getwarpY() {
        return warpY;
    }

    public float getwarpYaw() {
        return warpYaw;
    }

    public float getwarpPitch() {
        return warpPitch;
    }

    public long getExpireTime() {
        return ExpireTime;
    }

    public long getCreatedTime() {
        return CreatedTime;
    }

    public UUID getOwnerUUID() {
        return OwnerUUID;
    }

    public String getOwnerNickname() {
        return OwnerNickname;
    }
    public List<UUID> getFriendsUUID() {
        return FriendsUUID;
    }


    public int getSize() {
        return Size;
    }

    public double getPricePaid() {
        return PricePaid;
    }


    public void setID(int newID){
        ID = newID;
    }

    public void setMaX(double newMaX){
        MaX = newMaX;
    }

    public void setMiX(double newMiX){
        MiX = newMiX;
    }

    public void setMaZ(double newMaZ){
        MaZ = newMaZ;
    }

    public void setMiZ(double newMiZ){
        MiZ = newMiZ;
    }

    public void setjajX(double newjajX){
        jajX = newjajX;
    }

    public void setjajZ(double newjajZ){
        jajZ = newjajZ;
    }

    public void setjajY(double newjajY){
        jajY = newjajY;
    }
    public void setjajYaw(float newjajYaw){
        jajYaw = newjajYaw;
    }
    public void setjajPitch(float newjajPitch){
        jajPitch = newjajPitch;
    }

    public void setisWarpPurchased(boolean newisWarpPurchased){
        isWarpPurchased = newisWarpPurchased;
    }
    public void setwarpX(double newwarpX){
        warpX = newwarpX;
    }

    public void setwarpZ(double newwarpZ){
        warpZ = newwarpZ;
    }

    public void setwarpY(double newwarpY){
        warpY = newwarpY;
    }
    public void setwarpYaw(float newwarpYaw){
        warpYaw = newwarpYaw;
    }
    public void setwarpPitch(float newwarpPitch){
        warpPitch = newwarpPitch;
    }

    public void setOwnerUUID(UUID newOwnerUUID) {
        OwnerUUID = newOwnerUUID;
    }

    public void setOwnerNickname(String newOwnerNickname) {
        OwnerNickname = newOwnerNickname;
    }

    public void setExpireTime(long newExpireTime) {
        ExpireTime = newExpireTime;
    }

    public void setCreatedTime(long newCreatedTime) {
        CreatedTime = newCreatedTime;
    }

    public void setFriendsUUID(List<UUID> newFriendsUUID) {
        FriendsUUID = newFriendsUUID;
    }

    public void setSize(int newSize){
        Size = newSize;
    }


    public void setPricePaid(double newPricePaid){
        PricePaid = newPricePaid;
    }
}
