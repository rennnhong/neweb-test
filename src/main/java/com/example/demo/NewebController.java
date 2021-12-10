package com.example.demo;

import idv.rennnhong.neweb.Trade;
import idv.rennnhong.neweb.TradeBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


        //        model.addAttribute("endpoint", endpoit);
//        model.addAttribute("MerchantID", "MS327178038");
//        model.addAttribute("TradeInfo", "BE381C081EFB710E93A2FCD42A0908760298EEC3743453F3BC04D2252FBD35705DB83926218DCC2145E05D546DC3DDEA9E0B257DA74FB59F74C6E3BCC4E225832817CC98F73B9DD02EBB119251725AE0B5AF50DF545C19A4FA927AAA8260D1E519DC8DDA328208AADF11E753819D8CC3DE6B144FA39E42F4AD7BCD0408DB6B85322567F5196EADBE8DB56F67AE91DD41723968D06097289DB71190BFE33986FF");
//        model.addAttribute("TradeSha", "62C77D0BBED95C30F1A60039AB69C73DA64C84DE99840505E429EA3B7EDBB2DB");
//        model.addAttribute("Version", "1.6");

        return "index";
    }



}
