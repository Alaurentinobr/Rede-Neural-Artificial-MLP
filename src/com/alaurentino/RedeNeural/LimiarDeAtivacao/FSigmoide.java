package com.alaurentino.RedeNeural.LimiarDeAtivacao;

/**
 * Created by Anderson Laurentino on 23/12/2016.
 */
public class FSigmoide implements IFuncaoDeAtivacao {
    @Override
    public double ativacao(double somaPonderada) {
        return 1/(1 + Math.exp(-1 * somaPonderada));
    }

    @Override
    public double derivada(double saida) {
        return saida * (1 - saida);
    }
}
