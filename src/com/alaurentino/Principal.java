package com.alaurentino;

import com.alaurentino.RedeNeural.Camada;
import com.alaurentino.RedeNeural.LimiarDeAtivacao.FLimiar;
import com.alaurentino.RedeNeural.Neuronio;
import com.alaurentino.RedeNeural.RedeNeural;
import com.alaurentino.RedeNeural.Treinamento.Backpropagation;

/**
 * Created by Anderson Laurentino on 20/12/2016.
 */
public class Principal {
    public static void main(String[] args) {
        RedeNeural rna = new RedeNeural("(&&)");

        Camada camadaDeEntrada = new Camada(null);
        Camada camadaDeSaida = new Camada(camadaDeEntrada);

        camadaDeEntrada.addNeuronio(new Neuronio(new FLimiar(1)));
        camadaDeEntrada.addNeuronio(new Neuronio(new FLimiar(1)));

        camadaDeSaida.addNeuronio(new Neuronio(new FLimiar(2)));

        rna.addCamada(camadaDeEntrada);
        rna.addCamada(camadaDeSaida);

        double[][] entradas = {{0,0},{0,1},{1,0},{1,1}};
        double[][] saidas = {{0},{0},{0},{1}};

        treinar(rna, entradas, saidas);

        System.out.println("\nEx(&&):");

        rna.setEntradas(new double[]{0,0});
        System.out.println("0 && 0: " + rna.getSaidas()[0]);

        rna.setEntradas(new double[]{0,1});
        System.out.println("0 && 1: " + rna.getSaidas()[0]);

        rna.setEntradas(new double[]{1,0});
        System.out.println("1 && 0: " + rna.getSaidas()[0]);

        rna.setEntradas(new double[]{1,1});
        System.out.println("1 && 1: " + rna.getSaidas()[0]);
    }

    public static void treinar(RedeNeural redeNeural, double[][] entradas, double[][] saidas) {
        Backpropagation b = new Backpropagation(redeNeural, 0.5);
        b.treino(entradas, saidas);
        b.comentario();
    }
}
