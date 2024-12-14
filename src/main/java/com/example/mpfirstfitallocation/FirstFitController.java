package com.example.mpfirstfitallocation;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class FirstFitController {


    @FXML
    private TextField txtProcessSize;

    @FXML
    public TableView<MemoryBlock> tableID;

    @FXML
    private TableColumn<MemoryBlock, Integer> colId;

    @FXML
    private TableColumn<MemoryBlock, Integer> colBlockSize;

    @FXML
    private TableColumn<MemoryBlock, Integer> colProcessSize;

    @FXML
    private TableColumn<MemoryBlock, Boolean> colAllocated;

    // Database connection settings
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FirstFitAllocation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ACPT";

    @FXML
    public void initialize() {
        // Initialize table columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBlockSize.setCellValueFactory(new PropertyValueFactory<>("blockSize"));
        colProcessSize.setCellValueFactory(new PropertyValueFactory<>("processSize"));
        colAllocated.setCellValueFactory(new PropertyValueFactory<>("allocated"));

        // Load initial data
        refreshTable();
    }

    @FXML
    private void allocateProcessAction() {
        try {
            int processSize = Integer.parseInt(txtProcessSize.getText());
            allocateProcess(processSize);
            refreshTable();
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: " + txtProcessSize.getText());
        }
    }

    private void allocateProcess(int processSize) {
        String selectQuery = "SELECT * FROM memory_blocks WHERE block_size >= ? AND allocated = FALSE ORDER BY id";
        String updateQuery = "UPDATE memory_blocks SET process_size = ?, allocated = TRUE WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            selectStmt.setInt(1, processSize);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int blockId = rs.getInt("id");
                updateStmt.setInt(1, processSize);
                updateStmt.setInt(2, blockId);
                updateStmt.executeUpdate();
            } else {
                System.out.println("No suitable block found for process size: " + processSize);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void refreshTable() {
        ObservableList<MemoryBlock> blocks = FXCollections.observableArrayList();
        String query = "SELECT * FROM memory_blocks";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FirstFitAllocation", "root", "ACPT");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                blocks.add(new MemoryBlock(
                        rs.getInt("id"),
                        rs.getInt("block_size"),
                        rs.getInt("process_size"),
                        rs.getBoolean("allocated")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableID.setItems(blocks);
    }
}
