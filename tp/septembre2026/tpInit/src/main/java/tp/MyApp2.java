package tp;

public class MyApp2 {

    static void main() {
        //testBagage();
        testTableauMoyenne();
        testString();
    }

    static void testBagage(){
        Bagage b1 = new Bagage("sac" , 5000 , 12.5);
        b1.setPoids(6000);
        //System.out.println("b1="+b1.toString());
        System.out.println("b1="+b1);
    }

    public static void testTableauMoyenne(){
        /*
        double tabVal[] = new double[6];
        tabVal[0]=2.0; tabVal[1]=4.0; tabVal[2]=10.0; tabVal[3]=8.0; tabVal[4]=6.0; tabVal[5]=12.0;
        */
        double tabVal[] = { 2.0 , 4.0 , 10.0, 8.0 , 6.0 , 12.0 };
        double somme = 0;
        for(int i=0;i<tabVal.length;i++){
            somme += tabVal[i];
        }
        double moyenne = somme / tabVal.length;
        System.out.println("moyenne="+moyenne);
        double plusGrand = tabVal[0];
        for(int i=1;i<tabVal.length;i++){
            if(tabVal[i]>plusGrand){
                plusGrand=tabVal[i];
            }
        }
        System.out.println("plusGrand="+plusGrand);

    }

    public static void testString(){
        String s1 = "2023-01-17";
        //extraire la partie mois de différentes façons et afficher cette valeur
        //String tabPartS1[] = s1.split("-");  String mois =  tabPartS1[1];
        String mois = s1.substring(5,7);//charater index from 5(inclusive) to 7(exclusive)
        //String mois = s1.substring(s1.indexOf('-')+1,s1.lastIndexOf('-'));
        //String mois = s1.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$1"); //returning second group in ()
        System.out.println("mois="+mois +" in s1="+s1);

        String chaine="YTREZA";
        //créer une nouvelle chaine inverse où tous les caractères sont dans l'ordre inverse
        StringBuilder sb =  new StringBuilder();
        for(int i=chaine.length()-1;i>=0;i--){
            sb.append(chaine.charAt(i));
        }
        String inverse=sb.toString();
        System.out.println("inverse="+inverse);
    }

}
