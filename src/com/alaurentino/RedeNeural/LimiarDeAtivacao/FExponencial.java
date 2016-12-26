package com.alaurentino.RedeNeural.LimiarDeAtivacao;

/**
 * Created by Anderson Laurentino on 25/12/2016.
 */
public class FExponencial implements IFuncaoDeAtivacao {
    @Override
    public double ativacao(double somaPonderada) {
        return Math.exp(somaPonderada);
    }

    @Override
    public double derivada(double saida) {
        return Math.exp(saida);
    }
}
