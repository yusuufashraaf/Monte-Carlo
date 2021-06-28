package MonteCarlo;

import java.util.Random;
import java.util.stream.IntStream;
import javax.swing.JTextField;

public class MonteCarloFunctions {
    //function to get random numbers between 1 and 100 according to number of days
    public static int[] getrandom(int numberofdays){
        int Randomarr[]=new int[numberofdays+1];
          for(int i=1;i<numberofdays+1;i++){
         Random RandomNumber = new Random();
        int RandomInt = RandomNumber.nextInt(100) + 1; 
            int Random2=RandomInt;
            Randomarr[i]=Random2; 
    }
      return Randomarr;
}
    //function to get frequency from user
   
    //function to get probability from user
    public static float[] probability(int numberofindex,JTextField textfields[],int numberofdays ){
        float probability[]=new float[numberofdays+1];
        for(int i=1;i<numberofindex+1;i++){
            String probabilityfromtextfield=textfields[i].getText();
            float probabilty=Float.parseFloat(probabilityfromtextfield);
            probability[i]=probabilty;
        }
        return probability;
    }
     public static float[] Frequency(int numberofindex,JTextField textfields[],int numberofdays ){
        float Frequency[]=new float[numberofdays+1];
        for(int i=1;i<numberofindex+1;i++){
            String frequencyfromtextfield=textfields[i].getText();
            float frequency=Float.parseFloat(frequencyfromtextfield);
            Frequency[i]=frequency;
        }
        return Frequency;
    }
    //function to calculate cumulative probability
    public static float[] cumulativeprobability(int numberofindex,JTextField textfields[],int numberofdays){
        float [] probability=probability(numberofindex,textfields,numberofdays);
        float cumulativearr[]=new float[numberofdays+1];
        float sum=0;
        for(int i=1;i<numberofindex+1;i++){
            sum+=probability[i];
            cumulativearr[i]=sum;
        }
        return cumulativearr;
    }
    //function to calculate cumulative frequency
    public static float[] cumulativefrequency(int numberofindex,JTextField textfields[],int numberofdays){
        float [] frequency=Frequency(numberofindex,textfields,numberofdays);
        float cumulativearr[]=new float[numberofdays+1];
        float sum=0;
        float sum_frequency=0;
for(int i=1;i<numberofindex+1;i++){
    sum_frequency+=frequency[i];
}
        for(int i=1;i<numberofindex+1;i++){
                  frequency[i]=frequency[i]/sum_frequency;
              }
               for(int i=1;i<numberofindex+1;i++){
               sum+=frequency[i];
               cumulativearr[i]=sum;
        }
               return cumulativearr;
    }
    // function to get interval from cumulativefrequency
    public static int[] Intervalfrequency(int numberofindex,JTextField textfields[],int numberofdays){
        float []Intervalfrequency=cumulativefrequency(numberofindex,textfields, numberofdays);
        int []Interval=new int[numberofdays+1];
        for(int i=1;i<numberofindex+1;i++){
            Interval[i]=(int) (Intervalfrequency[i]*100);
        }
        return Interval;
    }
    // function to get interval from cumulativeprobability
    public static int[] Intervalprobability(int numberofindex,JTextField textfields[],int numberofdays){
        float []Intervalprobability=cumulativeprobability(numberofindex,textfields,numberofdays);
        int []Interval=new int[numberofdays+1];
        for(int i=1;i<numberofindex+1;i++){
            Interval[i]=(int) (Intervalprobability[i]*100);
        }
        return Interval;
    }
    //function to make days
     public static int[] days(int numberofdays){
        int[] days=new int[numberofdays+1];
        for(int i=1;i<numberofdays+1;i++){
            days[i]=i;
        }
        return days;
    }
    //function to get number of index
     public static int index(){
         String y=MonteCarloMain.index1.getText();
         int x=Integer.parseInt(y);
         return x;
     }
      //function to get from combo box
     public static String combo(){
         String y=(String) MonteCarloMain.combo.getSelectedItem();
         return y;
     }
     //function to calculate probability
     public static void calculateprobabilities(){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);
         String w=MonteCarloMain.index1.getText();
         int z=Integer.parseInt(w);
         float[] probability=probability(z,MonteCarlo.tabTextFields,y );
         float[] Cummulativeprobability=cumulativeprobability(z,MonteCarlo.tabTextFields,y);
         int[] interval=Intervalprobability(z,MonteCarlo.tabTextFields,y);
         int [] days=days(y);
         int[] Random=getrandom(y);
         int [] simulated=simulatedfrequency(y);
         for(int i=1;i<y+1;i++){
             MonteCarlo.model.addRow(new Object[]{probability[i],Cummulativeprobability[i],interval[i],days[i],Random[i],simulated[i]});
         }
     }
     //function to calculate frequency
     public static void calculatefrequency(){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);
         String w=MonteCarloMain.index1.getText();
         int z=Integer.parseInt(w);
         float[] frequency=Frequency(z,MonteCarlo.tabTextFields,y );
         float[] Cummulativefrequency=cumulativefrequency(z,MonteCarlo.tabTextFields,y);
         int[] interval=Intervalfrequency(z,MonteCarlo.tabTextFields,y);
         int [] days=days(y);
         int[] Random=getrandom(y);
         int [] simulated=simulatedfrequency(y);
         for(int i=1;i<y+1;i++){
             MonteCarlo.model.addRow(new Object[]{frequency[i],Cummulativefrequency[i],interval[i],days[i],Random[i],simulated[i]});
         }
     }
     public static int[] simulatedfrequency(int numberofdays){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);
         String w=MonteCarloMain.index1.getText();
         int z=Integer.parseInt(w);
         int[] intervalfrequency=Intervalfrequency(z,MonteCarlo.tabTextFields,y);
         int[] random=getrandom(y);
         int simulated[]=new int[y+1];
         for(int i=1;i<y+1;i++){
             for(int j=1;j<y+1;j++){
                 if(random[i]<=intervalfrequency[j]){
                     simulated[i]=j-1;
                     break;
                 }
             }
         }
         return simulated;
     }
     public static int[] simulatedprobabilities(int numberofdays){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);
         String w=MonteCarloMain.index1.getText();
         int z=Integer.parseInt(w);
         int[] Intervalprobability=Intervalprobability(z,MonteCarlo.tabTextFields,y);
         int[] random=getrandom(y);
         int simulated[]=new int[y+1];
         for(int i=1;i<y+1;i++){
             for(int j=1;j<y+1;j++){
                 if(random[i]<=Intervalprobability[j]){
                     simulated[i]=j-1;
                     break;
                 }
             }
         }
         return simulated;
     }
     public static float averagefrequency(){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);   
         int simulated[]=simulatedfrequency(y);
         int sum_sim = IntStream.of(simulated).sum();
         float Average=(float)sum_sim/y;
         return Average;
     }
     public static float averageprobability(){
         String x=MonteCarloMain.days1.getText();
         int y=Integer.parseInt(x);   
         int simulated[]=simulatedprobabilities(y);
         int sum_sim = IntStream.of(simulated).sum();
         float Average=(float)sum_sim/y;
         return Average;
     }
     public static float[] sumfrequency(int numberofindex,JTextField textfields[],int numberofdays){
         String x=MonteCarloMain.index1.getText();
         int y=Integer.parseInt(x);
         float sum=0;
         float sumfrequency []=Frequency(numberofindex,textfields,numberofdays);
         for (int i=1;i<y+1;i++){
             sum+=sumfrequency[i];
         }
         for(int i=1;i<y+1;i++){
             sumfrequency[i]=sumfrequency[i]/sum;
         }
         return sumfrequency;
     }
      public static float expected(){
                   float sum7=0;
                   int o=0;
                   String m=MonteCarloMain.index1.getText();
                   int p=Integer.parseInt(m);
                   String h=(String) MonteCarloMain.combo.getSelectedItem();
                   float [] sumfrequency=sumfrequency(p,MonteCarlo.tabTextFields,p);
                   float[] sumprobability=probability(p,MonteCarlo.tabTextFields,p);
                   String f=MonteCarloMain.start1.getText();
                   int y=Integer.parseInt(f);
                   if(h=="Frequency"){
            for(int i=y;i<=p+y;i++){
            sum7+=sumfrequency[o]*(i-1);
            o++;
             }
                   }
                   else{
                         for(int i=y;i<=p+y;i++){
            sum7+=sumprobability[o]*(i-1);
                    o++;
             }
                        sum7=(float) (Math.round(sum7 * 100.0) / 100.0);
                   }
                   return sum7;
      }
}