/* 
 * This file is part of the Tustan AR distribution 
 * (https://github.com/softserveinc-rnd/tustan-ar).
 * Copyright (c) 2017 Softserve Inc.
 *
 * Tustan 3D model Copyright 2017 Vasyl Rozhko
 * 3D model of the 5th period of the cliffside city-fortress Tustan's log 
 * constructions was created on the basis of the architectural analysis 
 * of graphic reconstructions by Mykhailo Rozhko (1939-2004), an architect,  
 * archeologist and researcher of Tustan. 
 * 3D model's creators: Vasyl Rozhko, Maksym Yasinskyi, Vasyl Dmytruk, 
 * Oleh Rybchynskyi and Andriy Dedyshyn.
 * 
 * This file is part of Tustan AR.
 *
 * Tustan AR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Tustan AR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Tustan AR. If not, see <http://www.gnu.org/licenses/>.
 */

package com.softserve.plugin.locale;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class LocalizationManager {
    public static LocalizationManager Instance = new LocalizationManager();
    private LocalizationManager(){}

    private Map<String, String> localizedText = new HashMap<String, String>();
    private String missingTextString = "Localized text not found";

    public static void LoadLocalizedText(String jsonString){
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray items = json.getJSONArray("items");
            for(int i=0; i< items.length(); i++){
                JSONObject localizationItem = items.getJSONObject(i);
                Instance.localizedText.put(localizationItem.getString("key"),localizationItem.getString("value"));
            }
        }
        catch(JSONException ex){
            Log.e(LocalizationManager.class.getSimpleName(), "Plugin read data incorrectly");
        }
    }

    public String GetLocalizedValue(String key){
        String result = missingTextString;
        if (localizedText.containsKey(key))
        {
            result = localizedText.get(key);
        }

        return result;
    }
}
