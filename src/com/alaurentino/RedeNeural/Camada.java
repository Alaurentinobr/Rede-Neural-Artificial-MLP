package com.alaurentino.RedeNeural;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Laurentino on 20/12/2016.
 */
public class Camada implements Serializable {

    private List<Neuronio> neuronios;
    private Camada camadaAnterior;
    private Camada camadaPosterior;
    private Neuronio bias;

    public Camada() {
        neuronios = new ArrayList<>();
        camadaAnterior = null;
    }

    public Camada(Camada camadaAnterior) {
        this();
        this.camadaAnterior = camadaAnterior;
    }

    public Camada(Camada camadaAnterior, Neuronio bias) {
        this(camadaAnterior);
        this.bias = bias;
    }

    public void addNeuronio(Neuronio neuronio) {
        if(camadaAnterior != null)
            for(Neuronio neuronioDaCamadaAnterior : camadaAnterior.getNeuronios())
                neuronio.addEntrada(new Sinapse(neuronioDaCamadaAnterior, (Math.random() * 1) - 0.5));

        neuronios.add(neuronio);
    }

    public void addNeuronio(Neuronio neuronio, double[] pesos) {
        if(camadaAnterior != null) {
            if(camadaAnterior.getNeuronios().size() != pesos.length)
                throw new IllegalArgumentException("O número de pesos não corresponde ao número de neurônios da camada anterior!");
            else
                for(int i = 0; i < camadaAnterior.getNeuronios().size(); i++)
                    neuronio.addEntrada(new Sinapse(camadaAnterior.getNeuronios().get(i), pesos[i]));
        }

        neuronios.add(neuronio);
    }

    public void feedForward() {
        int bias = hasBias() ? 1 : 0;

        for(int i = bias; i < getNeuronios().size(); i++)
            neuronios.get(i).activacao();
    }

    public boolean ehCamadaDeSaida() {
        return camadaPosterior == null;
    }

    public boolean hasBias() {
        return bias != null;
    }

    public List<Neuronio> getNeuronios() {
        return neuronios;
    }

    public Camada getCamadaAnterior() {
        return camadaAnterior;
    }

    public void setCamadaAnterior(Camada camadaAnterior) {
        this.camadaAnterior = camadaAnterior;
    }

    public Camada getCamadaPosterior() {
        return camadaPosterior;
    }

    public void setCamadaPosterior(Camada camadaPosterior) {
        this.camadaPosterior = camadaPosterior;
    }

    public void setBias(Neuronio bias) {
        this.bias = bias;
    }

    public Neuronio getBias() {
        return bias;
    }
}
