package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NewebayResonse extends AuditableEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("MerchantID")
    private String merchantID;

    @JsonProperty("TradeInfo")
    @Lob
    @Column( length = Integer.MAX_VALUE )
    private String tradeInfo;

    @JsonProperty("TradeSha")
    private String tradeSha;

    @JsonProperty("Version")
    private String version;
}
