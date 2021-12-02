package com.example.demo;

import idv.rennnhong.neweb.Trade;
import idv.rennnhong.neweb.TradeBuilder;
import idv.rennnhong.neweb.response.TradeInfo;
import idv.rennnhong.neweb.response.TradeInfoResult;
import idv.rennnhong.neweb.response.parser.TradeInfoParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class NewebController {

    public static String endpoit = "https://ccore.newebpay.com/MPG/mpg_gateway";
    public static String merchantId = "MS327178038";

    public static String key = "deckgFiN1n9tuzsFoNBCxnYBy8BJHQsx";
    public static String iv = "CwpcSlJTNNCtciWP";

    @GetMapping
    public String index(Model model) throws Exception {

        TradeBuilder tradeBuilder = TradeBuilder.newBuilder(merchantId, key, iv);
        tradeBuilder.setAmt("52");
        tradeBuilder.setItemDesc("TEST");
        tradeBuilder.setEmail("changerui%40gmail.com");

        Trade trade = tradeBuilder.build();

        model.addAttribute("endpoint", endpoit);
        model.addAttribute("MerchantID", trade.getMerchantID());
        model.addAttribute("TradeInfo", trade.getTradeInfo());
        model.addAttribute("TradeSha", trade.getTradeSha());
        model.addAttribute("Version", trade.getVersion());

        return "index";
    }

    @PostMapping("notify")
    @ResponseBody
    public void notify(@RequestBody String notify) throws Exception {

        Map<String, String> map = new HashMap<>();
        String[] split = notify.split("&");
        for (String s : split) {
            String[] kv = s.split("=");
            map.put(kv[0], kv[1]);
        }

        System.out.println(MessageFormat.format("接收到藍新的繳款結果通知: {0}", notify));


        TradeInfoParser parser = new TradeInfoParser(merchantId, key, iv);
        TradeInfo tradeInfo = parser.parse(map.get("TradeInfo"));

        TradeInfoResult result = tradeInfo.getResult();

        System.out.println(MessageFormat.format("TradeInfo解碼結果: {0}", result));

    }


}
