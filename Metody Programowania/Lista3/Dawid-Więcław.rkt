#lang racket
(require rackunit)
(require rackunit/text-ui)
;;Dawid Więcław
;;Metody Programowania
;;Pracowania 4

;;Liczenie rozmiaru listy
  (define (size xs)
    (define (size-iter xs acc)
      (if (null? xs)
          acc
          (size-iter (cdr xs) (+ 1 acc))))
    (size-iter xs 0))


;;Łączanie dwóch list w jedną
(define (append-fixed xs ys)
  
  (define (append-rek l1 l2)
    (if (or (null? l1) (empty? l1))
        l2
        (cons (car l1) (append-rek (cdr l1) l2))))
  
  (cond ((and (list? xs) (list? ys)) (append-rek xs ys))
        ((and (number? xs) (list? ys)) (append-rek (list xs) ys))
        ((and (list? xs) (number? ys)) (append-rek xs (list ys)))
        ((and (number? xs) (number? ys)) (append-rek (list xs) (list ys)))
        (else null)))

;;Łączenie dwóch posortowanych list
(define (merge xs ys)
    (define (merge-iter xs ys acc)
      (cond ((null? xs) (append-fixed acc ys))
            ((null? ys) (append-fixed acc xs))
            ((> (car xs) (car ys)) (merge-iter xs (cdr ys) (append-fixed acc (car ys))))
            (else (merge-iter (cdr xs) ys (append-fixed acc (car xs))))))

  (merge-iter xs ys null))




;;Dzielenie jednej listy na dwie mniej więcej tego samego rozmiaru
(define (split xs)
    
  (define length (/ (size xs) 2))
    
  (define (split-iter xs acc n)
    (if (not (< n length))
      (cons acc (list xs))
      (split-iter (cdr xs) (append acc (list (car xs))) (+ n 1))))
  (split-iter xs null 0))


;;Rekurencyjna procedura sortująca tablice przez scalanie
(define (merge-sort xs)
(if (> (size xs) 1)
    (merge (merge-sort (car (split xs))) (merge-sort (car (cdr (split xs)))))
    xs))

;;Testy
(define merge-sort-tests
  (test-suite
   "Test of merge-sort function"
   (test-case
    "Null list check"
    (let ((list null))
      (check-equal? (merge-sort list) list)))

   (test-case
    "Empty list check"
    (let ((list '()))
      (check-equal? (merge-sort list) list)))

   (test-case
    "Sort test 1"
    (let ((list '(1 9 0 92 3 4 5 21 1)))
      (check-equal? (merge-sort list) '(0 1 1 3 4 5 9 21 92))))
   
   (test-case
    "Sort test 2"
    (let ((list '(1 2 3 4 5 6 7 8 9)))
      (check-equal? (merge-sort list) '(1 2 3 4 5 6 7 8 9))))

   (test-case
    "Sort test 3"
    (let ((list '(23 9 0 2 9 7 9 8 3 42 123 23 12)))
      (check-equal? (merge-sort list) '(0 2 3 7 8 9 9 9 12 23 23 42 123))))

   (test-case
    "Split test 1"
    (let ((list '(23 9 0 2 9 7 9 8 3 42 123 23 12)))
      (check-equal? (split list) '((23 9 0 2 9 7 9) (8 3 42 123 23 12)))))
   
   (test-case
    "Split test 2"
    (let ((list '(1 9 0 92 3 4 5 21 1)))
      (check-equal? (split list) '((1 9 0 92 3) (4 5 21 1)))))
  
   
   (test-case
    "Merge test 1"
      (check-equal? (merge '(0 2 3 7 8 9 9 9) '(12 23 23 42 123) ) '(0 2 3 7 8 9 9 9 12 23 23 42 123)))
   
   (test-case
    "Merge test 2"
      (check-equal? (merge '(1 9 0 92 3) '(4 5 21 1)) '(1 4 5 9 0 21 1 92 3)))
   ))

;;Wypisanie wynikow testowania na ekran
(run-tests merge-sort-tests)