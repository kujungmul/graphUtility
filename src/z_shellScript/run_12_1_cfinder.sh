#!bin/sh



for fileName in "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.15._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._2000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._3000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._4000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._5000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.1._on._500._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.25._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.2._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._20._maxdegree._200._mu._0.3._on._1000._om._2/network.dat"  "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-2]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._3/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-3]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._4/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-4]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._5/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-5]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._6/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-6]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._7/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-7]N_10000._degree._20._maxdegree._200._mu._0.1._on._1000._om._8/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._100._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._40._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._60._maxdegree._200._mu._0.1._on._1000._om._2/network.dat" "/home/rk2tcpl/benchmarkDataSet/HOON_12_TEMP/[HOON-12-1]N_10000._degree._80._maxdegree._200._mu._0.1._on._1000._om._2/network.dat"

do



start=`date +%s`

dirName=$(dirname $fileName)

$HOME/otherAlgorithm/cfinder_cpm/CFinder-2.0.6--1448/CFinder_commandline64 -i $fileName -o $dirName/network.dat_files -l $HOME/otherAlgorithm/cfinder_cpm/CFinder-2.0.6--1448/licence.txt > cfinderTemp.dat


end=`date +%s`


runtime=$((end-start))
rm cfinderTemp.dat
echo $fileName $runtime

cd $dirName
cd ${fileName}_files


find * -prune -type d | while read d; do
	mv ${fileName}_files/${d}/communities ${dirName}/cfinder_${d:-1}.dat
done

rm -r ${dirName}/network.dat_files


find $dirName -name 'cfinder*' | while read fname; do
	sed '/^#/ d' $fname > ${fname}_1
	sed '/^\s*$/d' ${fname}_1 > ${fname}_2
	cut -d' ' -f2- ${fname}_2 > ${fname}_3
	cat ${fname}_3 > $fname
	rm ${fname}_1
	rm ${fname}_2
	rm ${fname}_3

done

done
