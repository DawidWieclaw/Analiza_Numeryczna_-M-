#!/usr/bin/env python
# coding: utf-8

# In[1]:


Stack = []
Vis = [0]*8


# ## Inicjalizacja tablicy odwiedzonych

# In[2]:


def reset():
    Stack = []
    for a in range(len(Vis)):
        Vis[a]=[0]*8
        for b in range(len(Vis[a])):
            Vis[a][b] = [0]*8
            for c in range(len(Vis[a][b])):
                Vis[a][b][c] = [0]*8
                for d in range(len(Vis)):
                    Vis[a][b][c][d] = [0]*8
                    for e in range(len(Vis)):
                        Vis[a][b][c][d][e] = [0]*8
reset()


# ## Funkcje wyznaczające możliwe ruchy z danego położenia

# ### Dla czarnego króla

# In[3]:


def sensBlack(s,i,j):
    if i<0 or i>7 or j>7 or j<0: #ruch poza plansze
        return False
    if i==s.Wtx or j==s.Wty: #pod bicie wieży 
        return False
    if abs(i-s.Wkx)<2 and abs(j-s.Wky)<2:
        return False #pod bicie króla
    return True


# ### Dla Białego Króla

# In[4]:


def sensWhiteKing(s,i,j): # jeszce wskakiwanie na pola wiezy i krola wzajemne
    if i<0 or i>7 or j<0 or j>7: #ruch poza plansze
        return False
    #if abs(i-s.Bkx)>abs(s.Wkx-s.Bkx) and abs(j-s.Bky)>abs(s.Wky-s.Bky):
    #    return False # oddala sie od czarnego krola
    if i==s.Wkx and j==s.Wky: #brak zmiany
        return False
    if abs(i-s.Bkx)<2 and abs(j-s.Bky)<2: # pod bicie króla
        return False #bez ochrony wieży
    return True


# ### Dla białej wieży

# In[5]:


def sensWhiteTower(s,i,j):
    if abs(s.Wkx-s.Bkx)>3 or abs(s.Wky-s.Bky)>3 or not (s.Bkx==0 or s.Bkx==7 or s.Bky == 0 or s.Bky == 7):
        return False
    if i==s.Wtx and j==s.Wty: #brak zmiany
        return False
    #if i<0 or j<0 or i>7 or j>7:
    #    return False
    if abs(i-s.Bkx)<2 and abs(j-s.Bky)<2: # pod bicie króla
        if abs(i-s.Wkx)<2 and abs(j-s.Wky)<2: # chronione królem
            return True
        return False
    return True


# ### Funkcja sprawdzająca czy zachodzi mat (lub pat)

# In[6]:


def ifMat(s, move): # czy w danej sytuacji wystepuje mat
    if move == 0:
        return False
    sensRuch = s.sensowneRuchy(1)
    
    b=False
    if sensBlack(s, s.Bkx, s.Bky)==False: # czyli jest bicie
        #print("BICIE:")
        #print(s.Bkx, s.Bky, s.Wkx, s.Wky, s.Wtx, s.Wty)
        b=True
    
    if len(sensRuch)==0: # nie ma sensownych ruchow
        if b == True:
            return True # mat
        else:
            return 3.14 # pat
    return False # nic  return False
    
    if b==True:
        return True
    return 3.14 # pat


# ## Klasa opisująca dane położenie pionków na planszy

# In[7]:


