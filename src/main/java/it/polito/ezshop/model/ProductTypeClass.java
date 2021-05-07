package it.polito.ezshop.model;

public class ProductTypeClass implements it.polito.ezshop.data.ProductType {

    private int quantity;
    private String location;
    private String note;
    private String productDescription;
    private String barCode;
    private Double pricePerUnit;
    private int id;

    public ProductTypeClass(int _id, String _productDescription, String _barCode, Double _pricePerUnit, String _note) {
        super();
        this.productDescription = _productDescription;
        this.barCode = _barCode;
        this.pricePerUnit = _pricePerUnit;
        this.note = _note;
        this.id = _id;
    }

    public static boolean VerifyBarCode(String barCode) {

        if(barCode == null)
            return false;

        int length = barCode.length();

        if(length > 14 || length < 12)
            return false;


        int[] barCodeNumber = new int[length];
        int i = 0;

        for (char element : barCode.toCharArray()) {
            if(Character.isDigit(element)){
                if(i%2 == length%2) {
                    barCodeNumber[i] = Character.getNumericValue(element) * 3;
                }
                else {
                    barCodeNumber[i] = Character.getNumericValue(element);
                }
                i++;
            }
            else 
                return false;
        }

        int checkSum = 0;
        for(int value : barCodeNumber){
            checkSum += value;
        }

        if(checkSum%10 != 0) {
            return false;
        }

        return true;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getProductDescription() {
        return this.productDescription;
    }

    @Override
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String getBarCode() {
        return this.barCode;
    }

    @Override
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public Double getPricePerUnit() {
        return this.pricePerUnit;
    }

    @Override
    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
}
