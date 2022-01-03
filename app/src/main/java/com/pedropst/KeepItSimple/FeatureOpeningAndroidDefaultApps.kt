package com.pedropst.KeepItSimple

import android.widget.Button

class FeatureOpeningAndroidDefaultApps : IHomeButtons{ // QUEM SABE COLOCAR CORES DIFERENTES TAMBÉM
    val text : String
    val position : Int
    val button : Button


    constructor(text: String, position: Int, button : Button){
        this.text = text
        this.position = position
        this.button = button
    }

    override fun click() {
        this.button.setOnClickListener{

        }
    }
}

//A IDEIA É IMPLEMENTAR ESSA CLASSE PARA CADA BOTÃO DISPONÍVEL NO HOME, PODE ATÉ SER TODOS, MESMO
//        AQUELES QUE NAO ESTÃO SENDO OCUPADOS.
//        TEXT VAI SER O TEXTO EXIBIDO NO BOTAO
//        ICON VAI SER O ICONE DO BOTÃO  --> TEM QUE VER COMO FAZ PARA CRIAR OBJETO IMAGE VECTOR
//        POSITION A POSICAO EX.: 10 11 20 21 30 31 ... 61
//        BUTTON É O PRÓPRIO BOTAO
//
//ESSA CLASSE IMPLEMENTA A INTERFACE IHomeButton QUE CONTÉM O MÉTODO CLICK, QUE POR SUA VEZ SERÁ
//        SOBREESCRITO EM TIPO DE CLASSE QUE O BOTAO PODE SER