package net.oliver.olframework.test;


import java.text.NumberFormat;

public class core {
    double scoredl = 0;
    double scoredl1 = 0;
    double scoredl2 = 0;
    double scoredl3 = 0;
    double scoredl4 = 0;
    double scoredl5 = 0;
    double scoredl6 = 0;
    double scoredl7 = 0;
    double scoredl8 = 0;
    double scoredl9 = 0;
    double scoredl10 = 0;
    double scoredl11 = 0;
    double scoredl12 = 0;
    double scoredl13 = 0;
    double scoredl14 = 0;
    double scoredl15 = 0;
    double scoredl16 = 0;
    double scoredl17 = 0;
    double scoredl18 = 0;

    double scoredx1 = 0;
    double scoredx2 = 0;
    double scoredx3 = 0;
    double scoredx4 = 0;
    double scoredx5 = 0;
    double scoredx6 = 0;
    double scoredx7 = 0;
    double scoredx8 = 0;
    double scoredx9 = 0;
    double scoredx10 = 0;
    double scoredx11 = 0;
    double scoredx12 = 0;
    double scoredx13 = 0;
    double scoredx14 = 0;
    double scoredx15 = 0;


   // BigDecimal num = new BigDecimal("#.##");

    /*
         private String dingliang1 = null;
         private String dingliang2 = null;
         private String dingliang3 = null;
         private String dingliang4 = null;
         private String dingliang5 = null;
         private String dingliang6 = null;
         private String dingliang7 = null;
         private String dingliang8 = null;
         private String dingliang9 = null;
         private String dingliang10 = null;
         private String dingliang11 = null;
         private String dingliang12 = null;
         private String dingliang13 = null;
         private String dingliang14 = null;
         private String dingliang15 = null;
         private String dingliang16 = null;
         private String dingliang17 = null;
         private String dingliang18 = null;

         public void setDingliang1(String dingliang1) {
        this.dingliang1 = dingliang1;
         }

         public void setDingliang2(String dingliang2) {
        this.dingliang2 = dingliang2;
         }

         public void setDingliang3(String dingliang3) {
        this.dingliang3 = dingliang3;
         }

         public void setDingliang4(String dingliang4) {
        this.dingliang4 = dingliang4;
         }

         public void setDingliang5(String dingliang5) {
        this.dingliang5 = dingliang5;
         }

         public void setDingliang6(String dingliang6) {
        this.dingliang6 = dingliang6;
         }

         public void setDingliang7(String dingliang7) {
        this.dingliang7 = dingliang7;
         }

         public void setDingliang8(String dingliang8) {
        this.dingliang8 = dingliang8;
         }

         public void setDingliang9(String dingliang9) {
        this.dingliang9 = dingliang9;
         }

         public void setDingliang10(String dingliang10) {
        this.dingliang10 = dingliang10;
         }

         public void setDingliang11(String dingliang11) {
        this.dingliang11 = dingliang11;
         }

         public void setDingliang12(String dingliang12) {
        this.dingliang12 = dingliang12;
         }

         public void setDingliang13(String dingliang13) {
        this.dingliang13 = dingliang13;
         }

         public void setDingliang14(String dingliang14) {
        this.dingliang14 = dingliang14;
         }

         public void setDingliang15(String dingliang15) {
        this.dingliang15 = dingliang15;
         }

         public void setDingliang16(String dingliang16) {
        this.dingliang16 = dingliang16;
         }

         public void setDingliang17(String dingliang17) {
        this.dingliang17 = dingliang17;
         }

         public void setDingliang18(String dingliang18) {
        this.dingliang18 = dingliang18;
         }

         public String getDingliang1() {
        return dingliang1;
         }

         public String getDingliang2() {
        return dingliang2;
         }

         public String getDingliang3() {
        return dingliang3;
         }

         public String getDingliang4() {
        return dingliang4;
         }

         public String getDingliang5() {
        return dingliang5;
         }

         public String getDingliang6() {
        return dingliang6;
         }

         public String getDingliang7() {
        return dingliang7;
         }

         public String getDingliang8() {
        return dingliang8;
         }

         public String getDingliang9() {
        return dingliang9;
         }

         public String getDingliang10() {
        return dingliang10;
         }

         public String getDingliang11() {
        return dingliang11;
         }

         public String getDingliang12() {
        return dingliang12;
         }

         public String getDingliang13() {
        return dingliang13;
         }

         public String getDingliang14() {
        return dingliang14;
         }

         public String getDingliang15() {
        return dingliang15;
         }

         public String getDingliang16() {
        return dingliang16;
         }

         public String getDingliang17() {
        return dingliang17;
         }

         public String getDingliang18() {
        return dingliang18;
         }

     */

