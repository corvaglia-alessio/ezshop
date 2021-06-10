package it.polito.ezshop.model;

public class Product {

    private String RFID;
    private Integer ProductId;

    public Product(String rfid, Integer productid){
        this.RFID = rfid;
        this.ProductId = productid;
    }

    public String getRFID(){
        return this.RFID;
    }

    public Integer getProductId(){
        return this.ProductId;
    }

    private Long getLongRFID(){
        return Long.parseLong(this.RFID);
    }

    private String padToRFID(Long rfid){
        String result = rfid.toString();

        if (result.length() > 12){
            return result.substring(0, 12);
        }
        else {
            int diff = 12 - result.length();
            String pad = "";
            for (int i = 0; i < diff; i++){
                pad += "0";
            }
            return pad+result;
        }
    }

    public String getRFIDFromOffest(int offset){
        Long rfid = this.getLongRFID();
        rfid += offset;
        return this.padToRFID(rfid);
    }
}
