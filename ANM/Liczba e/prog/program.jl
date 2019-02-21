
#program oblcizajacy liczbę e
#Dawid Więcław
#Metoda wyznaczająca (1+1/n)^n
function zGranicy(n::Integer)
    e=BigFloat((BigFloat(1.0)+BigFloat(1.0)/BigFloat(n)))
    e=BigFloat(e^BigFloat(n))
    return BigFloat(e)
end

#Metoda wyznaczająca n! potrezbna do obliczania kolejnych składników szeregu Macloraine'a
function silnia(n::Integer)
    if n==0 || n==1
        return 1
    end
    e=1
    while n > 1
        e*=n
        n=n-1
    end
    return BigFloat(e)
end

#Metoda wyznaczająca e jako część sumy szeregu Macloraine'a funkcji e^x w x=1 dla n skłądników
function zSzeregu(n::Integer)
    e=BigFloat(0)
    i=n
    while i >= 0
        e+=1/silnia(i)
        i-=1
    end
    return BigFloat(e)
end

#Metoda będąca poprawioną funkcją zSzeregu (wyeliminowanie liczenia 1/n!) w postaci rekurencyjnej
function rekurencyjnie(n::Integer, e1::BigFloat)
    if n>4000
        return rekurencyjnie(4000, e1)
    end
    if n==2
        return BigFloat(2.0+e1/2)
    end
    return rekurencyjnie(n-1, 1+e1/n)
end 

#Metoda będąca poprawioną funkcją zSzeregu (wyeliminowanie liczenia 1/n!) w postaci iteracyjnej
function  iteracyjnie(n::Integer)
    e=BigFloat(1.0)
    while n>2
        e=BigFloat(1+e/n) 
        n=n-1
    end
    if n==2
        return BigFloat(2+e/2)
    end
    if n==1 
        return BigFloat(2)
    end
end 

#Dane do tabeli dla precyzji 16 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n
println("Dane do tabeli dla precyzji 16 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n")
using Printf
setprecision(16)
for i=1:11
    @printf("Dla:%.i\n", 8^i)
    @printf(" Z granicy  %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", zGranicy(8^i), Base.MathConstants.ℯ-zGranicy(8^i), (Base.MathConstants.ℯ-zGranicy(8^i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 32 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n
println("Dane do tabeli dla precyzji 32 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n")
setprecision(32)
for i=1:11
    @printf("Dla:%.i\n", 8^i)
    @printf(" Z granicy  %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", zGranicy(8^i), Base.MathConstants.ℯ-zGranicy(8^i), (Base.MathConstants.ℯ-zGranicy(8^i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 256 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n
println("Dane do tabeli dla precyzji 256 bitowej sposobem liczącym przybliżenie e jako (1+1/n)^n")
setprecision(256)
for i=1:22
    @printf("Dla:%.i\n", 8^i)
    @printf(" Z_definicji  %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", zGranicy(8^i), Base.MathConstants.ℯ-zGranicy(8^i), (Base.MathConstants.ℯ-zGranicy(8^i))/Base.MathConstants.ℯ)
end
#Dane do tabeli dla precyzji 16 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 16 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(16)
for i=1:66
    @printf("Dla:%.i\n", i)
    @printf(" Z_Szeregu    %.16f  błąd bezwzględny %.1e  błąd względny %.1e\n", zSzeregu(i), Base.MathConstants.ℯ-zSzeregu(i), (Base.MathConstants.ℯ-zSzeregu(i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 32 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 32 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(32)
for i=1:66
    @printf("Dla:%.i\n", i)
    @printf(" Z_Szeregu    %.16f  błąd bezwzględny %.1e  błąd względny %.1e\n", zSzeregu(i), Base.MathConstants.ℯ-zSzeregu(i), (Base.MathConstants.ℯ-zSzeregu(i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 256 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 256 bitowej sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(256)
for i=1:66
    @printf("Dla:%.i\n", i)
    @printf(" Z_Szeregu    %.16f  błąd bezwzględny %.1e  błąd względny %.1e\n", zSzeregu(i), Base.MathConstants.ℯ-zSzeregu(i), (Base.MathConstants.ℯ-zSzeregu(i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 16 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 16 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(16)
for i=1:8
    @printf("Dla:%.i\n", i)
    @printf(" Ulepszony   %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", iteracyjnie(i), Base.MathConstants.ℯ-iteracyjnie(i), (Base.MathConstants.ℯ-iteracyjnie(i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 32 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 32 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(32)
for i=1:16
    @printf("Dla:%.i\n", i)
    @printf(" Ulepszony   %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", iteracyjnie(i), Base.MathConstants.ℯ-iteracyjnie(i), (Base.MathConstants.ℯ-iteracyjnie(i))/Base.MathConstants.ℯ)
end

#Dane do tabeli dla precyzji 256 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1
println("Dane do tabeli dla precyzji 256 bitowej ulepszonym sposobem liczącym przybliżenie e jako fragment szeregu Macloraine'a funkcji e^x w x=1")
setprecision(256)
for i=1:64
    @printf("Dla:%.i\n", i)
    @printf(" Ulepszony   %.16f  blad_bezwzgledny %.1e  blad_wzgledny %.1e\n", iteracyjnie(i), Base.MathConstants.ℯ-iteracyjnie(i), (Base.MathConstants.ℯ-iteracyjnie(i))/Base.MathConstants.ℯ)
end