class Sytuacja:
    Bkx,Bky,Wkx,Wky,Wtx,Wty,m,n=0,0,0,0,0,0,0,0 # m = 1 to czarne m = 0  biale
    rodzic = 0
    odl = 0;
    def nadajRodzica(self, s):
        self.rodzic = s
    def sensowneRuchy(self, m):
        sensRuch = set()
        if m==1: # rusza sie czarny samotny krol
            for i in range(self.Bkx-1, self.Bkx+2):
                for j in range(self.Bky-1, self.Bky+2):
                    if sensBlack(self,i,j):
                        q = Sytuacja()
                        q.Bkx, q.Bky, q.Wkx, q.Wky, q.Wtx, q.Wty, q.m,q.n=i,j,self.Wkx,self.Wky,self.Wtx,self.Wty,0,self.n+1
                        sensRuch.add(q)
        else:
            for i in range(self.Wkx-1, self.Wkx+2):
                for j in range(self.Wky-1, self.Wky+2):
                    if sensWhiteKing(self,i,j):
                        q = Sytuacja()
                        q.Bkx, q.Bky, q.Wkx, q.Wky, q.Wtx, q.Wty, q.m,q.n=self.Bkx,self.Bky,i,j,self.Wtx,self.Wty,1,self.n+1
                        sensRuch.add(q)
            for i in range(0,8):
                if sensWhiteTower(self, i, self.Wty):
                    q = Sytuacja()
                    q.Bkx, q.Bky, q.Wkx, q.Wky, q.Wtx, q.Wty, q.m,q.n=self.Bkx,self.Bky,self.Wkx,self.Wky,i,self.Wty,1,self.n+1
                    sensRuch.add(q)
                if sensWhiteTower(self, self.Wtx, i):
                    q = Sytuacja()
                    q.Bkx, q.Bky, q.Wkx, q.Wky, q.Wtx, q.Wty, q.m,q.n=self.Bkx,self.Bky,self.Wkx,self.Wky,self.Wtx,i,1,self.n+1
                    sensRuch.add(q)
        return sensRuch


# ## Modyfikacja algorytmu przeszukiwania wszerz szukająca najkrótszej drogi do mata

# In[8]:


import time



def BFS(word, deb):
    maxS=0
    reset()
    first = Sytuacja()
    first.Bkx, first.Bky, first.Wkx, first.Wky, first.Wtx, first.Wty, first.n, first.m=translate(word)
    print(translate(word))
    Stack.append(first) ## tutaj korzen bez rodzica
    debug = deb
    
    
    Vis[first.Bkx][first.Bky][first.Wkx][first.Wky][first.Wtx][first.Wty]=1
    
    
    start_time = time.time()
    while len(Stack)>0:
        u=Stack.pop(0)
        p=ifMat(u, u.m)
        #print("XD")
        if p==True: # mat
            if debug:
                zwrocRuchy(u)
            print(maxS)
            print("--- %s seconds ---" % (time.time() - start_time))
            return(u.n)
        
        if p==3.14: # pat
            continue
        sensRuch = u.sensowneRuchy(u.m)
        if len(Stack)>maxS:
            maxS=len(Stack)
        for x in sensRuch:
            if Vis[x.Bkx][x.Bky][x.Wkx][x.Wky][x.Wtx][x.Wty]==0:
                Vis[x.Bkx][x.Bky][x.Wkx][x.Wky][x.Wtx][x.Wty]=1
                x.nadajRodzica(u)
                Stack.append(x)
    
    return


# ## Wypisywanie danego położenia

# In[9]:


def wypiszWsp(q):
    print(q.Bkx, q.Bky, q.Wkx, q.Wky, q.Wtx, q.Wty)


# ## Zwracanie drogi do danego położenia

# In[10]:


def zwrocRuchy(s):
    wypiszWsp(s)
    if not s.rodzic==0:
        zwrocRuchy(s.rodzic)


# In[11]:


def translate(word):
    T = str.split(word, " ")
    Bkx = ord(T[3][0])-97
    Bky = ord(T[3][1])-49
    Wkx = ord(T[1][0])-97
    Wky = ord(T[1][1])-49
    Wtx = ord(T[2][0])-97
    Wty = ord(T[2][1])-49
    if T[0] == 'black':
        m=1
    else:
        m=0
    return Bkx, Bky, Wkx, Wky, Wtx, Wty, 0, m    


# In[12]:


def translateText():
    file = open ("zad1_input.txt")
    output = open("zad1_output.txt", "w+")
    z = file.readline()
    v = (BFS(z, True))
    print(v)
    output.write(str(v))


# In[13]:


translateText()

