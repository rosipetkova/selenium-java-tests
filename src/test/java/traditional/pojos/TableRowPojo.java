package traditional.pojos;

public class TableRowPojo {
    private String status;
    private String date;
    private String description;
    private String category;
    private String amount;

    public TableRowPojo(String status, String date, String description, String category, String amount) {
        this.status = status;
        this.date = date;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    public Float getCastedAmount() {
        return Float.parseFloat(amount.replaceAll("[^\\d.\\-]", ""));
    }
}
