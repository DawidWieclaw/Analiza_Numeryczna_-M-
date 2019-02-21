
#k, m zamieniane wiersze, n rozmiar macierzy, M macierz
using LinearAlgebra
using Printf
setprecision(32)
function swapWerse(k, m, pi)
    pi[m]=k
    pi[k]=m
end

function wypisz(M)
    n,m=size(M)
    for i=1:n
        for j=1:n
            @printf("%.1f ", M[i,j])
        end
        @printf("\n")
    end
end

#Funkcja pomocnicza obliczająca |x|
function modulo(x)
    if x>=0
        return x
    end
    return -x
end

#Zwracany jest numer wiersza
function findNotZero(k::Integer, M, pi)
    max=0.0
    m=k
    n,n=size(M)
    for i=k:n
        if abs(M[pi[i],k])>max
            max=abs(M[pi[i],k])
            m=i
        end
    end
    if max==0.0
        return false
    end
    return m
end

#rozklad LU
function elimination(M)
    n,n=size(M)
    pi=Array{Integer,1}(undef, n)
    
    #uzupełnianie tablicy permutacji wierszy macierzy
    for i=1:n
        pi[i]=i
    end
    
    #eliminacja
    for i=1:n-1 
        #wybór częściowy
        if M[pi[i],i]==0.0
            s = findNotZero(i, M, pi)
            if s==false
                return 0.0
            end
            swapWerse(pi[i],s,pi)
        end
        
        #częśc zasadnicza
        for k=i+1:n
            for l=i+1:n
                b=M[pi[i],l]
                b/=M[pi[i],i]
                b*=M[pi[k],i]
                M[pi[k],l]-=b
            end
            M[pi[k],i]=0.0  #M[pi[k],i]/M[pi[i],i]
        end
    end
    #obliczanie wyznacznika
    k=1.0
    for i=1:n
        k*=M[pi[i],i]
    end
    return k
end

function Hilbert(n::Integer)
    M=Array{BigFloat,2}(undef, n,n)
    for i=1:n
        for j=1:n
            M[i,j]=BigFloat(1/(i+j-1))
        end
    end
    return M
end

function Pei(n::Integer, p)
    M=Array{BigFloat,2}(undef, n,n)
    for i=1:n
        for j=1:n
            M[i,j]=1
            if i==j
                M[i,j]=p
            end
        end
    end
    return M
end

function Lower(n, maxVal)
    Low = Array{BigFloat,2}(undef, n,n)
    for i=1:n
        for j=1:i
            Low[i,j]=rand()*rand(1:maxVal)*(-1)^(rand(1:2))
        end
        
        for j=i:n
            Low[i,j]=0
        end 
        Low[i,i]=1
    end
    return Low
end

function Upper(n, maxVal)
   Upp = Array{BigFloat,2}(undef, n,n)
    for i=1:n
        for j=1:i
            Upp[i,j]=0
        end
        for j=i:n
            Upp[i,j]=rand()*rand(1:maxVal)*(-1)^(rand(1:2))
        end 
    end
    return Upp
    
end

function detUp(A)
    n,n=size(A)
    d=1
    for i=1:n
        d*=A[i,i]
    end
    return d
end

function mult(A, B)
    n,n=size(A)
    C=Array{BigFloat,2}(undef, n,n)
    for i=1:n
        for j=1:n
            C[i,j]=0
            for k=1:n
                C[i,j]+=A[i,k]*B[k,j]
            end
        end
    end
    return C
end

function genRandomMatrix(n, maxVal)
    Upp=Upper(n, maxVal)
    Low=Lower(n, maxVal)
    d=detUp(Upp)
    return mult(Low,Upp), d
end

