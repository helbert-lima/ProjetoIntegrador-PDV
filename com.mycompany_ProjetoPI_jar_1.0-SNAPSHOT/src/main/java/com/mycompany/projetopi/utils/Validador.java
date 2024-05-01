/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.utils;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
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
            objValidar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        } catch (Exception e) {
            objValidar.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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

    public static void validarData(JDateChooser dateChooser) {
        try {
            Date dataSelecionada = dateChooser.getDate();

            if (dataSelecionada == null) {
                throw new RuntimeException("Selecione uma data válida.");
            }

            // Restante do seu código para validação, se necessário
            dateChooser.setBorder(BorderFactory.createEmptyBorder());
        } catch (RuntimeException e) {
            dateChooser.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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

            formattedField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        } catch (RuntimeException e) {
            formattedField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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
            formattedField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        } catch (RuntimeException e) {
            formattedField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
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
