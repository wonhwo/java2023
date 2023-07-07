package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel statusbar;

    public Tetris() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        Board board = new Board(this);
        add(board);
        board.start();

        setTitle("reppy's Tetris");
        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    public JLabel getStatusBar() {

        return statusbar;
    }
    

    public static void main(String[] args) {


        
    }
    public void pu() {
    	 EventQueue.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
    	 });
    }
    }