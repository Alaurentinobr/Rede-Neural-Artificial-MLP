package com.alaurentino.RedeNeural.LimiarDeAtivacao;

import java.io.Serializable;

/**
 * Created by Anderson Laurentino on 20/12/2016.
 */
public class FLimiar implements Serializable, IFuncaoDeAtivacao {

    private double limite;

    public FLimiar(double limite) {
        this.limite = limite;
    }

    @Override
    public double ativacao(double somaPonderada) {
        return somaPonderada >= limite ? 1 : 0;
    }

    @Override
    public double derivada(double saida) {
        return 1;
    }
}
