import java.io.*;
import java.util.*;
//import commLib.*;

public class matrix {
	public static void main(String[] args){
		
	}
	
    /*
     * use two boolean array to record which row or col should be set to zero.
     * then set to zero. two scan of the matrix. 
    x,x,0,x
    x,0,x,x
    x,x,x,x
    -------
    0,0,0,0
    x,0,0,x
    x,x,0,x 
    --------
    0,0,0,0
    0,0,0,0
    x,0,0,x 
    --------  
    isFirstRowZero=true;//first row set to 0
    isFirstColZero=false; //
    */
    public void setZeroes(int[][] matrix) {
        boolean[] rowFlag = new boolean[matrix.length];
        boolean[] colFlag = new boolean[matrix[0].length];
        // Traversal matrix, set flags
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
                
            }
        }//for
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if( rowFlag[i] || colFlag[j] ){
                    matrix[i][j] = 0;
                }
                
            }
        }//for
        
    }
    
    public void ptrMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
            	System.out.println(matrix[i][j]);
            }
        }
    }
    
}