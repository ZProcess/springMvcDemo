package com;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerTest {
    @RequestMapping("/test")
    @ResponseBody
    public String test(Map<String,String> map){
      map = new HashMap();
      map.put("1","z");
      map.put("2","j");
      map.put("3","c");
      return JSONObject.toJSONString(map);
    }
}
