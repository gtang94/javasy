package io.github.gtang94.finejar.hutool;


import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadJsonToExcel {

    private static String readJsonData(String filename) {
        StringBuffer buffer = new StringBuffer();
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String str;
            while ((str = in.readLine()) != null) {
                buffer.append(str);
            }
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) {

        List<String> jsonFileList = Lists.newArrayList(
                "C:\\Users\\olivi\\Downloads\\ecforce_1.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_2.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_3.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_4.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_5.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_6.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_7.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_8.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_9.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_10.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_11.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_12.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_13.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_14.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_15.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_16.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_17.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_18.json",
                "C:\\Users\\olivi\\Downloads\\ecforce_19.json"
        );

//        List<String> header = Lists.newArrayList(
//                "id", "type", "number", "customer_id", "customer_number", "customer_labels", "product_labels", "product_name_with_tax"
//        );
        List<String> header = Lists.newArrayList(
                "受注ID", "受注番号", "顧客ID", "顧客番号", "顧客ラベル", "商品ラベル", "購入商品（商品名：印）"
        );

        List<List<String>> rows = Lists.newArrayList();
        rows.add(header);

        for (String jsonFile : jsonFileList) {
            String jsonString = readJsonData(jsonFile);
            JSONObject object = JSONObject.parseObject(jsonString);

            JSONArray data = object.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject dataJson = data.getJSONObject(i);

                String id = dataJson.getString("id");
                JSONObject attributes = dataJson.getJSONObject("attributes");

                List<String> row = Lists.newArrayList(
                        dataJson.getString("id"),
                        attributes.getString("number"),
                        attributes.getString("customer_id"),
                        attributes.getString("customer_number"),
                        attributes.getString("customer_labels"),
                        attributes.getString("product_labels"),
                        attributes.getString("product_name_with_tax")
                );

                rows.add(row);
            }

        }

        BigExcelWriter writer= ExcelUtil.getBigWriter("C:\\Users\\olivi\\Downloads\\ecforce_orders.xlsx");
        writer.write(rows);
        writer.close();
    }
}
