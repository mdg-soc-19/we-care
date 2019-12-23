package com.example.wecare;

public class Med {

    public String MedName="";
    public String MedDose="";

    public Med() {
    }

    public Med(String Medname, String Meddose) {
        this.MedName = Medname;
        this.MedDose = Meddose;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String mTitle) {
        this.MedName = MedName;
    }
}
