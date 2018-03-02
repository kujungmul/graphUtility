#!bin/sh

echo "HELLO WORLD! THIS IS LAYOUT AUTOMATIC PROGRAM"

idx=3

p="$HOME/makeBenchmark/benchmark$idx"

cd $p

for valueMu in "0.1"
do

for valueK in "20" "40" "60" "80" "100"
do

for valuemaxK in "200"
do

for valOm in "2"
do

for valN in "10000"
do

for valOn in "1000"
do

for minC in "20" "30" "40" "50"
do

for maxC in "100" "150" "300"
do


  ./benchmark -N $valN -k $valueK -maxk $valuemaxK -mu $valueMu -on $valOn -om $valOm -minc $minC -maxc $maxC
mkdir "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv network.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv community.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv statistics.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"

done
done
done
done
done
done
done
done














for valueMu in "0.1"
do

for valueK in "20"
do

for valuemaxK in "200"
do

for valOm in "2" "3" "4" "5" "6" "7" "8"
do

for valN in "10000"
do

for valOn in "1000"
do

  for minC in "20" "30" "40" "50"
  do

  for maxC in "100" "150" "300"
  do

  ./benchmark -N $valN -k $valueK -maxk $valuemaxK -mu $valueMu -on $valOn -om $valOm -minc $minC -maxc $maxC
mkdir "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv network.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv community.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv statistics.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"

done
done
done
done
done
done
done
done

















for valueMu in "0.1"
do

for valueK in "20"
do

for valuemaxK in "200"
do

for valOm in "2"
do

for valN in "10000"
do

for valOn in "500" "1000" "2000" "3000" "4000" "5000"
do

  for minC in "20" "30" "40" "50"
  do

  for maxC in "100" "150" "300"
  do


  ./benchmark -N $valN -k $valueK -maxk $valuemaxK -mu $valueMu -on $valOn -om $valOm -minc $minC -maxc $maxC
mkdir "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv network.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv community.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv statistics.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"

done
done
done
done
done
done
done
done
















for valueMu in "0.1" "0.15" "0.2" "0.25" "0.3"
do

for valueK in "20"
do

for valuemaxK in "200"
do

for valOm in "2"
do

for valN in "10000"
do

for valOn in "1000"
do

  for minC in "20" "30" "40" "50"
  do

  for maxC in "100" "150" "300"
  do


  ./benchmark -N $valN -k $valueK -maxk $valuemaxK -mu $valueMu -on $valOn -om $valOm -minc $minC -maxc $maxC
mkdir "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv network.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv community.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"
mv statistics.dat "[HOON-12-$idx]N_$valN._degree._$valueK._maxdegree._$valuemaxK._mu._$valueMu._on._$valOn._om._$valOm._minc._$minC._maxc._$maxC"

done
done
done
done
done
done
done
done
