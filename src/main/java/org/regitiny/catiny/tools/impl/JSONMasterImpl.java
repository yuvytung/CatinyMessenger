package org.regitiny.catiny.tools.impl;

import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;
import org.regitiny.catiny.tools.JSONMaster;
import org.regitiny.catiny.tools.constant.StringPool;

@Log4j2
public class JSONMasterImpl implements JSONMaster
{

  private JSONObject jsonObject;

  private String defaultValue = null;

  public JSONMasterImpl(JSONObject jsonObject)
  {
    this.jsonObject = jsonObject;
  }

  public JSONMasterImpl(String json)
  {
    this.jsonObject = new JSONObject(json);
  }

  public JSONMasterImpl defaultValue(String defaultValue)
  {
    this.defaultValue=defaultValue;
    return this;
  }

  public String getStringByKey(String keyInput)
  {

    if (keyInput == null )
      return defaultValue;
    String keys[] = keyInput.split(StringPool.DOT_IN_REGEX);

    try
    {
      JSONObject jsonObject = this.jsonObject;
      for (int i = 0; i < keys.length - 1; i++)
      {
        String key = keys[i];
        if (jsonObject.has(key) && !jsonObject.getJSONObject(key).isEmpty())
          jsonObject = jsonObject.getJSONObject(key);
        else
          return defaultValue;
      }
      String lastKey = keys[keys.length - 1];
      if (jsonObject.has(lastKey) && !jsonObject.getString(lastKey).isEmpty())
        return jsonObject.getString(lastKey);
    }
    catch (JSONException jsonException)
    {
      log.error("error JSONException", jsonException);
      return defaultValue;
    }
    return defaultValue;
  }


  public static void main(String[] args)
  {
    String json = "{\n" +
      "    \"a\":{\n" +
      "        \"b\":\n" +
      "        {\n" +
      "            \"c\":\n" +
      "            {\n" +
      "                \"d\":\"1234\"\n" +
      "            }\n" +
      "        }\n" +
      "    }\n" +
      "}";

    JSONMasterImpl jsonMaster = new JSONMasterImpl(json).defaultValue("hí hí");
    String j = jsonMaster.getStringByKey("a.b.c.d");
    System.out.println(j);
  }


}
