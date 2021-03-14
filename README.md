# Project: Music Char Application
___
Projekt został napisany w javie 11 z urzyciem narzędzia maven w wersji 4.0. i jest aplikacją konsolową.
Do poprawnego wyświetlania listy piosenek wymagana jest szerokość okna konsoli ustawiona na przynajmniej 152kolumny.

W celu uruchomienia programu proszę wykonać poniższą komendę w systemie windows:\
```
> git clone https://github.com/poplar82/MusicCharApp.git
&& cd MusicCharApp && git checkout tags/solution
&& compile && run [argumenty]
  ```
lub dla systemów linux, iOS:
```
$ git clone https://github.com/poplar82/MusicCharApp.git
&& cd MusicCharApp && git checkout tags/solution
&& ./compile.sh && ./run.sh [argumenty]
```
___
#### Treść zadania
Program do obsługi zamówień, który na wejściu przyjmuje plik z listą piosenek w formacie csv i xml.
Każdy plik zawiera jedną lub więcej piosenek
Każdą piosenkę należy zapisać w pamięci

Piosenka powinna zawierać następujące atrybuty:
1. Tytuł
1. Autor
1. Płyta
1. Kategoria (typ wyliczalny)
1. Głosy inicjalne

   Przy wczytaniu więcej niż 1 pliku, system musi sprawdzić czy piosenka już nie istnieje w
   bazie, po czym posumować jej głosy inicjalne.
### System będzie miał następujące funkcjonalności:
- Oddanie głosu na piosenkę
- Dodanie piosenki
- Wyzerowanie głosów (dla wybranej piosenki i dla wszystkich piosenek)
- Zrobienie i wypisanie raportu z rankingu (top 10, top 3 i wszystkie)
- Zrobienie i wypisanie raportu wg. Kategorii
  Każdy raport można wyświetlić na ekranie, albo zapisać do pliku csv lub xml
  Nieprawidłowe piosenki są ignorowane, ale informacja o złym formacie jest wypisywana na
  ekran.
###Załącznik – formaty
Plik CSV\
Pierwsza linia zawiera nazwy kolumn i zawsze występuje, kolumny są oddzielone przecinkiem:
```csv
   Title,Author,Album,Category,Votes
   Living in a Ghost Town,The Rolling Stones,Honk,Rock,10
   You Should Be Sad,Halsey,Manic,Alternative,2
   Imported,Jessie Reyez,Before Love Came to Kill Us,R&B,6
```
Plik XML\
Plik zawsze jest poprawnym dokumentem xml. Nie zawiera dodatkowych tagów.<songs>
```xml   
<song>
<title>Living in a Ghost Town</title>
<author>The Rolling Stones</author>
<album>Honk</album>
<category>Rock</category>
<votes>10</votes>
</song>
<song>
<title>You Should Be Sad</title>
<author>Halsey</author>
<album>Manic</album>
<category>Alternative</category>
<votes>2</votes>
</song>
<song>
<title>Imported</title>
<author>Jessie Reyez</author>
<album>Before Love Came to Kill Us</album>
<category>R&B</category>
<votes>6</votes>
</song>
</songs>
```