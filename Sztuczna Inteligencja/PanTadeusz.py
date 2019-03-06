#!/usr/bin/env python
# coding: utf-8

# ## Zadanie 2 

# In[1]:


Wordsx = set()
maxLen = 0
slownik=open("words_for_ai1.txt")

lines = len ( slownik.readlines ())
slownik=open("words_for_ai1.txt")
word = slownik.readline()
for i in range(0, lines):
    word = slownik.readline()
    if len(word)>maxLen:
        maxLen = len(word)
    Wordsx.add(word[0:len(word)-1])


# ## Algorytm rozbijania

# In[30]:


import copy

def dynamicznie(Word, Words, T, maxi):
    
    Maxval = [] # tablica  z makstymalnymi wartoscioami
    Combinations = [] #tablica najlepszych kombinacji
    szukaj = ''
    
    #tworzenie tablicy wartosci i kombinacji
    for i in range(0, len(Word)+1): # dla kazdej dlugosic podslowa
        Maxval.append(-1)
        Combinations.append([])
    Maxval[0]=0
    
    
    #zasadnicza czesc algorytmu
    length = len(Word)
    for i in range(0, length): #dla wszystkich dostepnych pkt startowych do najdluzszego slowa
        cei = min(i+maxLen+1, length)
        for j in range(i, cei): #dla danego pktu startowego wyklep wszystkie nastepne
            szukaj = Word[i:j+1]
            if szukaj in Words: # jestesmy w itym pucie szukamy wyrazow dlugosci j
                if len(szukaj)*len(szukaj)+Maxval[i]>Maxval[j+1]: #udalo sie znalecz lepsza kombinacje
                    Maxval[j+1] = len(szukaj)*len(szukaj)+Maxval[i]
                    Combinations[j+1] = copy.copy(Combinations[i]) #zamiana na lepszą kombinacje
                    Combinations[j+1].append(szukaj)
        
        
        #jedzie do nastepnego kawalka
        i=i+1
        while(i<length-1 and Maxval[i]<0): # chyab jedzie kawalek za daleko bo jeszce for to sie da zoptymaizowac
            i=i+1

    return Combinations[length]        


# In[34]:


def translateText():
    file = open ("zad2_input.txt")
    lines = len ( file.readlines ())
    file = open ("zad2_input.txt")
    output = open("zad2_output.txt", "w+")
    for i in range(0, lines):
        y = ''
        z = file.readline()
        x = dynamicznie(z[0:len(z)-1] , Wordsx, [], 0)
        for j in range(0, len(x)):
            y += x[j] + " "
        output.write(y + "\n")


# In[35]:


translateText()

