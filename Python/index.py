from bs4 import BeautifulSoup
import urllib.request
import sqlite3

conn = sqlite3.connect('dictionary.db')
c = conn.cursor()
# c.execute('''CREATE TABLE words (id INTEGER PRIMARY KEY, tr TEXT NOT NULL, en TEXT NOT NULL)''')

url = "https://blog.konusarakogren.com/en-cok-kullanilan-ingilizce-kelimeler-ve-anlamlari"
urlopen = urllib.request.urlopen(url)

soup = BeautifulSoup(urlopen, 'html.parser')

index = 0
wordTR = ""
wordEN = ""
for td in soup.find_all('td'):
    if(index%2 == 0):
        wordEN = td.text.strip()
    else:
        wordTR = td.text.strip()
        c.execute("INSERT INTO words (tr, en) VALUES (?, ?)", (wordTR, wordEN))
    
    

    index += 1

conn.commit()
conn.close()