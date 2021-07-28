package com.apollo.pharmacy.ocr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Global_api_request {
    @SerializedName("KEY")
    @Expose
    private String kEY;
    @SerializedName("DEVICEID")
    @Expose
    private String dEVICEID;

    public String getKEY() {
        return kEY;
    }

    public void setKEY(String kEY) {
        this.kEY = kEY;
    }

    public String getDEVICEID() {
        return dEVICEID;
    }

    public void setDEVICEID(String dEVICEID) {
        this.dEVICEID = dEVICEID;
    }

}
