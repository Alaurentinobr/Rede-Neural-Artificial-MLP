package com.alaurentino.RedeNeural.LimiarDeAtivacao;

/**
 * Created by Anderson Laurentino on 25/12/2016.
 */
public class FLogaritima implements IFuncaoDeAtivacao {
    @Override
    public double ativacao(double somaPonderada) {
        return Math.log(somaPonderada);
    }

    @Override
    public double derivada(double saida) {
        return 1/saida;
    }
}
