/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.utils;

import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class Validador {

    public static ArrayList<String> mensagens = new ArrayList<>();

    public static void validar(JTextField objValidar) {

        try {
            if (objValidar.getText().strip().equals("")) {
                throw new Exception("Preencha o campo: " + objValidar.getName());
            }
            objValidar.setBackground(Color.white);
        } catch (Exception e) {
            objValidar.setBackground(Color.red);
            mensagens.add(e.getMessage());
        }

    }


    public static void validarRadio(ButtonGroup buttonGroup) {
        try {
            if (buttonGroup.getSelection() == null) {
                throw new RuntimeException("Selecione uma opção.");
            }
        } catch (Exception e) {
            mensagens.add(e.getMessage());
        }
    }

    public static void validarData(JFormattedTextField formattedField) {
        try {
            String texto = formattedField.getText();

            String dataSemMascara = texto.replaceAll("[^0-9]", "");

            if (dataSemMascara.length() != 8) {
                throw new RuntimeException("Digite uma data válida (DD/MM/AAAA).");
            }

            formattedField.setBackground(Color.white);
        } catch (RuntimeException e) {
            formattedField.setBackground(Color.red);
            mensagens.add(e.getMessage());
        }
    }
    
    public static void validarCPF(JFormattedTextField formattedField) {
        try {
            String texto = formattedField.getText();

            String dataSemMascara = texto.replaceAll("[^0-9]", "");

            if (dataSemMascara.length() != 11) {
                throw new RuntimeException("Digite um CPF válido.");
            }

            formattedField.setBackground(Color.white);
        } catch (RuntimeException e) {
            formattedField.setBackground(Color.red);
            mensagens.add(e.getMessage());
        }
    }
    public static void validarTel(JFormattedTextField formattedField) {
        try {
            String texto = formattedField.getText();

            String dataSemMascara = texto.replaceAll("[^0-9]", "");

            if (dataSemMascara.length() != 11) {
                throw new RuntimeException("Digite um número válido.");
            }

            formattedField.setBackground(Color.white);
        } catch (RuntimeException e) {
            formattedField.setBackground(Color.red);
            mensagens.add(e.getMessage());
        }
    }

    public static boolean hasErro() {

        boolean retorno = false;

        if (mensagens.size() > 0) {
            retorno = true;
        }

        return retorno;

    }

    public static void limparMensagens() {

        mensagens.clear();

    }

    public static String exibirMensagens() {

        StringBuilder mensagensConcatenadas = new StringBuilder();
        String retorno = "";

        for (String msg : mensagens) {
            mensagensConcatenadas.append(msg + "\n");
        }

        if (mensagensConcatenadas.length() > 0) {
            retorno = mensagensConcatenadas.toString();
        }

        return retorno;

    }

}
