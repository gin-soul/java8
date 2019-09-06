package com.naruto.json;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

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

public class ResolveJsonByAli {
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

        //查找所有节点下的 isbn 的value
        List readIsbn = JsonPath.read(json, "$..isbn");
        readIsbn.forEach(e -> System.out.println(e));

        System.out.println("- - - - - - - - - - - - - - - -");

        //查找所有节点下的 book
        List readBook = JsonPath.read(json, "$..book");
        readBook.forEach(e -> System.out.println(e));

        System.out.println("- - - - - - - - - - - - - - - -");

        //查找所有节点下的 book 下的 author
        List readBookAuthor = JsonPath.read(json, "$..book[*].author");
        readBookAuthor.forEach(e -> System.out.println(e));
    }
}
