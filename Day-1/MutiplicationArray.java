public class MutiplicationArray {
    public static void main(String[] args){
        int[][] arr1={{1,2},{3,4}};
        int[][] arr2={{4,1},{1,2}};
        int n= arr1.length;
        int[][] result = new int[n][n];

        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    result[i][j]+=arr1[i][j] * arr2[k][j];
                }
            }
        }

        for(int i =0;i<=n;i++){
            for(int j =0;j<n;j++){
                System.out.println(result[i][j]+" ");
            }
            System.out.println();
        }
    }
}
