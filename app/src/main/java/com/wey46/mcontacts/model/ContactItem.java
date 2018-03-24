package com.wey46.mcontacts.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.wey46.mcontacts.database.ContactsTable;

import java.util.UUID;

public class ContactItem implements Parcelable {
    private String UId;
    private String LName;
    private String FName;
    private String Address1;
    private String Address2;
    private String City;
    private String State;
    private int Zip;
    private String Country;
    private String Phone;
    private String Email;

    public ContactItem() { }

    public ContactItem(String LName, String FName, String address1, String address2,
                       String city, String state, int zip, String country, String phone, String email) {

        UId = UUID.randomUUID().toString();
        this.LName = LName;
        this.FName = FName;
        Address1 = address1;
        Address2 = address2;
        City = city;
        State = state;
        Zip = zip;
        Country = country;
        Phone = phone;
        Email = email;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getUId() {
        return UId;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        Zip = zip;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "UId='" + UId + '\'' +
                ", LName='" + LName + '\'' +
                ", FName='" + FName + '\'' +
                ", Address1='" + Address1 + '\'' +
                ", Address2='" + Address2 + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip=" + Zip +
                ", Country='" + Country + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(11);
        values.put(ContactsTable.COLUMN_ID, UId);
        values.put(ContactsTable.COLUMN_LNAME, LName);
        values.put(ContactsTable.COLUMN_FNAME, FName);
        values.put(ContactsTable.COLUMN_ADDRESS1, Address1);
        values.put(ContactsTable.COLUMN_ADDRESS2, Address2);
        values.put(ContactsTable.COLUMN_CITY, City);
        values.put(ContactsTable.COLUMN_STATE, State);
        values.put(ContactsTable.COLUMN_ZIP, Zip);
        values.put(ContactsTable.COLUMN_COUNTRY, Country);
        values.put(ContactsTable.COLUMN_PHONE, Phone);
        values.put(ContactsTable.COLUMN_EMAIL, Email);


        return values;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UId);
        dest.writeString(this.LName);
        dest.writeString(this.FName);
        dest.writeString(this.Address1);
        dest.writeString(this.Address2);
        dest.writeString(this.City);
        dest.writeString(this.State);
        dest.writeInt(this.Zip);
        dest.writeString(this.Country);
        dest.writeString(this.Phone);
        dest.writeString(this.Email);
    }

    protected ContactItem(Parcel in) {
        this.UId = in.readString();
        this.LName = in.readString();
        this.FName = in.readString();
        this.Address1 = in.readString();
        this.Address2 = in.readString();
        this.City = in.readString();
        this.State = in.readString();
        this.Zip = in.readInt();
        this.Country = in.readString();
        this.Phone = in.readString();
        this.Email = in.readString();
    }

    public static final Parcelable.Creator<ContactItem> CREATOR = new Parcelable.Creator<ContactItem>() {
        @Override
        public ContactItem createFromParcel(Parcel source) {
            return new ContactItem(source);
        }

        @Override
        public ContactItem[] newArray(int size) {
            return new ContactItem[size];
        }
    };
}
