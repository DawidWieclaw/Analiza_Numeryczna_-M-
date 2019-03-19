#lang racket
(define (f a b c)
  (cond ((and (not (> a b)) (not (> a c))) (+ (* b b) (* c c)))
        ((and (not (> c b)) (not (> c a))) (+ (* b b) (* a a)))
        (else (+ (* a a) (* c c)))))

(define (silnia n)
  (cond ((= n 0) 1)
        ((= n 1) 1)
        (else (* n (silnia (- n 1))))))

(define (p)
  (p))

(define (test x y)
  (if (= x 0)
      0
      y))


(define (power-close-to b n)
  (define (power-close-to b n m)
    (if(> (expt b m) n)
       m
       (power-close-to b n (+ m 1))))
  (power-close-to b n 1))

