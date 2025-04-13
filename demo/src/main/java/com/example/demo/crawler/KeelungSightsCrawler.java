// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.demo.crawler;

import com.example.demo.model.Sight;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KeelungSightsCrawler {
    private static final String BASE_URL = "https://www.travelking.com.tw";
    private static final String KEELUNG_URL = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";

    public KeelungSightsCrawler() {
    }

    public List<Sight> getItems(String targetZone) {
        List<Sight> sights = new ArrayList();

        try {
            Document doc = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").userAgent("Mozilla/5.0").get();
            Elements sightLinks = doc.select(".box a");
            Iterator var5 = sightLinks.iterator();

            while(true) {
                Sight sight;
                do {
                    if (!var5.hasNext()) {
                        return sights;
                    }

                    Element link = (Element)var5.next();
                    String sightName = link.text();
                    String sightUrl = "https://www.travelking.com.tw" + link.attr("href");
                    sight = this.parseSightDetail(sightName, sightUrl);
                } while(!sight.getZone().equals(targetZone) );

                sights.add(sight);
            }
        } catch (IOException var10) {
            var10.printStackTrace();
            return sights;
        }
    }

    private Sight parseSightDetail(String sightName, String sightUrl) {
        try {
            Document detailDoc = Jsoup.connect(sightUrl).userAgent("Mozilla/5.0").get();
            Sight sight = new Sight(sightName, sightUrl);

            // 1. 地區
            Elements zoneElements = detailDoc.select(".bc_last");
            String zone = zoneElements.isEmpty() ? "未知" : zoneElements.text().trim();
            sight.setZone(zone);

            // 2. 地址
            Elements addressElements = detailDoc.select("span[property=vcard:street-address]");
            String address = addressElements.isEmpty() ? "無地址資訊" : addressElements.text().trim();
            sight.setAddress(address);

            // 3. 描述
            Elements descElements = detailDoc.select("div.text");
            String description = descElements.isEmpty() ? "無描述" : descElements.text().trim();
            sight.setDescription(description);

            // 4. 分類
            Elements categoryElements = detailDoc.select("span[property=rdfs:label]");
            String category = categoryElements.isEmpty() ? "無類別" : categoryElements.text().trim();
            sight.setCategory(category);

            // ✅ 5. 照片網址（新增）
            Elements imgElements = detailDoc.select("meta[itemprop=image]");
            String photoUrl = imgElements.isEmpty() ? "無圖片" : imgElements.attr("abs:content");
            sight.setPhotoUrl(photoUrl);


            return sight;

        } catch (IOException e) {
            System.out.println("解析失敗：" + sightUrl);
            return new Sight(sightName, sightUrl);
        }
    }

}
