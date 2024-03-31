mvn compile
mkdir -p snapshots
java -cp target/gilded-rose-kata-0.0.1-SNAPSHOT.jar com.gildedrose.TexttestFixture 30 > snapshots/run.txt

diff -u snapshots/run.txt texttests/ThirtyDays/stdout.gr
