package com.dayfour.hospitalqueue;

public class BubbleSort {

    static void bubbleSort(String[][] patients) {
        int n = patients.length;

        for (int i = 0; i < n - 1; i++) {
        	
        	boolean flagged = false;
            for (int j = 0; j < n - i - 1; j++) {

                if (Integer.parseInt(patients[j][1]) < Integer.parseInt(patients[j + 1][1])) {

                    String namej  = patients[j][0];
                    String crtj   = patients[j][1];

                    String namej_1 = patients[j + 1][0];
                    String crtj_1  = patients[j + 1][1];

                    patients[j][0] = namej_1;
                    patients[j][1] = crtj_1;

                    patients[j + 1][0] = namej;
                    patients[j + 1][1] = crtj;
                    
                    flagged = true;
                }
            }
            
            if(!flagged) {
            	break; 
            }
        }
    }
}
