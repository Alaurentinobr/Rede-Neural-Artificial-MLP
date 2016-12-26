package com.alaurentino.RedeNeural;

import java.io.Serializable;

/**
 * Created by Anderson Laurentino on 19/12/2016.
 */
public class Sinapse implements Serializable {

    private Neuronio neuronioDeOrigem;
    private double peso;

    public Sinapse(Neuronio neuronioDeOrigem, double peso) {
        this.neuronioDeOrigem = neuronioDeOrigem;
        this.peso = peso;
    }

    public Neuronio getNeuronioDeOrigem() {
        return neuronioDeOrigem;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return String.valueOf(neuronioDeOrigem.getSaida());
    }
}
