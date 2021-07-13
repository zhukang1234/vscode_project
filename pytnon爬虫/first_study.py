from urllib.request import urlopen
from bs4 import BeautifulSoup as bf
html=urlopen("http://www.baidu.com/")
html_text=bytes.decode(html.read())
obj=bf(html_text,'html.parser')
tit=obj.head.title
print(tit)