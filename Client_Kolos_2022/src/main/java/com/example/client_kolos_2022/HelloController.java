package com.example.client_kolos_2022;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {

    @FXML
    public TextField filterField;
    @FXML
    public ListView<String> wordList;
    @FXML
    public Label wordCountLabel;
    private Client client;
    private ObservableList<String> allWords = FXCollections.observableArrayList();
    private ObservableList<String> filteredWords = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        wordList.setItems(filteredWords); // Ustawienie filtrowanej listy w ListView
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            applyFilter(newValue); // Ustawienie nasłuchiwacza na zmiany w filterField
        });
    }

    public void initializeClient() throws IOException {
        client = new Client(this);
        client.connect();
    }

    public void addWord(String wordWithTime) {
        allWords.add(wordWithTime); // Dodanie pełnej wiadomości do listy wszystkich słów
        if (passesFilter(wordWithTime)) {
            filteredWords.add(wordWithTime); // Dodanie pełnej wiadomości do listy filtrowanej, jeśli spełnia filtr
        }
        updateWordCountLabel();
    }

    private void applyFilter(String filterText) {
        filteredWords.clear();
        for (String word : allWords) {
            if (passesFilter(word)) {
                filteredWords.add(word);
            }
        }
        updateWordCountLabel();
    }

    private boolean passesFilter(String wordWithTime) {
        String filterText = filterField.getText().trim().toLowerCase();
        if (filterText.isEmpty()) {
            return true; // Brak filtra, wszystkie słowa przechodzą
        }
        return wordWithTime.toLowerCase().contains(filterText); // Sprawdzenie, czy pełna wiadomość zawiera filtr
    }

    private void updateWordCountLabel() {
        wordCountLabel.setText(String.valueOf(filteredWords.size())); // Aktualizacja liczby wyświetlanych słów
    }
}
