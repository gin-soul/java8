package com.naruto.http;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.naruto.http.HttpUtils.getPutByHttpClient;

public class EquipmentThread implements Runnable {

    private List<String> personIds;

    private Collection<Equipment> equipmentList;

    public EquipmentThread(List<String> personIds, Collection<Equipment> equipmentList) {
        this.personIds = personIds;
        this.equipmentList = equipmentList;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public Collection<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(Collection<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public void run() {
        long count = 0;
        for (String personId : personIds) {
            count++;
            System.out.println("count=" + count + " thread" + Thread.currentThread().getName());
            if (StringUtils.isNotBlank(personId)) {
                try {
                    System.out.println(personId);
                    String url = "http://10.18.31.101:8088/uniteApi/serviceConfigTest?testConfig=%5B%7B%22paramName%22%3A%22S_DC_VB_FORGCODE%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VB_FCOMPANYNAME%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VS_IDNO%22%2C%22paramValue%22%3A%22"+ personId +"%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_DC_VS_NAME%22%2C%22paramValue%22%3A%22%22%2C%22mustInput%22%3Afalse%7D%2C%7B%22paramName%22%3A%22S_ET_VB_BUYERCODE%22%2C%22paramValue%22%3A%22%22%2C%22mustInput%22%3Afalse%7D%5D&uuid=b10def1e-2438-4ef9-ab6e-3020dd250828";
                    String json = getPutByHttpClient(url, null, null);
                    Map<String, String> read = JsonPath.read(json, "$.data");
                    equipmentList.add(new Equipment(
                            personId,
                            read.get("S_ET_VD_PRODUCTIDLIST"),
                            read.get("N_ET_VD_PRODUCTIDCNT"),
                            read.get("N_ET_VD_TOTSYBBHCNT"),
                            read.get("F_ET_VD_ZEJJDATEFIRSTYEARS"),
                            read.get("F_ET_VD_ZEJJDATELASTYEARS"),
                            read.get("N_ET_VD_ZEZBSTABNCNT"),
                            read.get("N_ET_VD_ZEZBSTABWCNT"),
                            read.get("N_ET_VD_ZEZBSTAWKJCNT"),
                            read.get("N_ET_VD_ZEHDSBUSTAKYCNT"),
                            read.get("N_ET_VD_ZEHDSBUSTAYCCNT"),
                            read.get("N_ET_VD_ZEHDSBUSTATSCNT"),
                            read.get("N_ET_VD_ZEHDYCRJGSCNT"),
                            read.get("N_ET_VD_ZEHDYCFWKCCNT"),
                            read.get("N_ET_VD_ZEHDYCGNZHWCNT"),
                            read.get("N_ET_VD_ZEHDYCTYCNT"),
                            read.get("N_ET_VD_ZEHDYCJZDSHGCNT"),
                            read.get("N_ET_VD_ZEHDYCTHJCNT"),
                            read.get("N_ET_VD_ZEHDYCDJCNT"),
                            read.get("N_ET_VD_ZEHDYCBACNT"),
                            read.get("N_ET_VD_ZEEQSTAZCCNT"),
                            read.get("N_ET_VD_ZEEQSTASJCNT"),
                            read.get("N_ET_VD_ZEEQSTAYCCNT")
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
