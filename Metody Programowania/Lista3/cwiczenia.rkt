#lang racket

;;zad1
(define (make-vect a b)
  (cons a b))

(define (vect-begin v)
  (car v))

(define (vect? v)
  (and (pair? v)
       (pair? (car v))
       (pair? (cdr v))))

(define (square x)
  (* x x))

(define (rat n d)
  (let ((q (gcd n d)))
    (cons (/ n q) (/ d q))))

(define (vect-length v)
  (let ((dx (- (car (car v)) (car (cdr v))))
    (dy (- (cdr (car v)) (cdr (cdr v)))))
  (sqrt (+ (square dx) (square dy)))))

(define (vect-scale v k)
  (let ((dx (- (car (car v)) (car (cdr v))))
    (dy (- (cdr (car v)) (cdr (cdr v)))))
    (cons (cons (car (car v)) (cdr (car v))) (cons (+ (car (car v)) (* dx k)) (+ (cdr (car v)) (* dy k))))))

(define (vect-translate v p)
  (let ((dx (- (car (cdr v)) (car (car v))))
    (dy (- (cdr (cdr v)) (cdr (car v)))))
    (make-vect p (cons (+ (car p) dx) (+ (cdr p) dy)))))

;;zad3
(define (append l1 l2)
  (if (null? l1)
      l2
      (cons (car l1) (append (cdr l1) l2))))

(define (reverse l)
  (if (null? l)
      null
      (append (reverse (cdr l)) (list (car l)))))

(define (reverse-iter l acc)
  (if (null? l)
      acc
      (reverse-iter (cdr l) (append (list (car l)) acc))))

;;zad4
(define (insert l n acc)
  (if (null? l)
      (append acc (list n))
      (if (> (car l) n)
          (append acc (cons n l))
          (insert (cdr l) n (append acc (list (car l)))))))

(define (insert-sort l acc)
  (if (null? l)
      acc
      (insert-sort (cdr l) (insert acc (car l) null))))


;;zad 6
(define (permi l)
  (if (null? l)
      null
      (append (list (car l)) permi (cdr l))))

;;notatki
(define (map f xs)
  (if(null? xs)
     null
     (cons (f (car xs))
           (map f (cdr xs)))))

(define (filter p? xs)
  (if (null? xs)
      null
      (if (p? (car xs))
          (cons (car xs) (filter p? (cdr xs)))
          (filter p? (cdr xs)))))







