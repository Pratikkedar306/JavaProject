package demo;

public class Insertion {
public static void main(String[] args) {
	int[] a = {40,12,15,2,8,51};
	for(int i=1;i<a.length;i++) {
		int k=a[i];
		int j=i-1;
		while(j>=0 && a[j]>k) {
			a[j+1]=a[j];
			j--;
		}
		a[j+1]=k;
	}
	for(int b : a) {
	System.out.print(b + " ");
	}
}
}
