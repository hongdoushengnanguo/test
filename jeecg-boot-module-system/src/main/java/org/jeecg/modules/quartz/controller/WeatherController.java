package org.jeecg.modules.quartz.controller;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import org.jeecg.common.api.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/weather")
public class WeatherController {
    /**
     * 以Json返回
     *
     * @param City
     * @throws Exception
     */
    @GetMapping(value = "/wethernews")
    public Result<?> weather (String City) throws Exception {
        HashMap<String ,String> map=new HashMap<>();
        // 参数url化
        String city = URLEncoder.encode(City, "utf-8");
        // 拼地址
        String apiUrl = String.format("https://www.sojson.com/open/api/weather/json.shtml?city=%s", city);
        // 开始请求
        URL url = new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        // 这里转换为String
        String result = IOUtils.toString(input, "utf-8");
        // 输出
        map.put("天气",result);
       return Result.ok(map);

    }


}
