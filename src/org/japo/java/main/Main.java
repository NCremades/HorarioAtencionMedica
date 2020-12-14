/*
 * Copyright 2020 Noelia Cremades Gómez - noelia.cremades.alum@iescamp.es.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.main;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Noelia Cremades Gómez - noelia.cremades.alum@iescamp.es
 */
public class Main {

    public static final Scanner SCN
            = new Scanner(System.in, "Windows-1252")
                    .useLocale(Locale.ENGLISH).useDelimiter("\\s+");
    public static final Calendar CAL = Calendar.getInstance();
    public static final Random RND = new Random();

    public static void main(String[] args) {

        //Limites Temporales de un Dia Regular
        final int H_MIN = 0;
        final int M_MIN = 0;
        final int H_MAX = 23;
        final int M_MAX = 59;
        final int T_MIN = 0;
        final int T_MAX = H_MAX * 60 + M_MAX;

        //Tramo Horario Antención Médica
        final int H_INI = 9;
        final int M_INI = 30;
        final int H_FIN = 13;
        final int M_FIN = 45;
        final int T_INI = H_INI * 60 + M_INI;
        final int T_FIN = H_FIN * 60 + M_FIN;

        System.out.printf("Inicio Atención....: %02d:%02dh%n", H_INI, M_INI);
        System.out.printf("Final Atención.....: %02d:%02dh%n", H_FIN, M_FIN);

        //Separador
        System.out.println("---");

        //Instante Temporal obtenido de forma Aleatoria
        int tRnd = RND.nextInt(T_MAX - T_MIN + 1) + T_MIN; //Tiempo Aleatorio
        int hRnd = tRnd / 60;  //Creamos las horas de ese tiempo random al dividirlo por 60
        int mRnd = tRnd % 60;  //El resto de 60 serán los minutos que no llegan a una hora

        //Informe Tiempo Aleatorio
        System.out.printf("Hora Aleatoria.....: %02d:%02dh%n", hRnd, mRnd); //Escupe la hora y los minutos aleatorios
        System.out.printf("Hora Aleatoria.....: %s%n",
                tRnd >= T_INI && tRnd <= T_FIN); //tiempo aleatorio mayor o igual al tiempo de inicio(de la jornada laboral y el tiempo random debe ser menor o igual al tiempo final (jornada laboral  
        //Si no se cumple esa condición dará FALSE y si la cumple TRUE 
        //Separador
        System.out.println("---");

        //Hora actual  ingresada por el usuario, puede ser cualquiera 
        int hAct = H_MIN;
        try {
            System.out.print("Hora Usuario.......: ");
            hAct = SCN.nextInt();
        } catch (Exception e) {
            System.out.println("ERROR: Entrada incorrecta");
        } finally {
            SCN.nextLine();
        }

        //Minuto actual
        int mAct = M_MIN;
        try {
            System.out.print("Minuto Usuario.....: ");
            mAct = SCN.nextInt();
        } catch (Exception e) {
            System.out.println("ERROR: Entrada incorrecta");
        } finally {
            SCN.nextLine();
        }

        //Tiempo Actual
        int tAct = hAct * 60 + mAct; // se calcula en minutos 

        //Informe Tiempo Usuario
        System.out.printf("Hora Usuario.......: %02d:%02dh%n", hAct, mAct);
        System.out.printf("Hora Usuario.......: %s%n",
                tAct >= T_INI && tAct <= T_FIN
                        ? "Atención SI Disponible"
                        : "Atención NO Disponible");

        //Separador
        System.out.println("---");

        //Instante Temporal obtenido del Reloj del Sistema
        int hClk = CAL.get(Calendar.HOUR_OF_DAY);
        int mClk = CAL.get(Calendar.MINUTE);
        int tClk = hClk * 60 + mClk;

        //Infomre Instante Temporal obtenido Reloj del Sistema
        System.out.printf("Hora Sistema.......: %02d:%02dh%n", hClk, mClk);
        System.out.printf("Hora Sistema.......: %s%n",
                tClk >= T_INI && tClk <= T_FIN
                        ? "Atención SI Disponible"
                        : "Atención NO Disponible");

    }

}
