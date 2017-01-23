package edu.calpoly.jgunawan.plantinventory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Plant implements Parcelable {

    private String botanicalName;
    private String commonName;
    private int size;
    private String details;
    private String type;
    private int USDAZone;
    private int numAvailable;
    private boolean saleable;

    /*
       TODO: Minimum Plant fields to create a Plant object?
       public Plant(...)

       TODO: Plant fields similar to the ones in the pdf?
    */

    public Plant() {

    }

    public Plant(String commonName) {
        this.commonName = commonName;
    }

    public String getBotanicalName() {
        return botanicalName;
    }

    public String getCommonName() {
        return commonName;
    }

    public int getSize() {
        return size;
    }

    public String getDetails() {
        return details;
    }

    public String getType() {
        return type;
    }

    public int getUSDAZone() {
        return USDAZone;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public boolean isSaleable() {
        return saleable;
    }

    public void setBotanicalName(String botanicalName) {
        this.botanicalName = botanicalName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUSDAZone(int USDAZone) {
        this.USDAZone = USDAZone;
    }

    public void setSaleable(boolean saleable) {
        this.saleable = saleable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(botanicalName);
        parcel.writeString(commonName);
        parcel.writeInt(size);
        parcel.writeString(details);
        parcel.writeString(type);
        parcel.writeInt(USDAZone);
        parcel.writeInt(numAvailable);

        boolean[] arr = new boolean[1];
        arr[0] = saleable;
        parcel.writeBooleanArray(arr);
    }

    public static final Parcelable.Creator<Plant> CREATOR
            = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel parcel) {
            Plant p = new Plant();

            boolean[] arr = new boolean[1];
            parcel.readBooleanArray(arr);
            p.setSaleable(arr[0]);

            p.setNumAvailable(parcel.readInt());
            p.setUSDAZone(parcel.readInt());
            p.setType(parcel.readString());
            p.setDetails(parcel.readString());
            p.setSize(parcel.readInt());
            p.setCommonName(parcel.readString());
            p.setBotanicalName(parcel.readString());

            return p;
        }

        @Override
        public Plant[] newArray(int i) {
            return new Plant[i];
        }
    };

    public static ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> plants = new ArrayList<>(ANIMALS.length);
        for (String ANIMAL : ANIMALS) {
            plants.add(new Plant(ANIMAL));
        }
        return plants;
    }

    private static String[] ANIMALS = {
            "pronghorn",
            "bunny",
            "dromedary",
            "fawn",
            "jackal",
            "guinea pig",
            "kitten",
            "rabbit",
            "ibex",
            "meerkat",
            "leopard",
            "blue crab",
            "starfish",
            "squirrel",
            "bison",
            "woodchuck",
            "ox",
            "grizzly bear",
            "chinchilla",
            "mynah bird",
            "polar bear",
            "vicuna",
            "mountain goat",
            "chipmunk",
            "buffalo",
            "ermine",
            "impala",
            "skunk",
            "goat",
            "okapi",
            "rooster",
            "walrus",
            "toad",
            "puma",
            "antelope",
            "parrot",
            "coati",
            "opossum",
            "parakeet",
            "doe",
            "ferret",
            "musk deer",
            "hamster",
            "bat",
            "basilisk",
            "ground hog",
            "fox",
            "gnu",
            "cow",
            "marten"};
}
