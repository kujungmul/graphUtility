#!bin/sh



for fileName in "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._100._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.15._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._3000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._4000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._5000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._500._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.25._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.2._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.3._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._40._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._60._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._80._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-2]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._3/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-3]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._4/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-4]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._5/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-5]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._6/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-6]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._7/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-7]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._8/network.dat"


do

for para in "100" "0"
do

for algo in "infomap" "louvain" "label_prop"
do

java -cp ".:/usr/lib/R/site-library/rJava/jri/JRI.jar" D2O_Spliter.Main $fileName $algo $para

done
done
done
