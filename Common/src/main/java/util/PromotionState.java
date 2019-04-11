package util;

public enum PromotionState {
    DRAFT("草稿", "-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:green;")
    , SAVED("存档", "-fx-text-fill: white;-fx-background-radius: 10; -fx-background-color:blue;");

    public final String chineseName, buttonStyle;

    PromotionState(String chineseName, String buttonStyle) {
        this.chineseName = chineseName;
        this.buttonStyle = buttonStyle;
    }

}
