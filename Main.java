import controller.ParqueController;
import view.ParqueView;

public class Main {

    public static void main(String[] args) {
        ParqueView parqueView = new ParqueView();

        ParqueController parqueController = new ParqueController(parqueView);
        parqueController.iniciar();
    }
}