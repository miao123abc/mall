package edu.dlut.catmall.thirdparty.component;

import edu.dlut.catmall.thirdparty.util.HttpUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR: raymond
 * @DATETIME: 2020/5/17  16:54
 * DESCRIPTION:
 **/
@Component
@ConfigurationProperties(prefix = "spring.config.sms")
@Data
public class SMSComponent {

    private String host;
    private String path;
    private String appcode;
    private String templateId;

    public void sendSMSCode(String phone, String code) {
        String method = "POST";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("receive", phone);
        querys.put("tag", code);
        querys.put("templateId", templateId);
        Map<String, String> bodys = new HashMap<>();

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

