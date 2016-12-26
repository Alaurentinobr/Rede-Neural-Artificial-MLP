package com.alaurentino.RedeNeural.LimiarDeAtivacao;

/**
 * Created by Anderson Laurentino on 20/12/2016.
 */
public interface IFuncaoDeAtivacao {
    double ativacao(double somaPonderada);
    double derivada(double saida);
}
