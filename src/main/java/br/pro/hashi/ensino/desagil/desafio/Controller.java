package br.pro.hashi.ensino.desagil.desafio;

import br.pro.hashi.ensino.desagil.desafio.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.annotation.Target;

public class Controller implements KeyListener, ActionListener {
    private final Model model;
    private final View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void keyTyped(KeyEvent event) {
        // Neste programa, não é necessário definir o que o controlador
        // faz quando um caractere é digitado, mas implementar KeyListener
        // obriga esse método a existir. Então deixamos vazio.
    }


    @Override
    public void keyPressed(KeyEvent event) {
        HumanPlayer humanPlayer = model.getHumanPlayer();

        Player player = model.getWinner();

        // Para agir de acordo com a tecla que foi pressionada, comparamos o key code do evento com as
        // constantes disponíveis na classe KeyEvent. Uma lista dessas constantes pode ser vista em
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/event/KeyEvent.html.
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(player == null) {
                    humanPlayer.moveUp();
                    break;
                }

            case KeyEvent.VK_RIGHT:
                if(player == null) {
                    humanPlayer.moveRight();
                    break;
                }

            case KeyEvent.VK_DOWN:
                if(player == null) {
                    humanPlayer.moveDown();
                    break;
                }

            case KeyEvent.VK_LEFT:
                if(player == null) {
                    humanPlayer.moveLeft();
                    break;
                }

        }

        view.repaint();
    }


    @Override
    public void keyReleased(KeyEvent event) {
        // Neste programa, não é necessário definir o que o controlador
        // faz quando uma tecla é solta, mas implementar KeyListener
        // obriga esse método a existir. Então deixamos vazio.
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        CpuPlayer cpuPlayer = model.getCpuPlayer();
        HumanPlayer humanPlayer = model.getHumanPlayer();
        Element target = model.getTarget();

        int targetColumn = target.getCol();
        int targetRow = target.getRow();

        int humanColumn = humanPlayer.getCol();
        int humanRow = humanPlayer.getRow();

        int cpuColumn = cpuPlayer.getCol();
        int cpuRow = cpuPlayer.getRow();

        if (humanColumn == targetColumn){
            if (humanRow == targetRow){
                System.out.println("HUMAN PLAYER WINS");
                model.setWinner(humanPlayer);
            }
        }

        if (cpuColumn == targetColumn) {
            if (cpuRow == targetRow){
                System.out.println("CPU PLAYER WINS");
                model.setWinner(cpuPlayer);
            }
        }

        Player player = model.getWinner();

        if (player == null){
            cpuPlayer.move();
        }

        view.repaint();
    }
}
