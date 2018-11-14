package com.github.zahileoo.livedatademo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 实现Android序列化接口Parcelable的Person类
 * Created by zahi on 2018/11/12.
 */
public class Person implements Parcelable {

    private String name;
    private int age;
    private boolean male;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return male;
    }

    public Person(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
        male = in.readByte() == 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeByte((byte) (male ? 0 : 1));
    }
}