    public double coreDingliang(String strDingliang1, String strDingliang2,
                                String strDingliang3, String strDingliang4,
                                String strDingliang5, String strDingliang6,
                                String strDingliang7, String strDingliang8,
                                String strDingliang9, String strDingliang10,
                                String strDingliang11, String strDingliang12,
                                String strDingliang13, String strDingliang14,
                                String strDingliang15, String strDingliang16,
                                String strDingliang17, String strDingliang18) {

        ///////////////////////////销售收入////////////////////////////////////////

        if (Double.parseDouble(strDingliang1) > 31194000 &&
            Double.parseDouble(strDingliang1) <= 34660000) {
            double temp;
            temp = (34660000 - 31194000) / 20;
            scoredl1 += (Double.parseDouble(strDingliang1) - 31194000) / temp;
            scoredl1 *= 0.18;
        } else if (Double.parseDouble(strDingliang1) > 34660000 &&
                   Double.parseDouble(strDingliang1) <= 71950000) {
            double temp;
            temp = (71950000 - 34660000) / 20;
            scoredl1 += (Double.parseDouble(strDingliang1) - 34660000) / temp +
                    20;
            scoredl1 *= 0.18;
        } else if (Double.parseDouble(strDingliang1) > 71950000 &&
                   Double.parseDouble(strDingliang1) <= 119168400) {
            double temp;
            temp = (119168400 - 71950000) / 20;
            scoredl1 += (Double.parseDouble(strDingliang1) - 71950000) / temp +
                    40;
            scoredl1 *= 0.18;
        } else if (Double.parseDouble(strDingliang1) > 119168400 &&
                   Double.parseDouble(strDingliang1) <= 197940100) {
            double temp;
            temp = (197940100 - 119168400) / 20;
            scoredl1 += (Double.parseDouble(strDingliang1) - 119168400) / temp +
                    60;
            scoredl1 *= 0.18;
        } else if (Double.parseDouble(strDingliang1) > 197940100 &&
                   Double.parseDouble(strDingliang1) <= 465860100) {
            double temp;
            temp = (465860100 - 197940100) / 20;
            scoredl1 += (Double.parseDouble(strDingliang1) - 197940100) / temp +
                    80;
            scoredl1 *= 0.18;
        } else if (Double.parseDouble(strDingliang1) > 465860100) {
            scoredl1 += 100;
            scoredl1 *= 0.18;
        }

        /////////////////////////////////////////////////////////////////////////




        /////////////////////////利息保障倍数///////////////////////////////////////
        if (Double.parseDouble(strDingliang2) > 1.31 &&
            Double.parseDouble(strDingliang2) <= 1.46) {

            double temp;
            temp = (1.46 - 1.31) / 20;
            scoredl2 += (Double.parseDouble(strDingliang2) - 1.31) / temp;
            scoredl2 *= 0.11;
        } else if (Double.parseDouble(strDingliang2) > 1.46 &&
                   Double.parseDouble(strDingliang2) <= 2.58) {
            double temp;
            temp = (2.58 - 1.46) / 20;
            scoredl2 += (Double.parseDouble(strDingliang2) - 1.46) / temp + 20;
            scoredl2 *= 0.11;
        } else if (Double.parseDouble(strDingliang2) > 2.58 &&
                   Double.parseDouble(strDingliang2) <= 3.91) {
            double temp;
            temp = (3.91 - 2.58) / 20;
            scoredl2 += (Double.parseDouble(strDingliang2) - 2.58) / temp + 40;
            scoredl2 *= 0.11;
        } else if (Double.parseDouble(strDingliang2) > 3.91 &&
                   Double.parseDouble(strDingliang2) <= 7.02) {
            double temp;
            temp = (7.02 - 3.91) / 20;
            scoredl2 += (Double.parseDouble(strDingliang2) - 3.91) / temp +
                    60;
            scoredl2 *= 0.11;
        } else if (Double.parseDouble(strDingliang2) > 7.02 &&
                   Double.parseDouble(strDingliang2) <= 21.69) {
            double temp;
            temp = (21.69 - 7.02) / 20;
            scoredl2 += (Double.parseDouble(strDingliang2) - 7.02) / temp +
                    80;
            scoredl2 *= 0.11;
        } else if (Double.parseDouble(strDingliang2) > 21.69) {
            scoredl2 += 100;
            scoredl2 *= 0.11;
        }

        ///////////////////////////////////////////////////////////////////////////////



        /////////////////////////////全部资本化比率////////////////////////////////////////
        if (Double.parseDouble(strDingliang3) > 16.76 &&
            Double.parseDouble(strDingliang3) <= 33.42) {
            double temp;
            temp = (33.42 - 16.76) / 20;
            scoredl3 += (33.42 - Double.parseDouble(strDingliang3)) / temp + 80;
            scoredl3 *= 0.08;
        } else if (Double.parseDouble(strDingliang3) > 33.42 &&
                   Double.parseDouble(strDingliang3) <= 46.39) {
            double temp;
            temp = (46.39 - 33.42) / 20;
            scoredl3 += (46.39 - Double.parseDouble(strDingliang3)) / temp + 60;
            scoredl3 *= 0.08;
        } else if (Double.parseDouble(strDingliang3) > 46.39 &&
                   Double.parseDouble(strDingliang3) <= 59.25) {
            double temp;
            temp = (59.25 - 46.39) / 20;
            scoredl3 += (59.25 - Double.parseDouble(strDingliang2)) / temp + 40;
            scoredl3 *= 0.08;
        } else if (Double.parseDouble(strDingliang3) > 59.25 &&
                   Double.parseDouble(strDingliang3) <= 78.88) {
            double temp;
            temp = (78.88 - 59.25) / 20;
            scoredl13 += (78.88 - Double.parseDouble(strDingliang3)) / temp +
                    20;
            scoredl3 *= 0.08;
        } else if (Double.parseDouble(strDingliang3) > 78.88 &&
                   Double.parseDouble(strDingliang3) <= 86.77) {
            double temp;
            temp = (86.77 - 78.88) / 20;
            scoredl3 += (86.77 - Double.parseDouble(strDingliang3)) / temp;
            scoredl3 *= 0.08;
        } else if (Double.parseDouble(strDingliang3) <= 16.76) {
            scoredl3 += 100;
            scoredl3 *= 0.08;
        }

        /////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////现金流动负债比//////////////////////////////////////
        if (Double.parseDouble(strDingliang4) > 4 &&
            Double.parseDouble(strDingliang4) <= 5.47) {
            double temp;
            temp = (5.47 - 4) / 20;
            scoredl4 += (Double.parseDouble(strDingliang4) - 4) / temp;
            scoredl4 *= 0.13;
        } else if (Double.parseDouble(strDingliang4) > 5.47 &&
                   Double.parseDouble(strDingliang4) <= 12.76) {
            double temp;
            temp = (12.76 - 5.47) / 20;
            scoredl4 += (Double.parseDouble(strDingliang4) - 5.47) / temp + 20;
            scoredl4 *= 0.13;
        } else if (Double.parseDouble(strDingliang4) > 12.76 &&
                   Double.parseDouble(strDingliang4) <= 22.3) {
            double temp;
            temp = (22.3 - 12.76) / 20;
            scoredl4 += (Double.parseDouble(strDingliang4) - 12.76) / temp + 40;
            scoredl4 *= 0.13;
        } else if (Double.parseDouble(strDingliang4) > 22.3 &&
                   Double.parseDouble(strDingliang4) <= 30.92) {
            double temp;
            temp = (30.92 - 22.3) / 20;
            scoredl4 += (Double.parseDouble(strDingliang4) - 22.3) / temp +
                    60;
            scoredl4 *= 0.13;
        } else if (Double.parseDouble(strDingliang4) > 30.92 &&
                   Double.parseDouble(strDingliang4) <= 43.76) {
            double temp;
            temp = (43.76 - 30.92) / 20;
            scoredl4 += (Double.parseDouble(strDingliang4) - 30.92) / temp +
                    80;
            scoredl4 *= 0.13;
        } else if (Double.parseDouble(strDingliang4) > 43.76) {
            scoredl4 += 100;
            scoredl4 *= 0.13;
        }

        //////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////主营业务利润 /总资产//////////////////////////////
        if (Double.parseDouble(strDingliang5) > 4.4 &&
            Double.parseDouble(strDingliang5) <= 4.89) {
            double temp;
            temp = (4.89 - 4.4) / 20;
            scoredl5 += (Double.parseDouble(strDingliang5) - 4.4) / temp;
            scoredl5 *= 0.15;
        } else if (Double.parseDouble(strDingliang5) > 4.89 &&
                   Double.parseDouble(strDingliang5) <= 10.92) {
            double temp;
            temp = (10.92 - 4.89) / 20;
            scoredl5 += (Double.parseDouble(strDingliang5) - 4.89) / temp + 20;
            scoredl5 *= 0.15;
        } else if (Double.parseDouble(strDingliang5) > 10.92 &&
                   Double.parseDouble(strDingliang5) <= 16.77) {
            double temp;
            temp = (16.77 - 10.92) / 20;
            scoredl5 += (Double.parseDouble(strDingliang5) - 10.92) / temp + 40;
            scoredl5 *= 0.15;
        } else if (Double.parseDouble(strDingliang5) > 16.77 &&
                   Double.parseDouble(strDingliang5) <= 21.21) {
            double temp;
            temp = (21.21 - 16.77) / 20;
            scoredl5 += (Double.parseDouble(strDingliang5) - 16.77) / temp +
                    60;
            scoredl5 *= 0.15;
        } else if (Double.parseDouble(strDingliang5) > 21.21 &&
                   Double.parseDouble(strDingliang5) <= 30.92) {
            double temp;
            temp = (30.92 - 21.21) / 20;
            scoredl5 += (Double.parseDouble(strDingliang5) - 21.21) / temp +
                    80;
            scoredl5 *= 0.15;
        } else if (Double.parseDouble(strDingliang5) > 30.92) {
            scoredl5 += 100;
            scoredl5 *= 0.15;
        }

        /////////////////////////////////////////////////////////////////////////////////



        //////////////////////////////净资产收益率////////////////////////////////////////
        if (Double.parseDouble(strDingliang6) > 1.85 &&
            Double.parseDouble(strDingliang6) <= 2.05) {
            double temp;
            temp = (2.05 - 1.85) / 20;
            scoredl6 += (Double.parseDouble(strDingliang6) - 1.85) / temp;
            scoredl6 *= 0.1;
        } else if (Double.parseDouble(strDingliang6) > 2.05 &&
                   Double.parseDouble(strDingliang6) <= 6.06) {
            double temp;
            temp = (6.06 - 2.05) / 20;
            scoredl6 += (Double.parseDouble(strDingliang6) - 2.05) / temp + 20;
            scoredl6 *= 0.1;
        } else if (Double.parseDouble(strDingliang6) > 6.06 &&
                   Double.parseDouble(strDingliang6) <= 10.33) {
            double temp;
            temp = (10.33 - 6.06) / 20;
            scoredl6 += (Double.parseDouble(strDingliang6) - 10.33) / temp + 40;
            scoredl6 *= 0.1;
        } else if (Double.parseDouble(strDingliang6) > 10.33 &&
                   Double.parseDouble(strDingliang6) <= 17.83) {
            double temp;
            temp = (17.83 - 10.33) / 20;
            scoredl6 += (Double.parseDouble(strDingliang6) - 10.33) / temp +
                    60;
            scoredl6 *= 0.1;
        } else if (Double.parseDouble(strDingliang6) > 17.83 &&
                   Double.parseDouble(strDingliang6) <= 27.68) {
            double temp;
            temp = (27.68 - 17.83) / 20;
            scoredl6 += (Double.parseDouble(strDingliang6) - 17.83) / temp +
                    80;
            scoredl6 *= 0.1;
        } else if (Double.parseDouble(strDingliang6) > 27.68) {
            scoredl6 += 100;
            scoredl6 *= 0.1;
        }

        ////////////////////////////////////////////////////////////////////////////////




        /////////////////////////////////现金周期///////////////////////////////////////////
        if (Double.parseDouble(strDingliang7) > 20.03 &&
            Double.parseDouble(strDingliang7) <= 30.08) {
            double temp;
            temp = (20.03 - 30.08) / 20;
            scoredl7 += (Double.parseDouble(strDingliang7) - 30.08) / temp + 80;
            scoredl7 *= 0.1;
        } else if (Double.parseDouble(strDingliang7) > 30.08 &&
                   Double.parseDouble(strDingliang7) <= 43.16) {
            double temp;
            temp = (30.08 - 43.16) / 20;
            scoredl7 += (Double.parseDouble(strDingliang7) - 43.16) / temp + 60;
            scoredl7 *= 0.1;
        } else if (Double.parseDouble(strDingliang7) > 43.16 &&
                   Double.parseDouble(strDingliang7) <= 69.71) {
            double temp;
            temp = (43.16 - 69.71) / 20;
            scoredl7 += (Double.parseDouble(strDingliang7) - 69.71) / temp + 40;
            scoredl7 *= 0.1;
        } else if (Double.parseDouble(strDingliang7) > 69.71 &&
                   Double.parseDouble(strDingliang7) <= 144.11) {
            double temp;
            temp = (69.71 - 144.11) / 20;
            scoredl7 += (Double.parseDouble(strDingliang7) - 144.11) / temp +
                    20;
            scoredl7 *= 0.1;
        } else if (Double.parseDouble(strDingliang7) > 144.11 &&
                   Double.parseDouble(strDingliang7) <= 158.52) {
            double temp;
            temp = (144.11 - 158.52) / 20;
            scoredl7 += (Double.parseDouble(strDingliang7) - 158.52) / temp;
            scoredl7 *= 0.1;
        } else if (Double.parseDouble(strDingliang7) <= 20.03) {
            scoredl7 += 100;
            scoredl7 *= 0.1;
        }

        ////////////////////////////////////////////////////////////////////////////////



        //////////////////////////////销售增长率/////////////////////////////////////
        if (Double.parseDouble(strDingliang8) > -9.96 &&
            Double.parseDouble(strDingliang8) <= -9.95) {
            double temp;
            temp = ( -9.95 - ( -9.96)) / 20;
            scoredl8 += (Double.parseDouble(strDingliang8) - ( -9.96)) / temp;
            scoredl8 *= 0.07;
        } else if (Double.parseDouble(strDingliang8) > ( -9.95) &&
                   Double.parseDouble(strDingliang8) <= 4.31) {
            double temp;
            temp = (4.31 - ( -9.95)) / 20;
            scoredl8 += (Double.parseDouble(strDingliang8) - ( -9.95)) / temp +
                    20;
            scoredl8 *= 0.07;
        } else if (Double.parseDouble(strDingliang8) > 4.31 &&
                   Double.parseDouble(strDingliang8) <= 10.13) {
            double temp;
            temp = (10.13 - 4.31) / 20;
            scoredl8 += (Double.parseDouble(strDingliang8) - 4.31) / temp + 40;
            scoredl8 *= 0.07;
        } else if (Double.parseDouble(strDingliang8) > 10.13 &&
                   Double.parseDouble(strDingliang8) <= 27.31) {
            double temp;
            temp = (27.31 - 10.13) / 20;
            scoredl8 += (Double.parseDouble(strDingliang8) - 10.13) / temp +
                    60;
            scoredl8 *= 0.07;
        } else if (Double.parseDouble(strDingliang8) > 27.31 &&
                   Double.parseDouble(strDingliang8) <= 66.12) {
            double temp;
            temp = (66.12 - 27.31) / 20;
            scoredl8 += (Double.parseDouble(strDingliang8) - 27.31) / temp +
                    80;
            scoredl8 *= 0.07;
        } else if (Double.parseDouble(strDingliang8) > 66.12) {
            scoredl8 += 100;
            scoredl8 *= 0.07;
        }

        //////////////////////////////////////////////////////////////////////////


        //////////////////////////////总资产增长率/////////////////////////////////////
        if (Double.parseDouble(strDingliang9) > -6 &&
            Double.parseDouble(strDingliang9) <= -4.84) {
            double temp;
            temp = ( -4.84 - ( -6)) / 20;
            scoredl9 += (Double.parseDouble(strDingliang9) - ( -6)) / temp;
            scoredl9 *= 0.03;
        } else if (Double.parseDouble(strDingliang8) > ( -4.84) &&
                   Double.parseDouble(strDingliang8) <= 5.79) {
            double temp;
            temp = (5.79 - ( -4.84)) / 20;
            scoredl9 += (Double.parseDouble(strDingliang9) - ( -4.84)) / temp +
                    20;
            scoredl9 *= 0.03;
        } else if (Double.parseDouble(strDingliang9) > 5.79 &&
                   Double.parseDouble(strDingliang9) <= 19.04) {
            double temp;
            temp = (19.04 - 5.79) / 20;
            scoredl9 += (Double.parseDouble(strDingliang9) - 5.79) / temp + 40;
            scoredl9 *= 0.03;
        } else if (Double.parseDouble(strDingliang9) > 19.04 &&
                   Double.parseDouble(strDingliang9) <= 35.1) {
            double temp;
            temp = (35.1 - 19.04) / 20;
            scoredl9 += (Double.parseDouble(strDingliang9) - 19.04) / temp +
                    60;
            scoredl9 *= 0.03;
        } else if (Double.parseDouble(strDingliang9) > 35.1 &&
                   Double.parseDouble(strDingliang9) <= 53.68) {
            double temp;
            temp = (53.68 - 35.1) / 20;
            scoredl9 += (Double.parseDouble(strDingliang9) - 35.1) / temp +
                    80;
            scoredl9 *= 0.03;
        } else if (Double.parseDouble(strDingliang9) > 53.68) {
            scoredl9 += 100;
            scoredl9 *= 0.03;
        }

        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////营业利润/////////////////////////////////////
        if (Double.parseDouble(strDingliang10) > 22.5 &&
            Double.parseDouble(strDingliang10) <= 25) {
            double temp;
            temp = (25 - 22.5) / 20;
            scoredl10 += (Double.parseDouble(strDingliang10) - 22.5) / temp;
            scoredl10 *= 0.18;
        } else if (Double.parseDouble(strDingliang10) > 25 &&
                   Double.parseDouble(strDingliang10) <= 134) {
            double temp;
            temp = (134 - 25) / 20;
            scoredl10 += (Double.parseDouble(strDingliang10) - 25) / temp +
                    20;
            scoredl10 *= 0.18;
        } else if (Double.parseDouble(strDingliang10) > 134 &&
                   Double.parseDouble(strDingliang10) <= 323) {
            double temp;
            temp = (323 - 134) / 20;
            scoredl10 += (Double.parseDouble(strDingliang10) - 134) / temp + 40;
            scoredl10 *= 0.18;
        } else if (Double.parseDouble(strDingliang10) > 323 &&
                   Double.parseDouble(strDingliang10) <= 564) {
            double temp;
            temp = (564 - 323) / 20;
            scoredl10 += (Double.parseDouble(strDingliang10)- 323) / temp +
                    60;
            scoredl10 *= 0.18;
        } else if (Double.parseDouble(strDingliang10) > 564 &&
                   Double.parseDouble(strDingliang10) <= 1198) {
            double temp;
            temp = (1198 - 564) / 20;
            scoredl10 += (Double.parseDouble(strDingliang10) - 564) / temp +
                    80;
            scoredl10 *= 0.18;
        } else if (Double.parseDouble(strDingliang10) > 1198) {
            scoredl10 += 100;
            scoredl10 *= 0.18;
        }

//////////////////////////////////////////////////////////////////////////


        //////////////////////////////经营活动净现金流/总债务/////////////////////////////////////
        if (Double.parseDouble(strDingliang11) > -25.84 &&
            Double.parseDouble(strDingliang11) <= -23.49) {
            double temp;
            temp = ( -23.49 - ( -25.84)) / 20;
            scoredl11 += (Double.parseDouble(strDingliang11) - ( -25.84)) /
                    temp;
            scoredl11 *= 0.11;
        } else if (Double.parseDouble(strDingliang11) > -23.49 &&
                   Double.parseDouble(strDingliang11) <= 0) {
            double temp;
            temp = (0 - ( -23.49)) / 20;
            scoredl11 += (Double.parseDouble(strDingliang11) - ( -23.49)) /
                    temp +
                    20;
            scoredl11 *= 0.11;
        } else if (Double.parseDouble(strDingliang11) > 0 &&
                   Double.parseDouble(strDingliang11) <= 6.57) {
            double temp;
            temp = (6.57 - 0) / 20;
            scoredl11 += (Double.parseDouble(strDingliang11) - 0) / temp + 40;
            scoredl11 *= 0.11;
        } else if (Double.parseDouble(strDingliang11) > 6.57 &&
                   Double.parseDouble(strDingliang11) <= 20.41) {
            double temp;
            temp = (20.41 - 6.57) / 20;
            scoredl11 += (Double.parseDouble(strDingliang11) - 20.41) / temp +
                    60;
            scoredl11 *= 0.11;
        } else if (Double.parseDouble(strDingliang11) > 20.41 &&
                   Double.parseDouble(strDingliang11) <= 58.52) {
            double temp;
            temp = (58.52 - 20.41) / 20;
            scoredl11 += (Double.parseDouble(strDingliang11) - 58.52) / temp +
                    80;
            scoredl11 *= 0.11;
        } else if (Double.parseDouble(strDingliang11) > 58.52) {
            scoredl11 += 100;
            scoredl11 *= 0.11;
        }

//////////////////////////////////////////////////////////////////////////

        //////////////////////////////调整后资产负债率/////////////////////////////////////
        if (Double.parseDouble(strDingliang12) > 27.59 &&
            Double.parseDouble(strDingliang12) <= 42) {
            double temp;
            temp = (27.59 - 42) / 20;
            scoredl12 += (Double.parseDouble(strDingliang12) - 42) / temp + 80;
            scoredl12 *= 0.08;
        } else if (Double.parseDouble(strDingliang12) > 42 &
                   Double.parseDouble(strDingliang12) <= 52.78) {
            double temp;
            temp = (42 - 52.78) / 20;
            scoredl12 += (Double.parseDouble(strDingliang12) - 52.78) / temp +
                    60;
            scoredl12 *= 0.08;
        } else if (Double.parseDouble(strDingliang12) > 52.78 &&
                   Double.parseDouble(strDingliang12) <= 71.74) {
            double temp;
            temp = (52.78 - 71.74) / 20;
            scoredl12 += (Double.parseDouble(strDingliang12) - 71.74) / temp +
                    40;
            scoredl12 *= 0.08;
        } else if (Double.parseDouble(strDingliang12) > 71.74 &&
                   Double.parseDouble(strDingliang12) <= 89.35) {
            double temp;
            temp = (71.74 - 89.35) / 20;
            scoredl12 += (Double.parseDouble(strDingliang12) - 89.35) / temp +
                    20;
            scoredl12 *= 0.08;
        } else if (Double.parseDouble(strDingliang12) > 89.35 &&
                   Double.parseDouble(strDingliang12) <= 98.29) {
            double temp;
            temp = (89.35 - 98.29) / 20;
            scoredl12 += (Double.parseDouble(strDingliang12) - 98.29) / temp;
            scoredl12 *= 0.08;
        } else if (Double.parseDouble(strDingliang12) <= 27.59) {
            scoredl12 += 100;
            scoredl12 *= 0.08;
        }

//////////////////////////////////////////////////////////////////////////

        //////////////////////////////流动比率/////////////////////////////////////
        if (Double.parseDouble(strDingliang13) > 90.95 &&
            Double.parseDouble(strDingliang13) <= 101.06) {
            double temp;
            temp = (101.06 - 90.95) / 20;
            scoredl13 += (Double.parseDouble(strDingliang13) - 90.95) /
                    temp;
            scoredl13 *= 0.13;
        } else if (Double.parseDouble(strDingliang13) > 101.06 &&
                   Double.parseDouble(strDingliang13) <= 116.57) {
            double temp;
            temp = (116.57 - 101.06) / 20;
            scoredl13 += (Double.parseDouble(strDingliang13) - 116.57) /
                    temp + 20;
            scoredl13 *= 0.13;
        } else if (Double.parseDouble(strDingliang13) > 116.57 &&
                   Double.parseDouble(strDingliang13) <= 131.46) {
            double temp;
            temp = (131.46 - 116.57) / 20;
            scoredl13 += (Double.parseDouble(strDingliang13) - 116.57) / temp +
                    40;
            scoredl13 *= 0.13;
        } else if (Double.parseDouble(strDingliang13) > 131.46 &&
                   Double.parseDouble(strDingliang13) <= 147.83) {
            double temp;
            temp = (147.83 - 131.46) / 20;
            scoredl13 += (Double.parseDouble(strDingliang13) - 131.46) / temp +
                    60;
            scoredl13 *= 0.13;
        } else if (Double.parseDouble(strDingliang13) > 147.83 &&
                   Double.parseDouble(strDingliang13) <= 172.38) {
            double temp;
            temp = (172.38 - 147.83) / 20;
            scoredl13 += (Double.parseDouble(strDingliang13) - 147.83) / temp +
                    80;
            scoredl13 *= 0.13;
        } else if (Double.parseDouble(strDingliang13) > 172.38) {
            scoredl13 += 100;
            scoredl13 *= 0.13;
        }

//////////////////////////////////////////////////////////////////////////


        //////////////////////////////EBIT/(总债务+净资产)/////////////////////////////////////
        if (Double.parseDouble(strDingliang14) > 2.37 &&
            Double.parseDouble(strDingliang14) <= 2.63) {
            double temp;
            temp = (2.63 - 2.37) / 20;
            scoredl14 += (Double.parseDouble(strDingliang14) - 2.37) /
                    temp;
            scoredl14 *= 0.12;
        } else if (Double.parseDouble(strDingliang14) > 2.63 &&
                   Double.parseDouble(strDingliang14) <= 7.51) {
            double temp;
            temp = (7.51 - 2.63) / 20;
            scoredl14 += (Double.parseDouble(strDingliang14) - 7.51) /
                    temp + 20;
            scoredl14 *= 0.12;
        } else if (Double.parseDouble(strDingliang14) > 7.51 &&
                   Double.parseDouble(strDingliang14) <= 11.6) {
            double temp;
            temp = (11.6 - 7.51) / 20;
            scoredl14 += (Double.parseDouble(strDingliang14) - 7.51) / temp +
                    40;
            scoredl14 *= 0.12;
        } else if (Double.parseDouble(strDingliang14) > 11.6 &&
                   Double.parseDouble(strDingliang14) <= 16.25) {
            double temp;
            temp = (16.25 - 11.6) / 20;
            scoredl14 += (Double.parseDouble(strDingliang14) - 11.6) / temp +
                    60;
            scoredl14 *= 0.12;
        } else if (Double.parseDouble(strDingliang14) > 16.25 &&
                   Double.parseDouble(strDingliang14) <= 24.93) {
            double temp;
            temp = (24.93 - 16.25) / 20;
            scoredl14 += (Double.parseDouble(strDingliang14) - 16.25) / temp +
                    80;
            scoredl14 *= 0.12;
        } else if (Double.parseDouble(strDingliang14) > 24.93) {
            scoredl14 += 100;
            scoredl14 *= 0.12;
        }

//////////////////////////////////////////////////////////////////////////


        //////////////////////////////成本费用利润率/////////////////////////////////////
        if (Double.parseDouble(strDingliang15) > 0.39 &&
            Double.parseDouble(strDingliang15) <= 0.43) {
            double temp;
            temp = (0.43 - 0.39) / 20;
            scoredl15 += (Double.parseDouble(strDingliang15) - 0.39) /
                    temp;
            scoredl15 *= 0.13;
        } else if (Double.parseDouble(strDingliang15) > 0.43 &&
                   Double.parseDouble(strDingliang15) <= 1.72) {
            double temp;
            temp = (1.72 - 0.43) / 20;
            scoredl15 += (Double.parseDouble(strDingliang15) - 0.43) /
                    temp + 20;
            scoredl15 *= 0.13;
        } else if (Double.parseDouble(strDingliang15) > 1.72 &&
                   Double.parseDouble(strDingliang15) <= 3.57) {
            double temp;
            temp = (3.57 - 1.72) / 20;
            scoredl15 += (Double.parseDouble(strDingliang15) - 1.72) / temp +
                    40;
            scoredl15 *= 0.13;
        } else if (Double.parseDouble(strDingliang15) > 3.57 &&
                   Double.parseDouble(strDingliang15) <= 4.93) {
            double temp;
            temp = (4.93 - 3.57) / 20;
            scoredl15 += (Double.parseDouble(strDingliang15) - 3.57) / temp +
                    60;
            scoredl15 *= 0.13;
        } else if (Double.parseDouble(strDingliang15) > 4.93 &&
                   Double.parseDouble(strDingliang15) <= 11.09) {
            double temp;
            temp = (11.09 - 4.93) / 20;
            scoredl15 += (Double.parseDouble(strDingliang15) - 4.93) / temp +
                    80;
            scoredl15 *= 0.13;
        } else if (Double.parseDouble(strDingliang15) > 11.09) {
            scoredl15 += 100;
            scoredl15 *= 0.13;
        }

//////////////////////////////////////////////////////////////////////////

        //////////////////////////////总资产周转率/////////////////////////////////////
        if (Double.parseDouble(strDingliang16) > 0.45 &&
            Double.parseDouble(strDingliang16) <= 0.5) {
            double temp;
            temp = (0.5 - 0.45) / 20;
            scoredl16 += (Double.parseDouble(strDingliang16) - 0.45) /
                    temp;
            scoredl16 *= 0.15;
        } else if (Double.parseDouble(strDingliang16) > 0.5 &&
                   Double.parseDouble(strDingliang16) <= 1.15) {
            double temp;
            temp = (1.15 - 0.5) / 20;
            scoredl16 += (Double.parseDouble(strDingliang16) - 0.5) /
                    temp + 20;
            scoredl16 *= 0.15;
        } else if (Double.parseDouble(strDingliang16) > 1.15 &&
                   Double.parseDouble(strDingliang16) <= 1.53) {
            double temp;
            temp = (1.53 - 1.15) / 20;
            scoredl16 += (Double.parseDouble(strDingliang16) - 1.15) / temp +
                    40;
            scoredl16 *= 0.15;
        } else if (Double.parseDouble(strDingliang16) > 1.53 &&
                   Double.parseDouble(strDingliang16) <= 2.61) {
            double temp;
            temp = (2.61 - 1.53) / 20;
            scoredl16 += (Double.parseDouble(strDingliang16) - 1.53) / temp +
                    60;
            scoredl16 *= 0.15;
        } else if (Double.parseDouble(strDingliang16) > 2.61 &&
                   Double.parseDouble(strDingliang16) <= 3.58) {
            double temp;
            temp = (3.58 - 2.61) / 20;
            scoredl16 += (Double.parseDouble(strDingliang16) - 2.61) / temp +
                    80;
            scoredl16 *= 0.15;
        } else if (Double.parseDouble(strDingliang16) > 3.58) {
            scoredl16 += 100;
            scoredl16 *= 0.15;
        }

//////////////////////////////////////////////////////////////////////////

        //////////////////////////////资本积累率/////////////////////////////////////
        if (Double.parseDouble(strDingliang17) > -7 &&
            Double.parseDouble(strDingliang17) <= -5.56) {
            double temp;
            temp = ( -5.56 - ( -7)) / 20;
            scoredl17 += (Double.parseDouble(strDingliang17) - ( -7)) /
                    temp;
            scoredl17 *= 0.06;
        } else if (Double.parseDouble(strDingliang17) > -5.56 &&
                   Double.parseDouble(strDingliang17) <= 0.94) {
            double temp;
            temp = (0.94 - ( -5.56)) / 20;
            scoredl17 += (Double.parseDouble(strDingliang17) - ( -5.56)) /
                    temp + 20;
            scoredl17 *= 0.06;
        } else if (Double.parseDouble(strDingliang17) > 0.94 &&
                   Double.parseDouble(strDingliang17) <= 7.63) {
            double temp;
            temp = (7.63 - 0.94) / 20;
            scoredl17 += (Double.parseDouble(strDingliang17) - 0.94) / temp +
                    40;
            scoredl17 *= 0.06;
        } else if (Double.parseDouble(strDingliang17) > 7.63 &&
                   Double.parseDouble(strDingliang17) <= 14.5) {
            double temp;
            temp = (14.5 - 7.63) / 20;
            scoredl17 += (Double.parseDouble(strDingliang17) - 7.63) / temp +
                    60;
            scoredl17 *= 0.06;
        } else if (Double.parseDouble(strDingliang17) > 14.5 &&
                   Double.parseDouble(strDingliang17) <= 55.83) {
            double temp;
            temp = (55.83 - 14.5) / 20;
            scoredl17 += (Double.parseDouble(strDingliang17) - 14.5) / temp +
                    80;
            scoredl17 *= 0.06;
        } else if (Double.parseDouble(strDingliang17) > 55.83) {
            scoredl17 += 100;
            scoredl17 *= 0.06;
        }

//////////////////////////////////////////////////////////////////////////

        //////////////////////////////(未分配利润+盈余公积)/总资产/////////////////////////////////////
        if (Double.parseDouble(strDingliang18) > 0.4 &&
            Double.parseDouble(strDingliang18) <= 1.39) {
            double temp;
            temp = (1.39 - 0.4) / 20;
            scoredl18 += (Double.parseDouble(strDingliang18) - 0.4) /
                    temp;
            scoredl18 *= 0.04;
        } else if (Double.parseDouble(strDingliang18) > 1.39 &&
                   Double.parseDouble(strDingliang18) <= 3.11) {
            double temp;
            temp = (3.11 - 1.39) / 20;
            scoredl18 += (Double.parseDouble(strDingliang18) - 1.39) /
                    temp + 20;
            scoredl18 *= 0.04;
        } else if (Double.parseDouble(strDingliang18) > 3.11 &&
                   Double.parseDouble(strDingliang18) <= 4.88) {
            double temp;
            temp = (4.88 - 3.11) / 20;
            scoredl18 += (Double.parseDouble(strDingliang18) - 3.11) / temp +
                    40;
            scoredl18 *= 0.04;
        } else if (Double.parseDouble(strDingliang18) > 4.88 &&
                   Double.parseDouble(strDingliang18) <= 7.9) {
            double temp;
            temp = (7.9 - 4.88) / 20;
            scoredl18 += (Double.parseDouble(strDingliang18) - 4.88) / temp +
                    60;
            scoredl18 *= 0.04;
        } else if (Double.parseDouble(strDingliang18) > 7.9 &&
                   Double.parseDouble(strDingliang18) <= 15.98) {
            double temp;
            temp = (15.98 - 7.9) / 20;
            scoredl18 += (Double.parseDouble(strDingliang18) - 7.9) / temp +
                    80;
            scoredl18 *= 0.04;
        } else if (Double.parseDouble(strDingliang18) > 15.98) {
            scoredl18 += 100;
            scoredl18 *= 0.04;
        }

//////////////////////////////////////////////////////////////////////////
        return scoredl1 + scoredl2+scoredl3 + scoredl4 + scoredl5 + scoredl6 +
                scoredl7 + scoredl8 + scoredl9 + scoredl10 + scoredl11 +
                scoredl12 +
                scoredl13 + scoredl14 + scoredl15 + scoredl16 + scoredl17 +
                scoredl18;
    }
    public static void main(String[] args)
    {
    	core obj = new core();
    	obj.coreDingxing("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
    }
    public double coreDingxing(String strDingxing1, String strDingxing2,
                               String strDingxing3, String strDingxing4,
                               String strDingxing5, String strDingxing6,
                               String strDingxing7, String strDingxing8,
                               String strDingxing9, String strDingxing10,
                               String strDingxing11, String strDingxing12,
                               String strDingxing13, String strDingxing14,
                               String strDingxing15) {
        switch (Integer.parseInt(strDingxing1)) {
        case -3:
            scoredx1 = 0;break;
        case -2:
            scoredx1 = 0;break;
        case -1:
            scoredx1 = 25;break;
        case 0:
            scoredx1 = 50;break;
        case 1:
            scoredx1 = 75;break;
        case 2:
            scoredx1 = 75;break;
        case 3:
            scoredx1 = 100;break;
        }
        scoredx1 *= 0.12;

        switch (Integer.parseInt(strDingxing2)) {
        case -3:
            scoredx2 = 0;break;
        case -2:
            scoredx2 = 0;break;
        case -1:
            scoredx2 = 25;break;
        case 0:
            scoredx2 = 50;break;
        case 1:
            scoredx2 = 75;break;
        case 2:
            scoredx2 = 75;break;
        case 3:
            scoredx2 = 100;break;
        }
        scoredx2 *= 0.08;

        switch (Integer.parseInt(strDingxing3)) {
        case -5:
            scoredx3 = 0;break;
        case -4:
            scoredx3 = 0;break;
        case -3:
            scoredx3 = 25;break;
        case -2:
            scoredx3 = 25;break;
        case -1:
            scoredx3 = 25;break;
        case 0:
            scoredx3 = 50;break;
        case 1:
            scoredx3 = 75;break;
        case 2:
            scoredx3 = 75;break;
        case 3:
            scoredx3 = 75;break;
        case 4:
            scoredx3 = 100;break;
        case 5:
            scoredx3 = 100;break;
        }
        scoredx3 *= 0.05;

        switch (Integer.parseInt(strDingxing4)) {
        case -3:
            scoredx4 = 0;break;
        case -2:
            scoredx4 = 0;break;
        case -1:
            scoredx4 = 25;break;
        case 0:
            scoredx4 = 50;break;
        case 1:
            scoredx4 = 75;break;
        case 2:
            scoredx4 = 75;break;
        case 3:
            scoredx4 = 100;break;
        }
        scoredx4 *= 0.05;

        switch (Integer.parseInt(strDingxing5)) {
        case -3:
            scoredx5 = 0;break;
        case -2:
            scoredx5 = 0;break;
        case -1:
            scoredx5 = 25;break;
        case 0:
            scoredx5 = 50;break;
        case 1:
            scoredx5 = 75;break;
        case 2:
            scoredx5 = 75;break;
        case 3:
            scoredx5 = 100;break;
        }
        scoredx5 *= 0.1;

        switch (Integer.parseInt(strDingxing6)) {
        case -4:
            scoredx6 = 0;break;
        case -3:
            scoredx6 = 0;break;
        case -2:
            scoredx6 = 25;break;
        case -1:
            scoredx6 = 25;break;
        case 0:
            scoredx6 = 50;break;
        case 1:
            scoredx6 = 75;break;
        case 2:
            scoredx6 = 75;break;
        case 3:
            scoredx6 = 100;break;
        case 4:
            scoredx6 = 100;break;
        }
        scoredx6 *= 0.07;

        switch (Integer.parseInt(strDingxing7)) {
        case -3:
            scoredx7 = 0;break;
        case -2:
            scoredx7 = 0;break;
        case -1:
            scoredx7 = 25;break;
        case 0:
            scoredx7 = 50;break;
        case 1:
            scoredx7 = 75;break;
        case 2:
            scoredx7 = 75;break;
        case 3:
            scoredx7 = 100;break;
        }
        scoredx7 *= 0.08;

        switch (Integer.parseInt(strDingxing8)) {
        case -4:
            scoredx8 = 0;break;
        case -3:
            scoredx8 = 0;break;
        case -2:
            scoredx8 = 25;break;
        case -1:
            scoredx8 = 25;break;
        case 0:
            scoredx8 = 50;break;
        case 1:
            scoredx8 = 75;break;
        case 2:
            scoredx8 = 75;break;
        case 3:
            scoredx8 = 100;break;
        case 4:
            scoredx8 = 100;break;
        }
        scoredx8 *= 0.1;

        switch (Integer.parseInt(strDingxing9)) {
        case -3:
            scoredx9 = 0;break;
        case -2:
            scoredx9 = 0;break;
        case -1:
            scoredx9 = 25;break;
        case 0:
            scoredx9 = 50;break;
        case 1:
            scoredx9 = 75;break;
        case 2:
            scoredx9 = 75;break;
        case 3:
            scoredx9 = 100;break;
        }
        scoredx9 *= 0.05;

        switch (Integer.parseInt(strDingxing10)) {
        case -3:
            scoredx10 = 0;break;
        case -2:
            scoredx10 = 0;break;
        case -1:
            scoredx10 = 25;break;
        case 0:
            scoredx10 = 50;break;
        case 1:
            scoredx10 = 75;break;
        case 2:
            scoredx10 = 75;break;
        case 3:
            scoredx10 = 100;break;
        }
        scoredx10 *= 0.05;

        if (Double.parseDouble(strDingxing11) >= 0.8) {
            scoredx11 = 100;
        } else if (Double.parseDouble(strDingxing11) >= 0.6 &&
                   Double.parseDouble(strDingxing11) < 0.8) {
            scoredx11 = 75;
        } else if (Double.parseDouble(strDingxing11) >= 0.4 &&
                   Double.parseDouble(strDingxing11) < 0.6) {
            scoredx11 = 50;
        } else if (Double.parseDouble(strDingxing11) >= 0.3 &&
                   Double.parseDouble(strDingxing11) < 0.4) {
            scoredx11 = 25;
        } else {
            scoredx11 = 0;
        }
        scoredx11 *= 0.05;

        if (Double.parseDouble(strDingxing12) >= 0.5) {
            scoredx12 = 100;
        } else if (Double.parseDouble(strDingxing12) >= 0.4 &&
                   Double.parseDouble(strDingxing12) < 0.5) {
            scoredx12 = 75;
        } else if (Double.parseDouble(strDingxing12) >= 0.3 &&
                   Double.parseDouble(strDingxing12) < 0.4) {
            scoredx12 = 50;
        } else if (Double.parseDouble(strDingxing12) >= 0.2 &&
                   Double.parseDouble(strDingxing12) < 0.3) {
            scoredx12 = 25;
        } else {
            scoredx12 = 0;
        }
        scoredx12 *= 0.05;

        switch (Integer.parseInt(strDingxing13)) {

        case -2:
            scoredx13 = 0;break;
        case -1:
            scoredx13 = 25;break;
        case 0:
            scoredx13 = 50;break;
        case 1:
            scoredx13 = 75;break;
        case 2:
            scoredx13 = 75;break;
        case 3:
            scoredx13 = 100;break;
        }
        scoredx13 *= 0.05;

        switch (Integer.parseInt(strDingxing14)) {
        case -4:
            scoredx14 = 0;break;
        case -3:
            scoredx14 = 0;break;
        case -2:
            scoredx14 = 25;break;
        case -1:
            scoredx14 = 25;break;
        case 0:
            scoredx14 = 50;break;
        case 1:
            scoredx14 = 75;break;
        case 2:
            scoredx14 = 75;break;
        case 3:
            scoredx14 = 100;break;
        case 4:
            scoredx14 = 100;break;
        }
        scoredx8 *= 0.05;

        switch (Integer.parseInt(strDingxing15)) {
        case -4:
            scoredx15 = 0;break;
        case -3:
            scoredx15 = 0;break;
        case -2:
            scoredx15 = 25;break;
        case -1:
            scoredx15 = 25;break;
        case 0:
            scoredx15 = 50;break;
        case 1:
            scoredx15 = 75;break;
        case 2:
            scoredx15 = 75;break;
        case 3:
            scoredx15 = 100;break;
        case 4:
            scoredx15 = 100;break;
        }
        scoredx15 *= 0.05;

        return scoredx1; }

}
