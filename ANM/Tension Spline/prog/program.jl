
#Zadanie 14
#hiperboliczna funkcja sklejana interpolacyjna (tension spline)
#Dawid Więcław
#Interpolowana funkcja to f(x) która jest zdefiniowana globalnie
#Do interpolacji należy utworzyć tablicę M o n+1 elementach (pustą), a także tablicę X z argumentami
#w których nastąpi interpolacja
#funkcja f musi być zdefiniowana tak aby w każdym z X[i] zwracała ona wartość
#Następnie należy wywołać funkcję fillM(), która uzupełni globalnie utworzoną tablicę M
#Hiperbliczna funkcja interpolacyjna to Sr(x) i można ją wywołać dla dowolnego argumentu
#Dla argumentu spoza zakresu zwraca ona wartosć odpowiednio jak na ostatnim lub pierwszym przedziale

#Funkcje pomocnicze

using Plots
function gamma(i::Integer)
    return BigFloat((f(X[i+2])-f(X[i+1]))/h(i))
end

function h(k::Integer)
    return BigFloat(X[k+2]-X[k+1])
end

function sinh(x)
    return BigFloat((Base.MathConstants.ℯ^x-Base.MathConstants.ℯ^(-x))/2.0)
end

function cosh(x)
    return BigFloat((Base.MathConstants.ℯ^x+Base.MathConstants.ℯ^(-x))/2.0)
end

function alpha(i)
    return BigFloat((1.0/h(i))-r/sinh(r*h(i)))
end

function beta(i)
    return BigFloat(r*cosh(r*h(i))/sinh(r*h(i))-1.0/h(i))
end

function y(i)
    return gamma(i)-gamma(i-1)
end

#Funcja znajduąca przedział w którym znajduje się x (xk<x<x(k+1))
#Zwraca -1 w przypadku x spoza przedziału
function findXi(x)
    if x<X[1] 
        return 0
    end
    if x>X[n] 
        return n-1
    end
    for i=2:n+1
        if x<=X[i] && x>=X[i-1]
            return i-2
        end
    end
end

#Funkcja uzupełniająca tablicę pomocniczna Y potrzebną do wyliczenia wartości tablicy M
function fillY(Y)
    for i=1:n-1
        Y[i]=y(i)
    end
    return Y
end

#Funkcja uzupełniająca tablicę pomocniczna T potrzebną do wyliczenia wartości tablicy M
function fillT(T)
    i=1
    a=1
    b=1
    
    while i<=2*n-3
        T[i]=beta(b-1)+beta(b)
        
        if i!=2*n-3
            T[i+1]=alpha(a)
        end
        
        a+=1 
        b+=1 
        i+=2 
    end
    
    return T
end

#Zmodyfikowana metoda eliminacji Gaussa dla przypadku układu równań występującego w zadaniu
#Czas działania algorytmu wynosi O(n)
function fixedGauss(T,Y)
    i=2
    while i<2*n-3
        Y[Integer(i/2)]*=T[i]/T[i-1]
        T[i]*=T[i]/T[i-1]
        Y[Integer(i/2)+1]-=Y[Integer(i/2)]
        T[i+1]-=T[i]
        i+=2
    end
    #Z założeń zadania pierwszy i ostatani element tablicy M jest równy 0
    M[n+1]=0
    M[n]=Y[n-1]/T[2*n-3]
    M[1]=0
end

#Funkcja wypełniająca tablicę M
function fillM()#w czasie liniowym wyprowadzenie algorytm testy oszacowanie bledu
    T=Array{BigFloat,1}(undef, 2*n-3)
    Y=Array{BigFloat,1}(undef, n-1)

    Y=fillY(Y)
    T=fillT(T)
    println(T)
    fixedGauss(T,Y)

    Y=fillY(Y)
    T=fillT(T)
    i=n-1
    while i>1
        M[i]=(Y[i-1]-T[2*(i-1)]*M[i+1])/T[2*(i-1)-1]
        i-=1
    end
end

#hiperboliczna funkcja sklejana interpolacyjna
function Sr(x)
    k=findXi(x)

    a = M[k+1]*sinh(r*(BigFloat(X[k+2])-x))/sinh(r*h(k))
    b = M[k+2]*sinh(r*(x-BigFloat(X[k+1])))/sinh(r*h(k))
    c = (f(X[k+1])-M[k+1])*(BigFloat(X[k+2])-x)/h(k)
    d = (f(X[k+2])-M[k+2])*(x-BigFloat(X[k+1]))/h(k)
    
    result = a+b+c+d
    return result
end

function f(x)
    return C[x+1]
end
