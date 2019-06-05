package com.naruto.json;

import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /*HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("2", 4);
        hashMap.put("3", 9);
        hashMap.forEach((eleMap, mapping) -> {
            System.out.println(eleMap);
            System.out.println(mapping);
        });*/

        String json = "{\"store\": {\"book\": [{\"category\": \"reference\",\"author\": \"Nigel Rees\",\"title\": \"Sayings of the Century\",\"price\": 8.95},{\"category\": \"fiction\",\"author\": \"Evelyn Waugh\",\"title\": \"Sword of Honour\",\"price\": 12.99,\"isbn\": \"0-553-21311-3\"}],\"bicycle\": {\"color\": \"red\",\"price\": 19.95}}}";

        List read = JsonPath.read(json, "$..isbn");
        read.forEach(e -> System.out.println(e));

    }
}

/*
{ "store": {
    "book": [
      { "category": "reference",
        "author": "Nigel Rees",
        "title": "Sayings of the Century",
        "price": 8.95
      },
      { "category": "fiction",
        "author": "Evelyn Waugh",
        "title": "Sword of Honour",
        "price": 12.99,
        "isbn": "0-553-21311-3"
      }
    ],
    "bicycle": {
      "color": "red",
      "price": 19.95
    }
  }
}
 */
