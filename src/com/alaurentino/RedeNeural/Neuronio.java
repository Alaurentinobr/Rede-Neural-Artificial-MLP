package com.alaurentino.RedeNeural;

import com.alaurentino.RedeNeural.LimiarDeAtivacao.IFuncaoDeAtivacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson Laurentino on 19/12/2016.
 */
public class Neuronio implements Serializable {

    private List<Sinapse> entradas;
    private IFuncaoDeAtivacao limiarDeAtivacao;
    private double somaPonderada;
    private double derivada;
    private double saida;
    private double erro;

    public Neuronio(IFuncaoDeAtivacao limiarDeAtivacao) {
        this.limiarDeAtivacao = limiarDeAtivacao;
        entradas = new ArrayList<>();
        erro = 0;
    }

    public void somaPonderada() {
        somaPonderada = 0;

        for(Sinapse entrada : entradas)
            somaPonderada += entrada.getPeso() * entrada.getNeuronioDeOrigem().getSaida();
    }

    public double[] getPesos() {
        double[] pesos = new double[entradas.size()];

        for(int i = 0; i < entradas.size(); i++)
            pesos[i] = entradas.get(i).getPeso();

        return pesos;
    }

    public void addEntrada(Sinapse entrada) {
        entradas.add(entrada);
    }

    public void activacao() {
        somaPonderada();
        saida = limiarDeAtivacao.ativacao(somaPonderada);
        derivada = limiarDeAtivacao.derivada(saida);
    }

    public List<Sinapse> getEntradas() {
        return entradas;
    }

    public double getSaida() {
        return saida;
    }

    public double getErro() {
        return erro;
    }

    public IFuncaoDeAtivacao getLimiarDeAtivacao() {
        return limiarDeAtivacao;
    }

    public void setErro(double erro) {
        this.erro = erro;
    }

    public double getSomaPonderada() {
        return somaPonderada;
    }

    public void setSomaPonderada(double somaPonderada) {
        this.somaPonderada = somaPonderada;
    }

    public double getDerivada() {
        return derivada;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }
}
