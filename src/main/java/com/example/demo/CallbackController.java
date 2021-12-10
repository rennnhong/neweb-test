package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.rennnhong.neweb.response.TradeInfo;
import idv.rennnhong.neweb.response.TradeInfoResult;
import idv.rennnhong.neweb.response.parser.TradeInfoParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CallbackController {

    public static String endpoit = "https://ccore.newebpay.com/MPG/mpg_gateway";
    public static String merchantId = "MS327178038";

    public static String key = "deckgFiN1n9tuzsFoNBCxnYBy8BJHQsx";
    public static String iv = "CwpcSlJTNNCtciWP";

    @Autowired
    private NewebayResonseRepository repository;

    @PostMapping(value = "notify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void notify(@RequestBody String notify) throws Exception {

        Map<String, String> map = new HashMap<>();
        String[] split = notify.split("&");
        for (String s : split) {
            String[] kv = s.split("=");
            map.put(kv[0], kv[1]);
        }

        System.out.println(MessageFormat.format("接收到藍新的繳款結果通知: {0}", notify));

        String status = map.get("Status");
        String merchantID = map.get("MerchantID");
        String tradeInfo = map.get("TradeInfo");
        String tradeSha = map.get("TradeSha");
        String version = map.get("Version");

        NewebayResonse resonse = new NewebayResonse();
        resonse.setStatus(status);
        resonse.setMerchantID(merchantID);
        resonse.setTradeInfo(tradeInfo);
        resonse.setTradeSha(tradeSha);
        resonse.setVersion(version);

        repository.save(resonse);

//        TradeInfoParser parser = new TradeInfoParser(merchantId, key, iv);
//        TradeInfo tradeInfo = parser.parse(map.get("TradeInfo"));
//
//        TradeInfoResult result = tradeInfo.getResult();
//
//        System.out.println(MessageFormat.format("TradeInfo解碼結果: {0}", result));

    }


}