function tests(number, maxVal, precision, eps)
    dif=0
    maxi=0
    averageDif=BigFloat(0.0)
    T=Array{BigFloat,1}(undef, number)
    prop = 0
    dif2=0
    pack=0
    for N=1:number
        T[N]=0
        n=rand(2:50)
        M,propDet = genRandomMatrix(n, maxVal)
        prop = LinearAlgebra.det(copy(M))
        det = elimination(copy(M))
        dif=abs(propDet-det)
        dif2=abs(propDet-prop)
        
        if propDet!=0
            T[N]=dif/propDet
            maxi = max(maxi, abs(T[N]))
            pack = max(pack, abs(dif2/propDet))
        elseif det != 0
            T[N]=dif
        end
    end

    T=sort(T)
    k=0.0
    for N=1:number
        if abs(T[N])<eps
            k+=1.0
        end
    end
    k*=100
    k/=number
    @printf("Procent prawidłowych dla precyzji %d bitowej dla %d testów dla %.1e epsilona: %.1e  \n",precision, number,eps, k)
    @printf("Maksymalny błąd w precyzji %d bitowej dla %d testów: %.1e\n",precision, number, maxi)
    @printf("Maksymalny błąd dla LinearAlgebra.det(): %.1e\n",pack)
end

setprecision(16)
tests(10000, 100, 16, 10^(-2))
setprecision(32)
tests(10000, 100, 32, 10^(-4))
setprecision(64)
tests(10000, 100, 64, 10^(-8))
setprecision(1024)
tests(10000, 100, 1024, 10^(-200))

function testsHilbert(number, precision, eps)
    dif=0
    maxi=0
    averageDif=BigFloat(0.0)
    T=Array{BigFloat,1}(undef, number)

    for N=1:number
        T[N]=0
        n=rand(2:50)
        M=Hilbert(n)
        propDet = LinearAlgebra.det(M)
        det = elimination(copy(M))
        dif=abs(propDet-det)
        
        if propDet!=0
            T[N]=dif/propDet
            maxi = max(maxi, T[N])
        elseif det != 0
            T[N]=dif
        end
    end

    T=sort(T)
    k=0.0
    for N=1:number
        if abs(T[N])<eps
            k+=1.0
        end
    end
    k*=100
    k/=number
    @printf("Procent prawidłowych policzeń w precyzji %d bitowej dla %d testów: %.1e\n",precision, number, k)
    @printf("Maksymalny błąd w precyzji %d bitowej dla %d testów: %.1e\n",precision, number, maxi)
end

setprecision(16)
testsHilbert(10000, 16, 10^(-2))
setprecision(32)
testsHilbert(10000, 32, 10^(-4))
setprecision(64)
testsHilbert(10000, 64, 10^(-8))
setprecision(1024)
testsHilbert(10000, 1024, 10^(-200))

function testsPei(number, precision, eps)
    dif=0
    maxi=0
    averageDif=BigFloat(0.0)
    T=Array{BigFloat,1}(undef, number)

    for N=1:number
        T[N]=0
        n=rand(2:50)
        M=Pei(n, 1+(1/10)*rand()*((-1)^(rand(1:2))))
        propDet = LinearAlgebra.det(M)
        det = elimination(copy(M))
        dif=abs(propDet-det)
        
        if propDet!=0
            T[N]=dif/propDet
            maxi = max(maxi, T[N])
        elseif det != 0
            T[N]=dif
        end
    end

    T=sort(T)
    k=0.0
    for N=1:number
        if abs(T[N])<eps
            k+=1.0
        end
    end
    k*=100
    k/=number
    @printf("Procent prawidłowych policzeń w precyzji %d bitowej dla %d testów: %.1e\n",precision, number, k)
    @printf("Maksymalny błąd w precyzji %d bitowej dla %d testów: %.1e\n",precision, number, maxi)
end

setprecision(16)
testsPei(10000, 16, 10^(-4))
setprecision(32)
testsPei(10000, 32, 10^(-8))
setprecision(64)
testsPei(10000, 64, 10^(-16))
setprecision(1024)
testsPei(10000, 1024, 10^(-400))
