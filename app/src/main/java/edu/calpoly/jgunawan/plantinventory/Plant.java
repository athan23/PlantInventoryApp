package edu.calpoly.jgunawan.plantinventory;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Plant implements Parcelable {

    private Cell botanicalName;
    private String botanicalNameString;
    private Cell commonName;
    private String commonNameString;
    private Cell growth;
    private String growthString;
    private int size;
    private Cell details;
    private String detailsString;
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
        this.commonNameString = commonNameString;
    }

    public Cell getBotanicalName() {
        return botanicalName;
    }

    public Cell getCommonName() {
        return commonName;
    }

    public int getSize() {
        return size;
    }

    public Cell getDetails() {
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

    public void setBotanicalName(Cell botanicalName) {
        this.botanicalName = botanicalName;
    }

    public void setCommonName(Cell commonName) {
        this.commonName = commonName;
    }

    public void setDetails(Cell details) {
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
        parcel.writeString(botanicalName.getStringCellValue());
        parcel.writeString(commonName.getStringCellValue());
        parcel.writeInt(size);
        parcel.writeString(details.getStringCellValue());
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

            /*
            boolean[] arr = new boolean[1];
            parcel.readBooleanArray(arr);
            p.setSaleable(arr[0]);

            p.setNumAvailable(parcel.readInt());
            p.setUSDAZone(parcel.readInt());
            p.setType(parcel.readString());
            p.setDetailsString(parcel.readString());
            p.setSize(parcel.readInt());
            */
            p.setCommonNameString(parcel.readString());
            //p.setBotanicalNameString(parcel.readString());

            return p;
        }

        @Override
        public Plant[] newArray(int i) {
            return new Plant[i];
        }
    };

    public static LinkedList<Plant> getAllPlants()
        throws IOException, FileNotFoundException{
//        ArrayList<Plant> plants = new ArrayList<>(ANIMALS.length);
//        for (String ANIMAL : ANIMALS) {
//            plants.add(new Plant(ANIMAL));
//        }

        LinkedList<Plant> plants = new LinkedList<Plant>();
        FileInputStream in = new FileInputStream("ggf12 barcode.xlsx");
        Plant p = new Plant();

        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();
        Row row;

        while(rowIterator.hasNext()) {
            row = rowIterator.next();
            Cell cell = row.getCell(0);
            if(cell.getStringCellValue().length() > 0) {
                p.setCommonNameString(cell.getStringCellValue());
                plants.add(p);
            }
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

    public String getBotanicalNameString() {
        return botanicalNameString;
    }

    public void setBotanicalNameString(String botanicalNameString) {
        this.botanicalNameString = botanicalNameString;
    }

    public String getCommonNameString() {
        return commonNameString;
    }

    public void setCommonNameString(String commonNameString) {
        this.commonNameString = commonNameString;
    }

    public Cell getGrowth() {
        return growth;
    }

    public void setGrowth(Cell growth) {
        this.growth = growth;
    }

    public String getGrowthString() {
        return growthString;
    }

    public void setGrowthString(String growthString) {
        this.growthString = growthString;
    }

    public String getDetailsString() {
        return detailsString;
    }

    public void setDetailsString(String detailsString) {
        this.detailsString = detailsString;
    }
}
