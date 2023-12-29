module Cal_RuRa_avec_interface {
	exports main;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens view to javafx.graphics, javafx.fxml;
}
