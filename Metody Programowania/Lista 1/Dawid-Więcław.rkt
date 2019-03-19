#lang racket
;;Dawid Więcław
;;Metody Programowania

;;Pierwiastek szescienny rzeczywisty
(define (cube-root x)
  
  ;;Funkcja licząca moduł liczby
  (define (abs x)
  (if (< x 0)
      (- x)
      x))
  
  ;;Funkcja licząca odległość błędu od 0
  (define (dist y)
  (abs (- x y)))
  
  ;;Poprawa metodą Newtona
  (define (improve y)
  (/ (+ (/ x (* y y)) (* 2 y)) 3))
  
  ;;Sprawdzenie czy przybliżenie jest wystarczające
  (define (good-enough? g)
    (< (dist (* g g g))
       0.00000001))
  
  ;;Iteracje metody Newtona
  (define (iter guess)
    (if (good-enough? guess)
        guess
        (iter (improve guess))))
  
  ;;Początkowe przybliżenie w celu uniknięcia rozbieżności (dla 2 przy metodzie Newtona dla przyblizenia początkowego 1 styczna nie miała miejsc zerowych)
  (if (< x 0)
      (iter -1.0)
      (iter 1.0))
  )


;;Funkcja do testowania zwraca informacje o dokladnosci wyniku
(define (check-result number)
  (define root (cube-root number))
  (define (abs x)
    (if (< x 0)
         (- x)
          x))
  (if (> 0.00000001 (abs (- number (* root root root))))
      #t
      #f)
  )

;;Testy

(check-result 100)
(check-result 50)
(check-result 37)
(check-result 29)
(check-result 17)
(check-result 11)
(check-result 7)
(check-result 3)
(check-result 2)
(check-result 1.9)
(check-result 1.8)
(check-result 1.7)
(check-result 1.6)
(check-result 1.5)
(check-result 1.4)
(check-result 1.3)
(check-result 1.2)
(check-result 1.1)
(check-result 1.0)
(check-result 0.9)
(check-result 0.8)
(check-result 0.7)
(check-result 0.6)
(check-result 0.5)
(check-result 0.4)
(check-result 0.3)
(check-result 0.2)
(check-result 0.1)
(check-result 0.0)
(check-result -50)
(check-result -37)
(check-result -29)
(check-result -17)
(check-result -11)
(check-result -7)
(check-result -3)
(check-result -2)
(check-result -1.9)
(check-result -1.8)
(check-result -1.7)
(check-result -1.6)
(check-result -1.5)
(check-result -1.4)
(check-result -1.3)
(check-result -1.2)
(check-result -1.1)
(check-result -1.0)
(check-result -0.9)
(check-result -0.8)
(check-result -0.7)
(check-result -0.6)
(check-result -0.5)
(check-result -0.4)
(check-result -0.3)
(check-result -0.2)
(check-result -0.1)
(check-result -0.0)
