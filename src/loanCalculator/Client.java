package loanCalculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Panel 1
        BorderPane paneForTextField1 = new BorderPane();
        paneForTextField1.setLeft(new Label("Annual Interest Rate: "));

        TextField tf1 = new TextField();
        tf1.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField1.setCenter(tf1);

        //Panel 2
        BorderPane paneForTextField2 = new BorderPane();
        paneForTextField2.setLeft(new Label("Number of years: "));

        TextField tf2 = new TextField();
        tf2.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField2.setCenter(tf2);

        //Panel 3
        BorderPane paneForTextField3 = new BorderPane();
        paneForTextField3.setLeft(new Label("Loan amount: "));

        TextField tf3 = new TextField();
        tf3.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField3.setCenter(tf3);

        Button submitButton = new Button("Submit");
        VBox inputVBox = new VBox(paneForTextField1, paneForTextField2, paneForTextField3);
        HBox inputHBox = new HBox(inputVBox, submitButton);

        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        ta.setMaxWidth(400);
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(inputHBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        submitButton.setOnAction(e -> {
            try {
                // Get the radius from the text field
                double AIR = Double.parseDouble(tf1.getText().trim());
                double NOY = Double.parseDouble(tf2.getText().trim());
                double LA = Double.parseDouble(tf3.getText().trim());

                Loan loan = new Loan(AIR, NOY, LA);

                Socket socket = new Socket("localhost", 8000);
                ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

                toServer.writeObject(loan);
                ObjectInputStream inputFromServer = new ObjectInputStream(socket.getInputStream());
                String message = (String)inputFromServer.readObject();
                ta.appendText(message+"\n");
            }
            catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex);
            }
        });
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
