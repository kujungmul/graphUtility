#!bin/sh



for fileName in  "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.15._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._3000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._4000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._5000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._500._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.25._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.2._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.3._on._1000._om._2/network.dat"  "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-2]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._3/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-3]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._4/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-4]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._5/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-5]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._6/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-6]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._7/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-7]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._8/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._100._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._40._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._60._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._80._maxdegree._200._mu._0.1._on._1000._om._2/network.dat"

do

	start=`date +%s`


	python $HOME/otherAlgorithm/linkPartitioning/link.py $fileName >> 201703101010543110101.log



	end=`date +%s`

	rm 201703101010543110101.log

	runtime=$((end-start))
	echo $fileName $runtime

dirName=$(dirname $fileName)
cd $dirName

find . -type f -name '*.comm2edges*' -o -name '*_thr_D*' -o -name '*.edge2comm.*' |  while read d; do
	rm $d
done

find . -type f -name '*comm2nodes*' | while read d; do
	mv $d linkpartitioning.dat
	cut -f2- linkpartitioning.dat > linkpartitioning.dat_1
	expand -t 1 linkpartitioning.dat_1 > linkpartitioning.dat_2
	cat linkpartitioning.dat_2 > linkpartitioning.dat
	rm linkpartitioning.dat_2
	rm linkpartitioning.dat_1
done
done
