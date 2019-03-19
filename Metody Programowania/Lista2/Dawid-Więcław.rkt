#lang racket
;;Dawid Więcław
;;Metody programowania

;;k oznacza ile raz nalezy zlozyc repeated aby sprawdzic kiedy metoda jest zbiezna
(define (sqrtn-test x k n)
  ;;Sprawdzanie czy wynik blisko rozwiazania
  (define (good? x y)
    (< (abs (- x y)) 0.000001))
  ;;liczenie sredniej
  (define (average x y)
    (/ (+ x y) 2))
  ;;skladanie funkcji
  (define (compose f g)
    (lambda (x) (f (g x))))
  ;;Funckja skladajaca funkcje nkrotnie
  (define (repeated p n)
    (if (= n 0)
      identity
      (compose p (repeated p (- n 1)))))
  ;;Wyznaczanie punktu stałego przekształcenia
  (define (fixed-point f s)
    (define (iter k)
      (let ((new-k (f k)))
        (if (good? k new-k)
          k
          (iter new-k))))
  (iter s))

  ;;Tłumienie z uśrednieniem
  (define (average-damp f)
    (lambda (x) (average x (f x))))

  
  (fixed-point ((repeated average-damp k) (lambda (y) (/ x (expt y (- n 1))))) 1.0))


;;EKSOPERYMENTALNE WYZNACZANIE STOPNIA TLUMIENIA
;;dziala
;(sqrtn-test 32 0 1)
;;nie dziala
;;(sqrtn-test 32 0 2)
;;dziala
;(sqrtn-test 32 1 2)
;(sqrtn-test 32 1 3)
;;nie dziala
;;(sqrtn-test 32 1 4)
;;dziala
;(sqrtn-test 32 2 4)
;(sqrtn-test 32 2 7)
;;nie dziala
;;(sqrtn-test 32 2 8)
;;dziala
;(sqrtn-test 32 3 8)
;(sqrtn-test 32 3 12)
;(sqrtn-test 32 3 15)
;;nie dziala
;;(sqrtn-test 32 3 16)

;;Na podstawie powyzszego eksperymentu mozna dojsc do wniosku ze prawidlowym stopniem tlumiania
;;jest podloga z logarytmu dwojkowego stopnia pierwiastka

;;Drugi test
(define (log2 x)
  (define (log-iter x k n)
    (if (> k x)
        (- n 1)
        (log-iter x (* 2 k) (+ n 1))))
  (log-iter x 1 0))

;(sqrtn-test 32 (log2 10) 10)
;(sqrtn-test 32 (log2 30) 30)
;(sqrtn-test 32 (log2 100) 100)
;(sqrtn-test 102444 (log2 110) 110)

;;Dla wsyzstkich powyżsyzch zadziałało

;;nie dziala:
;(sqrtn-test 32 (- (log2 10) 1) 10)
;(sqrtn-test 32 (- (log2 30) 1) 30)
;(sqrtn-test 32 (- (log2 100) 1) 100)
;(sqrtn-test 102444 (- (log2 110) 1) 110)

;;Prowadzi to do wniosku że twierdzenie dotyczące stopnia tłumienia było prawidłowe


;;Ostateczna postać funkcji obliczającej pierwiastek ntego stopnia
(define (nth-root x n)

  (define (average x y)
    (/ (+ x y) 2))
  
  (define (compose f g)
    (lambda (x) (f (g x))))
  
  (define (good? x y)
    (< (abs (- x y)) 0.000001))
  
  ;;ile razy trzeba zrobic repeated
  (define (log2)
    (define (log-iter k l)
      (if (> k n)
        (- l 1)
        (log-iter (* 2 k) (+ l 1))))
  (log-iter 1 0))

  ;;Funckja skladajaca funkcje
  (define (repeated p n)
    (if (= n 0)
      identity
      (compose p (repeated p (- n 1)))))

  ;;Wyznaczanie punktu stałego przekształcenia
(define (fixed-point f s)
  (define (iter k)
    (let ((new-k (f k)))
      (if (good? k new-k)
          k
          (iter new-k))))
  (iter s))

  ;;Tłumienie z uśrednieniem
  (define (average-damp f)
    (lambda (x) (average x (f x))))

  
  (fixed-point ((repeated average-damp (log2)) (lambda (y) (/ x (expt y (- n 1))))) 1.0))

;Testy:
(nth-root 1024 10)
(nth-root 216 3)
(nth-root 1024 20)
(nth-root (* 1024 1024) 20)
