package org.qas.jbehave.example;

import org.apache.commons.lang3.StringUtils;
import org.qas.jbehave.example.model.PhoneInfo;

import java.util.ArrayList;


public class PhoneStore {
    ArrayList<PhoneInfo> phoneList = new ArrayList<PhoneInfo>();


    public void addPhone(PhoneInfo phone) {
        phoneList.add(phone);
    }
    public void emptyStore() {
        phoneList.clear();
    }
    public ArrayList<PhoneInfo> searchByPriceGreaterOrEqual(double price) {
        ArrayList<PhoneInfo> result = new ArrayList<PhoneInfo>();
        for (int i=0; i<this.phoneList.size(); i++) {
            PhoneInfo phone = this.phoneList.get(i);
            if (1 <= Double.compare(phone.getPrice(), price)){
                result.add(phone);
            }
        }
        return result;
    }
    public ArrayList<PhoneInfo> searchByName(String name) {
        ArrayList<PhoneInfo> result = new ArrayList<PhoneInfo>();
        for (int i=0; i<this.phoneList.size(); i++) {
            PhoneInfo phone = this.phoneList.get(i);
            if (StringUtils.containsIgnoreCase(phone.getName(), name)){
                result.add(phone);
            }
        }
        return result;
//        return this.phoneList; // demo case failed
    }
}

