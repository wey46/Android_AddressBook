package com.wey46.mcontacts.sample;

import com.wey46.mcontacts.model.ContactItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<ContactItem> contactItemList;
    public static Map<String, ContactItem> contactItemMap;

    private static void addItem(ContactItem item){
        contactItemList.add(item);
        contactItemMap.put(item.getUId(),item);
    }

    static {
        contactItemList = new ArrayList<>();
        contactItemMap = new HashMap<>();
        addItem(new ContactItem("Will","Smith", "283 S Fake Rd",
                "", "NYC", "NY", 10010, "USA",
                "303202101","W.S.unknown@fake.com"));
        addItem(new ContactItem("Bill","Gates", "399 X Moon Rd",
                "", "Pittsburgh", "PA", 15213, "USA",
                "8008208820","B.G.notsure@real.com"));
        addItem(new ContactItem("Micheal","Wilson", "987 N Hollywood",
                "Rm 808", "Philly", "PA", 23508, "USA",
                "2377154811",""));
    }

}
