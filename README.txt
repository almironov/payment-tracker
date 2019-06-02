Payment Tracker

1. Install

Installed on the computer:
- JDK 1.8
- Maven 4.0

Build:
git clone https://github.com/almironov/payment-tracker.git
cd payment-tracker
mvn package

Run:
java -jar payment-tracker-1.0-jar-with-dependencies.jar

2. Description of the program

Program that keeps a record of payments. Each payment includes a currency and an amount.
Data should be kept in memory.

The program output a list of all the currency and amounts to the console once per minute.
The input be typed into the command line, and optionally also be loaded from a file.

Sample input:
USD 1000
HKD 100
USD -100
RMB 2000
HKD 200

Sample output:
USD 900
RMB 2000
HKD 300

When your Java program is run, a filename optionally specified. For enter data from a file,
enter 'file'. The format of the file will be one or more lines with Currency Code Amount 
like in the Sample Input above, where the currency may be any uppercase 3 letter code, 
such as USD, HKD, RMB, NZD, GBP etc. The user can then enter more lines into the console by
typing a currency and amount and pressing enter. Once per minute, the output showing the 
net amounts of each currency. If the net amount is 0, that currency is not displayed. When 
the user types "quit", the program should exit.

If the user enters invalid input, the program to display an error message and you can 
continue to set payments.

For each currency, the exchange rate is adjusted against the US dollar. When a withdrawal
is displayed, an equivalent dollar amount is displayed next to it, for example:
USD 900
RMB 2000 (USD 314.60)
HKD 300 (USD 38.62)