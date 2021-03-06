#!bin/sh













for fileName in "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._100._maxdegree._200._mu._0.1._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.15._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._3000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._4000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._5000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._500._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.25._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.2._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.3._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._40._maxdegree._200._mu._0.1._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._60._maxdegree._200._mu._0.1._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._80._maxdegree._200._mu._0.1._on._1000._om._2/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-2]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._3/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-3]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._4/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-4]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._5/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-5]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._6/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-6]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._7/" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-7]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._8/"
do
groundTruth="${fileName}community_overlap.dat"
find $fileName -name '*final*' | while read fname; do
	echo "$fname"
	$HOME/overlapping_measure/Overlapping-NMI-master/onmi $groundTruth $fname -o
done



find $fileName -name 'copra.dat' | while read fname; do
	echo "$fname"
	$HOME/overlapping_measure/Overlapping-NMI-master/onmi $groundTruth $fname -o
done

find $fileName -name 'linkpartitioning.dat' | while read fname; do
	echo "$fname"
	$HOME/overlapping_measure/Overlapping-NMI-master/onmi $groundTruth $fname -o
done

find $fileName -name 'cfinder_agg.dat' | while read fname; do
	echo "$fname"
	$HOME/overlapping_measure/Overlapping-NMI-master/onmi $groundTruth $fname -o
done

find $fileName -name 'SLPAw_*' | while read fname; do
	echo "$fname"
	$HOME/overlapping_measure/Overlapping-NMI-master/onmi $groundTruth $fname -o
done









done
