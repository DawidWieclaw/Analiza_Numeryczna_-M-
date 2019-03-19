#lang racket

;;Dawid Więcław
;;Metody Programowania

(define (square x)
  (* x x))

;;Funkcja wyliczajaca nty licznik (dla zadania 8) 
(define (N k)
  (if (= k 0)
      (lambda (x) x)
      (lambda (x) (square (* k x)))))



;;Funkcja wyliczająca nty mianownik (dla zadania 8)
(define (D k)
  (+ 1 (* 2 k)))


;;Funkcja wyliczająca wartość funkcji przebliżaną ułamkami łanuchowymi
(define (AB-iter A1 A2 B1 B2 x n N D)
  (if (< 2 n)
      (if (> 0.00001 (abs (- (/ A1 B1) (/ A2 B2))))
          (/ A1 B1)
          (AB-iter (+ (* (D n) A1) (* ((N n) x) A2)) A1 (+ (* (D n) B1) (* ((N n) x) B2)) B1 x (+ n 1) N D))
  (AB-iter (+ (* (D n) A1) (* ((N n) x) A2)) A1 (+ (* (D n) B1) (* ((N n) x) B2)) B1 x (+ n 1) N D)))


;;Testy
(AB-iter 0 1 1 0 0.0 0 N D)
(AB-iter 0 1 1 0 1.0 0 N D)
(AB-iter 0 1 1 0 2.0 0 N D)
(AB-iter 0 1 1 0 3.0 0 N D)
(AB-iter 0 1 1 0 4.0 0 N D)
(AB-iter 0 1 1 0 0.5 0 N D)
(AB-iter 0 1 1 0 1.71 0 N D)


;;Obliczanie 1/phi
(AB-iter 0 1 1 0 0.0 0 (lambda (k) (lambda (x) 1.0)) (lambda (x) 1.0))






