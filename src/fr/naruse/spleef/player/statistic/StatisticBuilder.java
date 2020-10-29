package fr.naruse.spleef.player.statistic;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class StatisticBuilder {

    private static final Gson GSON = new Gson();
    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>(){}.getType();

    public static String toJson(Map<StatisticType, Integer> map){
        if(map == null){
            map = Maps.newHashMap();
            for (StatisticType value : StatisticType.values()) {
                map.put(value, 0);
            }
        }
        return GSON.toJson(map);
    }

    public static void fromJson(String json, Map<StatisticType, Integer> statisticMap) {
        Map<String, Object> propertyMap = GSON.fromJson(json, MAP_TYPE);
        try{
            for (String s : propertyMap.keySet()) {
                StatisticType statisticType = StatisticType.valueOf(s);
                statisticMap.put(statisticType, Integer.parseInt(propertyMap.get(s).toString()));
            }
        }catch (Exception e) { }

        for (StatisticType value : StatisticType.values()) {
            if(!statisticMap.containsKey(value)){
                statisticMap.put(value, 0);
            }
        }
    }
}
