@Echo off
mvn exec:java -Dexec.mainClass=com.topolski.MusicCharApp -Dexec.args="%1 %2 %3 %4 %5 %6 %7 %8 %9" -q