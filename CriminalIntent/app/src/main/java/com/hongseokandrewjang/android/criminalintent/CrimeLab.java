package com.hongseokandrewjang.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;


public class CrimeLab {
    private static CrimeLab crimeLab;
    private ArrayList<Crime> crimes;

    public  static CrimeLab get(Context context){
        if(crimeLab == null)
            crimeLab = new CrimeLab(context);
        return crimeLab;
    }

    private CrimeLab(Context context){
        crimes = new ArrayList<>();
        for(int i=0;i<100;i++){
            Crime crime = new Crime();
            crime.setTitle("범죄 #"+i);
            crime.setSolved(i%2==0);
            crimes.add(crime);
        }
    }

    public ArrayList<Crime> getCrimes(){
        return crimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : crimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
